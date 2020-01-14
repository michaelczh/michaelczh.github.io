---
title: Maximum Subarray
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-13 21:43:52
---

#### Answer
dp[i]的定义是 nums[0,i]的最大子数列。
那么dp[i+1] = (dp[0,i] > 0 ? dp[i] : 0) + nums[i+1]
因为如果前面的子数列为负数的话，一定无法是答案，因为负数加上任何数一定是小于本身的。 
```c
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        
        int res = INT_MIN;
        vector<int> dp(nums.size(),0);
        dp[0] = nums[0]; 
        for(int i = 1; i < nums.size(); ++i) {
            
            dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0) ;
            res = max(res, dp[i]);
        }
        
        return res;
    }
};
```
