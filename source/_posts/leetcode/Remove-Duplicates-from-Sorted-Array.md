---
title: Remove Duplicates from Sorted Array
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-15 22:46:56
---

#### Answer
```c
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        
        int n = nums.size();
        int cnt = 0;
        for(int i = 1; i< n; ++i) {
            if (nums[i] == nums[i-1]) ++cnt;
            else nums[i-cnt] = nums[i];  // 相当于offset,往前推几位 
            
        }
        
        return n - cnt;
    }
};
```
