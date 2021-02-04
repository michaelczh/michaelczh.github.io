---
title: House Robber II
tags:
  - Leetcode
  - DP
categories:
  - Leetcode Medium
  - DP
date: 2020-05-30 17:58:21
---

#### Explanation
主要是dp的定义。
因为不能同时偷取第一个和最后一个房子所以我们可以定义两个`dp`
第一个`dp`是代表从第一个屋子开始偷
第二个`dp`是代表从第二个屋子开始偷。

`DP`的定义为

> dp[i]: 偷取前`i`个屋子(`nums[0,i)`)的最大效益
>
> dp[i] = max ( dp[i-1], dp[i-2] + nums[i-1] ) // 比较 偷取当前屋子和不偷取当前屋子的最大效益

在最近比较的时候，对于从第一个屋开始偷的，我们比较`n-1`的结果。和从第二个屋子开始偷的，我们计算到最后一个屋子就行。


#### Answer
```c
class Solution {
public:
    int rob(vector<int>& nums) {
        
        int n = nums.size();
        
        if (n == 0) return 0;
        if ( n == 1 ) return nums[0]; 
        
        vector<int> dpFirst(n+1, 0);  // d[i] represents rob [0,i) houses
        vector<int> dpSecond(n+1, 0);
        
        
        dpFirst[0] = 0;
        dpFirst[1] = nums[0];
        dpSecond[0] = 0;
        dpSecond[1] = 0;
        
        
        for(int i = 2; i <= n; ++i) {
            
            dpFirst[i] = max(dpFirst[i-1], dpFirst[i-2] + nums[i-1]);
            dpSecond[i] = max(dpSecond[i-1], dpSecond[i-2] + nums[i-1]);
        }
        
        return max(dpFirst[n-1], dpSecond[n]);
        
    }
};
```

