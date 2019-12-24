---
title: 896. Monotonic Array
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-13 09:40:53
---

#### Answer 1
最简单的方法就是两种各判断一遍，复杂度O(N)
```c
class Solution {
public:
    bool isMonotonic(vector<int>& A) {
        return increasing(A) || decreasing(A);
    }
    
    bool increasing(vector<int>& A) {
        for(int i = 1; i < A.size(); ++i) {
            if (A[i] < A[i-1]) return false;
        }
        return true;
    }
    
    bool decreasing(vector<int>& A) {
        for(int i = 1; i < A.size(); ++i) {
            if (A[i] > A[i-1]) return false;
        }
        return true;
    }
};
```

#### Answer 2
更简洁的方法
```c
class Solution {
public:
    bool isMonotonic(vector<int>& A) {
        int incFlag = true, decFlag = true;
        for(int i = 1; i < A.size(); ++i) {
            incFlag &= A[i] >= A[i-1];
            decFlag &= A[i] <= A[i-1];
        }
        return incFlag || decFlag;
    }
};
```
