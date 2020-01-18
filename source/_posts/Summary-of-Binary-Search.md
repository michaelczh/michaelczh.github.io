---
title: Summary of Binary Search
date: 2020-01-18 17:57:59
tags: binary search
categories: algorithm
---


#### Summary
```
/**
 * 左闭右开的二分查找, 在[left, right) 中间寻找
 */
fun commonBinarySearch(list:List<Int>, target:Int):Int {

    var left = 0
    var right = list.size

    while (left < right) {
        val mid = left + (right-left)/2
        if (list[mid] < target) left = mid+1
        else right = mid
    }

    return left
}

/**
 * 寻找左边界的二分查找
 */

fun leftBoundBinarySearch(list:List<Int>, target:Int):Int {

    var left = 0
    var right = list.size

    while (left < right) {
        val mid = left + (right-left)/2
        if (list[mid] == target) right = mid // 收紧右边界
        else if (list[mid] < target) left = mid+1
        else right = mid
    }

    // target 比所有的数都大  返回-1
    if (left == list.size) return -1

    // 因为left有可能被遗漏
    return if (list[left] == target) left else -1
}

/**
 * 寻找右边界的二分查找
 */
fun rightBoundBinarySearch(list:List<Int>, target:Int):Int {

    var left = 0
    var right = list.size

    while (left < right) {
        val mid = left + (right-left)/2
        if (list[mid] == target) left = mid+1 // 收紧左边界
        else if (list[mid] < target) left = mid+1
        else right = mid
    }

    // target 比所有的数都小  返回-1
    if (left == 0) return -1

    // 因为left更新的时候，需要加1，所以，最后返回下标的时候，就需要减1。
    return if (list[left-1] == target) left-1 else -1
}
```


#### 参考
1. https://www.zhihu.com/question/36132386
2. https://blog.csdn.net/IronWring_Fly/article/details/100548061