---
title: Longest Continuous Increasing Subsequence
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-14 21:32:01
---

#### Answer 1
```c
class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        
        if (nums.size() == 0) return 0;
        
        // dp[i] -> [0,i] subarray endwith nums[i]
        vector<int> dp(nums.size(), 1);
        int res = 1;
        
        for(int i = 1; i< nums.size(); ++i) {
            
            if (nums[i] > nums[i-1]) dp[i] = dp[i-1] + 1;
            else dp[i] = 1;
            
            // cout << dp[i] << endl;
        
            res = max(res, dp[i]);
        }
        
        return res;
    }
};
```

#### Answer 2
```c
class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        int n = nums.size();
        int cnt = 0;
        int res = 0;
        
        for (int i = 0; i < n; ++i) {
            if (i == 0 || nums[i] > nums[i-1]) res = max(res, ++cnt);
            else cnt = 1;
        }
        return res;
    }
};
```