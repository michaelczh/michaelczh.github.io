---
title: Check if Array Is Sorted and Rotated
tags:
  - Leetcode Easy
  - Array
categories:
  - Leetcode
date: 2021-02-07 16:36:32
---

#### Description

[Link](https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/)


#### Answer
题目要求判断一个数列是否可以向右移动几位后是否可以变为全升序的。
例如有一串数列 `a1 < a2 < a3 < a4 > b1 < b2 < b3 < b4`，在b1的时候，是唯一一次不满足后面大于前面的。
也就是说，如果该数列能移动几次就满足全升序的话，必须满足，前面比后面大出现的次数最多一次。
由于最后一个数和最开始一个数是相连接的，因此我们还得判断最后一个数的第一个数的关系。

```c
class Solution {
public:
    bool check(vector<int>& nums) {
        int n = nums.size();
        int found = 0;
        for(int i = 0; i < n; ++i) {
            if (nums[i] < nums[(i-1+n)%n]) {
                found++;
            }
            if (found > 1) {
                return false;
            }
        }
        return true;
    }
};
```
