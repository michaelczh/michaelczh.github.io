---
title: 905. Sort Array By Parity
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-13 23:54:02
---

#### Answer
```c
// 复杂度O(N)
// in place
class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& A) {
        
        for(int i = 0, j = 0; j < A.size(); ++j) {
            if (A[j] % 2 == 0) swap(A[j], A[i++]);
        }
        
        return A;
    }
};
```
