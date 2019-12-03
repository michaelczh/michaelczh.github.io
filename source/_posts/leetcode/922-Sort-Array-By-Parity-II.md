---
title: 922. Sort Array By Parity II
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-20 00:45:58
---

#### Answer
两个指针。不过没想到哦
```c
class Solution {
public:
    vector<int> sortArrayByParityII(vector<int>& A) {
        
        int odd = 0, even = 1, n = A.size();
        
        while( odd< n && even < n) {
            while( odd  <n && A[odd] %2 == 0) odd +=2;
            while( even <n && A[even]%2 == 1) even+=2;
            if (odd< n && even < n) swap(A[odd], A[even]);
        }
        return A;
    }
};
```
