---
title: 485. Max Consecutive Ones
tags:
  - Leetcode
categories:
  - Leetcode Easy
date: 2019-06-24 23:41:48
---

#### Problem

Given a binary array, find the maximum number of consecutive 1s in this array.


#### Example
> **Input**: [1,1,0,1,1,1]
**Output**: 3
**Explanation**:The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.


#### Explanation
好简单而且无聊的一道问题。就贴个答案吧

#### Answer
```c
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int res = 0;
        int tmp = 0;
        for(int&n : nums){
            tmp += n;
            tmp = tmp*n;
            res = max(res, tmp);
        }
        return res;
    }
};
````


