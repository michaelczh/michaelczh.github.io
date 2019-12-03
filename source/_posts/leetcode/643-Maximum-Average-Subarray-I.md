---
title: 643. Maximum Average Subarray I
tags:
  - Leetcode
categories:
  - Leetcode Easy
date: 2019-07-09 00:05:17
---

#### Problem
Given an array consisting of n integers, find the contiguous subarray of given length `k` that has the maximum average value. And you need to output the maximum average value.

**Note:**


#### Example
> **Input**: [1,12,-5,-6,50,3], k = 4
**Output**: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
**Explanation**: 12.75


#### Explanation
很简单的一个问题，刚开始没看到开，以为是求子序列的最大平均数。后来发现给定了`k`，就简单了点
就是维持一个长度为`k`的窗口，从左到右边扫一扫。保存最大的就好很简单

#### Answer
```c
class Solution {
public:
    double findMaxAverage(vector<int>& nums, int k) {
        int res = INT_MIN;
        int tmp = 0;
        for (int i = 0; i < k; ++i) tmp += nums[i];
        for (int i = k; i < nums.size(); ++i) {
            res = max(res, tmp);
            tmp += nums[i] - nums[i-k];
        }
        return (double)max(tmp,res)/k;
    }
};
````
