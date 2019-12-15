---
title: 283. Move Zeroes
tags:
  - Leetcode
  - Array 
categories:
  - Leetcode Easy
  - Array
date: 2019-12-15 18:19:24
---

#### Answer
复杂度O(n)
```c
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int i = 0, j = 0;
        
        for(int i = 0; i < nums.size(); ++i) {
            // 有多少个非0  这个就会被执行多少次
            if (nums[i] != 0) nums[j++] = nums[i]; 
        }
        
        // 最后再补齐0
        for(; j < nums.size(); ++j) nums[j] = 0;
        
        return nums
        
    }
};
```
