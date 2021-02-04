---
title: Paint House
tags:
  - Leetcode 
  - DP
categories:
  - Leetcode Easy
  - DP
date: 2020-02-14 23:03:47
---

#### Answer
很简单的一道DP问题了，做DP应该都需要先设定DP的定义吧。
这边的 `dp[i][j]` 的定义是如果第`i`个房子需要刷`j`类型的颜色最少是多少钱。
因为数组可以用一维的所以改成一维的。
```c
class Solution {
public:
    int minCost(vector<vector<int>>& costs) {
        int n = costs.size();
        if (n == 0) return 0;
        vector<int> dp = costs[0];
        
        for(int i = 1; i < n; ++i) {
            
            vector<int> tmp = vector<int>(3,0);
            
            tmp[0] = costs[i][0] + min(dp[1], dp[2]);
            tmp[1] = costs[i][1] + min(dp[0], dp[2]);
            tmp[2] = costs[i][2] + min(dp[0], dp[1]);
            dp = tmp;
        }
        
        return min(min(dp[0],dp[1]), dp[2]);
    }
};
```
