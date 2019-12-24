---
title: 766. Toeplitz Matrix
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-29 23:16:19
---

#### Answer 1

```c
// 复杂度O(NM)
class Solution {
public:
    bool isToeplitzMatrix(vector<vector<int>>& matrix) {
        value = matrix[0][0];
        
        for(int i = 0; i < matrix.size(); ++i) {
            int common = matrix[i][0];
            if (!helper(matrix, i+1, 1, common)) return false;
        }
        
        for(int j = 0; j < matrix[0].size(); ++j) {
            int common = matrix[0][j];
            if (!helper(matrix, 1, j+1, common)) return false;
        }
        
        return true;
        
    }
    
    bool helper(vector<vector<int>>& matrix, int i, int j, int& common) {
        if (i >= matrix.size() || j >= matrix[0].size()) return true;
        return (matrix[i][j] == common) && helper(matrix, i+1, j+1, common);
    }
};
```


#### Answer 2
第二种方法更简单，是在论坛找到的。很简洁
```c
// 复杂度O(NM)
class Solution {
public:
    bool isToeplitzMatrix(vector<vector<int>>& matrix) {

        int m = matrix.size(), n = matrix[0].size();
        
        for(int i = 0; i < m-1; ++i) {
            for(int j = 0; j < n-1; ++j) {
                if (matrix[i][j] != matrix[i+1][j+1]) return false;
            }
        }
        
        return true;
        
    }
};
```
