---
title: 561. Array Partition I
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-17 22:54:09
---

#### Answer
意外简单的一道题目。
题目是说有2N个数字，需要组成N对数字，要求N对里面最小的数字加起来最大。
所以应该是小的尽量要跟小的组一起，这样不会浪费大的数字。
排序一下然后挑选各自最小的就ok了
```c
// 时间复杂度O(NlogN)
class Solution {
public:
    int arrayPairSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int res = 0;
        for(int i = 0; i < nums.size(); i = i + 2) {
            res += nums[i];
        }
        
        return res;
    }
};
```
