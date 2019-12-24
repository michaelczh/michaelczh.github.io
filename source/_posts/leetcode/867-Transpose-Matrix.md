---
title: 867. Transpose Matrix
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-28 22:56:45
---

#### Answer
傻瓜题目
```c
// 复杂度 O(N^2)
class Solution {
public:
    vector<vector<int>> transpose(vector<vector<int>>& A) {
        
        int n = A.size(), m = A[0].size();
        
        vector<vector<int>> res(m, vector<int>(n,0));
        
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                res[j][i] = A[i][j];
            }
        }
        
        return res;
    }
};
```
