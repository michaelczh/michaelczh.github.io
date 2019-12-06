---
title: 566. Reshape the Matrix
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-07 00:02:40
---

#### Answer
```c
class Solution {
public:
    vector<vector<int>> matrixReshape(vector<vector<int>>& nums, int r, int c) {
        int n = nums.size(), m = nums[0].size();
        
        if (n*m != r*c) return nums;
        vector<vector<int>> res(r, vector<int>(c,0));
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                int index = i * m + j;
                res[index/c][index%c] = nums[i][j];
            }
        }
        
        return res;
    }
};
```
