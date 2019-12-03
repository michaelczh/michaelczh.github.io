---
title: 1064. Fixed Point
tags:
  - Leetcode
  - Array
  - Binary Search
categories:
  - Leetcode Easy
  - Array
date: 2019-11-16 23:57:24
---

#### Answer 1
第一种方法应该是最好想出来的，就是遍历数组然后找出`i`和`A[i]`相同的值，就是题目要求的最小的值。
复杂度是`O(N)`
```c
class Solution {
public:
    int fixedPoint(vector<int>& A) {
        
        for(int i = 0; i < A.size(); ++i) {
            if (i == A[i]) return i;
        }
        
        return -1;
    }
};

```


#### Answer 2
第二种方法是寻求`O(logN)`的二分查找法。
`A[i] < A[i+1]`
`A[i] <= A[i+1] - 1`
`A[i] - i <= A[i+1] - i - 1`
即 `A[i] - i <= A[i+1] - (i +1)`
因此，题目可以变为查找`A[i]-i`中第一个数值为0的序列。
```c
class Solution {
public:
    int fixedPoint(vector<int>& A) {
        int i = 0, j = A.size()-1;
        
        while(i < j) {
            int mid = i + (j - i) / 2;
            
            // 假如小于0， 那么目标一定是在右边 因此左边的一定是比0小的（右边有可能比0大的｜等于0的）。
            if (A[mid] - mid < 0) 
                i = mid + 1;
            else j = mid;
        }
        
        return A[i] == i ? i : -1;
    }
};

```


#### 思考
这题的二分查找一个关键的思想在于，
通过设定条件来判断答案是在左边，还是在非左边。

假设题目变为查找最大或者最后一个值那么代码应该变为
```c
class Solution {
public:
    int fixedPoint(vector<int>& A) {
        int i = 0, j = A.size()-1;
        
        while(i < j) {
            int mid = i + (j - i) / 2;
            
            // 假如大于0， 那么目标一定是在左边
            if (A[mid] - mid > 0) 
                j = mid - 1;
            else i = mid;
        }
        
        return A[i] == i ? i : -1;
    }
};

```

是这样想的，但是无法实现会造成死循环
