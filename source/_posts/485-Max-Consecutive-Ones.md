---
title: 485. Max Consecutive Ones
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-13 09:49:32
---

#### Description

[485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)

#### Answer
很简单的一道题目了
```c
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        
        int curr = 0;
        int res = 0;
        for(auto& n: nums) {
            curr = ( curr + n ) * n;
            res = max(curr, res);
        }
        
        return res;
    }
};
```