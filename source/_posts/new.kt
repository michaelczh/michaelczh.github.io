/*
 * Copyright (C) 2019 Yahoo Japan Corporation. All Rights Reserved.
 */

package jp.co.yahoo.sparkle.itemservice.domain.collector

import io.mockk.every
import io.mockk.mockk
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.CategoryPf
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.CategoryPf.Response.ProductCategories.ProductCategory
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.CsPf
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.ItemPf
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.ItemReqPf
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.PvPf
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.UserPf
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.Astrea
import jp.co.yahoo.sparkle.itemservice.infrastructure.dto.TripPf
import jp.co.yahoo.sparkle.itemservice.shared.graphql.GraphQLResponse
import jp.co.yahoo.sparkle.itemservice.shared.utils.Failure
import jp.co.yahoo.sparkle.itemservice.shared.utils.Platform
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException

class UserCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<UserPf.Response.Users.User>()

        val subject = UserCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = UserPf.Response.Users(
                        users = listOf(expect)
                    ),
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UserNotFoundException::class)
    fun convert_listが空のとき() {
        // arrange
        val subject = UserCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = UserPf.Response.Users(listOf()),
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class UsersCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<List<UserPf.Response.Users.User>>()

        val subject = UsersCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = UserPf.Response.Users(
                        users = expect
                    ),
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UserNotFoundException::class)
    fun convert_listが空のとき() {
        // arrange
        val data: UserPf.Response.Users? = null
        val subject = UsersCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class UpdateUserStatusCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val testFirstRegisteredTime = 1L
        val testFirstPurchasedTime = 2L
        val expect = mockk<UserPf.Response.Users.User.Status> {
            every { firstRegisteredTime } returns testFirstRegisteredTime
            every { firstPurchasedTime } returns testFirstPurchasedTime
        }

        val subject = UpdateUserStatusCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = UserPf.Response.Users.User.StatusContents(
                        status = expect
                    ),
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
        assertEquals(testFirstRegisteredTime, actual.firstRegisteredTime)
        assertEquals(testFirstPurchasedTime, actual.firstPurchasedTime)
    }

    @Test(expected = Failure.UserStatusNotFoundException::class)
    fun convert_statusがnullのとき() {
        // arrange
        val data: UserPf.Response.Users.User.StatusContents? = null
        val subject = UpdateUserStatusCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class CategoryCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ProductCategory>()
        val subject = CategoryCollector(CompletableFuture.completedFuture(mockk {
            every { categories } returns listOf(expect)
        }))

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }
}

class BrandCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val input = listOf(
            CategoryPf.Response.Brand(
                id = 1,
                name = "test",
                parentId = 0
            ),
            CategoryPf.Response.Brand(
                id = 2,
                name = "sub_test",
                parentId = 1
            ),
            CategoryPf.Response.Brand(
                id = 3,
                name = "child",
                parentId = 2
            )
        )

        val expect = CategoryPf.Response.Brand.OutPutBrand(
            id = 3,
            name = "child",
            parents = listOf(
                CategoryPf.Response.Brand.ParentBrand(
                    id = 1,
                    name = "test"
                ),
                CategoryPf.Response.Brand.ParentBrand(
                    id = 2,
                    name = "sub_test"
                )
            )
        )
        val subject = BrandCollector(CompletableFuture.completedFuture(mockk {
            every { categories } returns input
        }))

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }
}

class ItemCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Response.Item.StandardItem>()
        val subject = ItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { items } returns listOf(expect)
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.ItemNotFoundException::class)
    fun convert_listが空のとき() {
        // arrange
        val subject = ItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = ItemPf.Response.Items(items = listOf()),
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class ItemsCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<List<ItemPf.Response.Item.StandardItem>>()
        val subject = ItemsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = ItemPf.Response.Items(items = expect),
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    // when the retrieved items list is empty, it won't throw exception
    @Test
    fun convert_listが空のとき() {
        // arrange
        val expect = listOf<ItemPf.Response.Item.StandardItem>()
        val subject = ItemsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = ItemPf.Response.Items(items = expect),
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }
}

class RegisterItemCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Response.Item.StandardItem>()
        val subject = RegisterItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk() {
                        every { registeredItem } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.ItemNotFoundException::class)
    fun convert_RegisteredItemがnullのとき() {
        // arrange
        val data: ItemPf.Response.RegisteredItem? = null
        val subject = RegisterItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class UpdateItemCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Response.Item.StandardItem>()
        val subject = UpdateItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { updatedItem } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.ItemNotFoundException::class)
    fun convert_UpdatedItemがnullのとき() {
        // arrange
        val data: ItemPf.Response.UpdatedItem? = null
        val subject = UpdateItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class SellerItemsCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Response.SellerItems>()
        val subject = SellerItemsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = expect,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_SellerItemがnullのとき() {
        // arrange
        val data: ItemPf.Response.SellerItems? = null
        val subject = SellerItemsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class PurchasedItemsCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Response.PurchasedItems>()
        val subject = PurchasedItemsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = expect,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_PurchasedItemsがnullのとき() {
        // arrange
        val data: ItemPf.Response.PurchasedItems? = null
        val subject = PurchasedItemsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class RegisterLikeCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = 100L
        val subject = RegisterLikeCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { registerLike } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.RegisterLikeFailed::class)
    fun convert_RegisterLikeがnullのとき() {
        // arrange
        val data: ItemPf.Response.RegisterLike? = null
        val subject = RegisterLikeCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class LikedItemsCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Response.LikedItems>()
        val subject = LikedItemsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = expect,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_LikedItemsがnullのとき() {
        // arrange
        val data: ItemPf.Response.LikedItems? = null
        val subject = LikedItemsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class DeleteLikeCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = true
        val subject = DeleteLikeCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { deleteLike } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.DeleteLikeFailed::class)
    fun convert_DeleteLikeがnullのとき() {
        // arrange
        val data: ItemPf.Response.DeleteLike? = null
        val subject = DeleteLikeCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class CountBundleRequestsCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemReqPf.Response.CountBundleRequests>()
        val subject = CountBundleRequestsCollector(
            future = CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = expect,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_CountBundleRequestsがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.CountBundleRequests? = null
        val subject = CountBundleRequestsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class CountExhibitedItemCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = 100
        val subject = CountExhibitedItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { exhibiteditemCount } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_CountExhibitedItemがnullのとき() {
        // arrange
        val data: ItemPf.Response.CountExhibitedItem? = null
        val subject = CountExhibitedItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetViolationsCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<CsPf.Response.ViolationReports>()
        val subject = GetViolationsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { violationReports } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.ExternalServerError::class)
    fun convert_GetViolationsがnullのとき() {
        // arrange
        val data: CsPf.Response.GetViolations? = null
        val subject = GetViolationsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class RegisterViolationCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arange
        val expect = mockk<CsPf.Response.Violation>()
        val subject = RegisterViolationCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { violation } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.NoSuchResourceException::class)
    fun convert_RegisteredViolationがnullのとき() {
        // arrange
        val data: CsPf.Response.RegisteredViolation? = null
        val subject = RegisterViolationCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetItemWithLikesCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Response.Item.StandardItem>()
        val subject = GetItemWithLikesCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { items } returns listOf(expect)
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.ItemNotFoundException::class)
    fun convert_Itemsがnullのとき() {
        // arrange
        val data: ItemPf.Response.Items? = null
        val subject = GetItemWithLikesCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetItemFullCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Response.Item.StandardItem>()
        val subject = GetItemFullCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { items } returns listOf(expect)
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.ItemNotFoundException::class)
    fun convert_Itemsがnullのとき() {
        // arrange
        val data: ItemPf.Response.Items? = null
        val subject = GetItemFullCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class DeleteItemCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val data: Void? = null
        val subject = DeleteItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val expect = true
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }
}

class ReservePurchaseCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Purchase>()
        val subject = ReservePurchaseCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { reservedPurchase } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_ReservePurchaseがnullのとき() {
        // arrange
        val data: ItemPf.Response.ReservePurchase? = null
        val subject = ReservePurchaseCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class CancelReservePurchaseCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val data: Void? = null
        val subject = CancelReservePurchaseCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val expect = true
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }
}

class CompletePurchaseCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemPf.Purchase>()
        val subject = CompletePurchaseCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { registeredPurchase } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_CompletedPurchaseがnullのとき() {
        // arrange
        val data: ItemPf.Response.CompletedPurchase? = null
        val subject = CompletePurchaseCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetDiscountsByItemAndRequesterCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<List<ItemReqPf.Response.Discount>>()
        val subject = GetDiscountsByItemAndRequesterCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { discounts } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.NoSuchResourceException::class)
    fun convert_Discountsがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.Discounts? = null
        val subject = GetDiscountsByItemAndRequesterCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetDiscountsByItemCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<List<ItemReqPf.Response.Discount>>()
        val subject = GetDiscountsByItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { discounts } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.NoSuchResourceException::class)
    fun convert_Discountsがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.Discounts? = null
        val subject = GetDiscountsByItemCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetDiscountByIdCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemReqPf.Response.Discount>()
        val subject = GetDiscountByIdCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { discount } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.NoSuchResourceException::class)
    fun convert_RequestDiscountがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.RequestDiscount? = null
        val subject = GetDiscountByIdCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetMessagesCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<List<ItemReqPf.Response.Message>>()
        val subject = GetMessagesCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { messages } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test
    fun convert_GetMessagesがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.GetMessages? = null
        val expect: List<ItemReqPf.Response.Message?> = listOf()
        val subject = GetMessagesCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }
}

class RequestDiscountCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemReqPf.Response.Discount>()
        val subject = RequestDiscountCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { discount } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_RequestDiscountがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.RequestDiscount? = null
        val subject = RequestDiscountCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class ReplyDiscountCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemReqPf.Response.Discount>()
        val subject = ReplyDiscountCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { discount } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_RequestDiscountがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.RequestDiscount? = null
        val subject = ReplyDiscountCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetMessageCountCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemReqPf.Response.MessageCount>()
        val subject = GetMessageCountCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = expect,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.NoSuchResourceException::class)
    fun convert_MessageCountがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.MessageCount? = null
        val subject = GetMessageCountCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetQuestionsCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<List<ItemReqPf.Response.Question>>()
        val subject = GetQuestionsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { questions } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.NoSuchResourceException::class)
    fun convert_GetQuestionsがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.GetQuestions? = null
        val subject = GetQuestionsCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetPvCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<PvPf.Response.Get.Item>()
        val subject = GetPvCollector(
            CompletableFuture.completedFuture(
                PvPf.Response.Get(
                    items = listOf(expect)
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.NoSuchResourceException::class)
    fun convert_Itemが空のとき() {
        // arrange
        val data: List<PvPf.Response.Get.Item> = listOf()
        val subject = GetPvCollector(
            CompletableFuture.completedFuture(
                PvPf.Response.Get(
                    items = data
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class IncrementPvCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<PvPf.Response.Auctions>()
        val subject = IncrementPvCollector(
            CompletableFuture.completedFuture(
                PvPf.Response.Post(
                    auctions = expect
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }
}

class DeletePvCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<PvPf.Response.Auctions>()
        val subject = DeletePvCollector(
            CompletableFuture.completedFuture(
                PvPf.Response.Delete(
                    auctions = expect
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }
}

class SendQuestionCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemReqPf.Response.Message>()
        val subject = SendQuestionCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = mockk {
                        every { message } returns expect
                    },
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.NoSuchResourceException::class)
    fun convert_GetMessageがnullのとき() {
        // arrange
        val data: ItemReqPf.Response.GetMessage? = null
        val subject = SendQuestionCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try {
            subject.collect().join()
        } catch (e: CompletionException) {
            throw e.cause!!
        }
    }
}

class GetDiscountInfoForBuyerCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val expect = mockk<ItemReqPf.Response.DiscountsInfoForBuyer>()
        val subject = GetDiscountInfoForBuyerCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = expect,
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()
        val actual = subject.value

        // assert
        assertEquals(expect, actual)
    }

    @Test(expected = Failure.UnknownException::class)
    fun convert_GetDiscountがnullのとき() {
        // arrange: when the retrieved data is null
        val data: ItemReqPf.Response.DiscountsInfoForBuyer? = null
        val subject = GetDiscountInfoForBuyerCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = data,
                    errors = null
                )
            )
        )

        // act
        try { subject.collect().join() } catch (e: CompletionException) {
            // assert to throw UnknownException
            throw e.cause!!
        }
    }
}

class GetRegionCollectorTest {
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        // https://ghe.corp.yahoo.co.jp/auction/sparkle-consts/blob/master/src/main/kotlin/jp/co/yahoo/sparkle/consts/item/ItemConsts.kt#L203-L396
        val expect = "1" to "HOKKAIDO"
        val subject = GetRegionCollector(
            CompletableFuture.completedFuture(
                TripPf.Response.RegionResult(
                    resultSet = TripPf.Response.RegionResult.ResultSet(
                        prefectureCode = expect.first,
                        administrativeCode = ""
                    )
                )
            )
        )

        // act
        subject.collect().join()

        // assert
        assertEquals(expect.second, subject.value)
    }

    // when the prefectureCode is not defined in advance, it will throw the UndefinedException,
    // but actually it could throw more specific exception which is defined in https://ghe.corp.yahoo.co.jp/auction/sparkle-consts/blob/master/src/main/kotlin/jp/co/yahoo/sparkle/consts/item/ItemConsts.kt#L409
    @Test(expected = Failure.UndefinedException::class)
    fun convert_fail() {
        // arrange
        val expect = "9999" // this prefectureCode does not exit
        val subject = GetRegionCollector(
            CompletableFuture.completedFuture(
                TripPf.Response.RegionResult(
                    resultSet = TripPf.Response.RegionResult.ResultSet(
                        prefectureCode = expect,
                        administrativeCode = ""
                    )
                )
            )
        )

        // act
        try { subject.collect().join() } catch (e: CompletionException) {
            // assert to throw UnknownException
            throw e.cause!!
        }
    }

    // when the prefectureCode is null, the collector will return KAIGAI
    @Test
    fun convert_prefectureCode_null() {
        // arrange
        val expect = "KAIGAI"
        val subject = GetRegionCollector(
            CompletableFuture.completedFuture(
                TripPf.Response.RegionResult(
                    resultSet = TripPf.Response.RegionResult.ResultSet(
                        prefectureCode = "CANNOT_TO_INT", // this string cannot be converted into int
                        administrativeCode = ""
                    )
                )
            )
        )

        // act
        subject.collect().join()

        // assert
        assertEquals(expect, subject.value)
    }
}

