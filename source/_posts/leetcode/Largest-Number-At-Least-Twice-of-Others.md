---
title: Largest Number At Least Twice of Others
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-19 16:08:40
---

#### Answer
很无聊的一道题目
```c
class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        
        int maxNum = INT_MIN;
        int index = -1;
        
        for(int i = 0; i < nums.size(); ++i) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
                index = i;
            }
        }
        
        for(int i = 0; i < nums.size(); ++i) {
            if (i == index) continue;
            if (maxNum < nums[i]*2) return -1;
        }
        
        return index;
    }
};
```
