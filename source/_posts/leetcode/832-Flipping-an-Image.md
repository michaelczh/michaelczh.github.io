---
title: 832. Flipping an Image
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-12 23:53:24
---

#### Answer
```c
// 复杂度: O(NM) N,M分别为行列数
class Solution {
public:
    vector<vector<int>> flipAndInvertImage(vector<vector<int>>& A) {
        
        int n = A.size();
        int m = A[0].size();
        
        for(int i = 0; i < n; ++i) {
            vector<int> tmpRow = A[i];
            for(int j = 0; j < (m+1)/2; ++j) {
                swap(A[i][j], A[i][m-j-1]);
            }
        }
        
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) 
                A[i][j] = 1 - A[i][j];
        }
        return A;
    }
};
```
