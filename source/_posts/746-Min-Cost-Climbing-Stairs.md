---
title: 746. Min Cost Climbing Stairs
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-26 22:05:06
---

#### Answer 1
DP
```c
class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int n = cost.size();
        for(int i = 2; i < n; ++i) {
            cost[i] += min(cost[i-2], cost[i-1]);
        }
        
        return min(cost[n-1], cost[n-2]);
    }
};
```


#### Answer 2
慢很多
```c
class Solution {
public:
    unordered_map<int,int> memo;
    int minCostClimbingStairs(vector<int>& cost) {
        return min(helper(cost, 0), helper(cost,1));
    }
    
    int helper(vector<int>&cost, int i) {
        if(i == cost.size() - 1 || i == cost.size() - 2) return cost[i];
        if (memo.count(i+1) == 0) memo[i+1] = helper(cost, i+1);
        if (memo.count(i+2) == 0) memo[i+2] = helper(cost, i+2);
        return cost[i] + min(memo[i+1], memo[i+2]);
    }
};
```