class RegisterContactMessageCollectorTest {
    // when the response contains non-null sentDateTime, assert the collector would return true
    @Test
    fun convert_正しくコンバートされる() {
        // arrange
        val subject = RegisterContactMessageCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = CsPf.Response.ContactMessageProperty(
                        registerContactMessageProperty = CsPf.Response.RegisterContactMessageProperty(
                            sentDateTime = 1546300800000L
                        )
                    ),
                    errors = null
                )
            )
        )

        // act
        subject.collect().join()

        // assert
        assertTrue(subject.value)
    }

    // when the response data is null, collector will throw the exception
    @Test(expected = Failure.RegisterContactFailed::class)
    fun convert_fail() {
        // arrange
        val nullDate: CsPf.Response.ContactMessageProperty? = null
        val subject = RegisterContactMessageCollector(
            CompletableFuture.completedFuture(
                GraphQLResponse(
                    data = nullDate,
                    errors = null
                )
            )
        )

        // act
        try { subject.collect().join() } catch (e: CompletionException) {
            // assert to throw UnknownException
            throw e.cause!!
        }
    }
}

class DetermineNgKeywordsCollectorTest {
    // when the result is SPAM type, collector will return true
    @Test
    fun success_contain_NgWord() {
        // arrange
        val subject = DetermineNgKeywordsCollector(
            CompletableFuture.completedFuture(
                Astrea.Response.Success(
                    result = Astrea.Response.ResultType.SPAM
                )
            )
        )

        // act
        subject.collect().join()

        // assert
        assertTrue(subject.value)
    }

    // when the result is CLEAN type, collector will return false
    @Test
    fun success_does_not_contain_NgWord() {
        // arrange
        val subject = DetermineNgKeywordsCollector(
            CompletableFuture.completedFuture(
                Astrea.Response.Success(
                    result = Astrea.Response.ResultType.CLEAN
                )
            )
        )

        // act
        subject.collect().join()

        // assert
        assertFalse(subject.value)
    }
}

class GraphQLResponseConvertTest() {

    // when the response contains error, the convert method will throw ExternalServerError
    @Test(expected = Failure.ExternalServerError::class)
    fun contain_errors() {
        // arrange
        val platform = mockk<Platform>()
        val response = mockk<GraphQLResponse<Any>>()
        val failure = mockk<Failure.ExternalServerError>()
        every { response.getFailure(any()) } returns failure

        // act
        response.convert(platform) {}

        // assert to throw ExternalServerError
    }
}