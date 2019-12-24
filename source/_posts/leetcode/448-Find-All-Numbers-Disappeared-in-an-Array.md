---
title: 448. Find All Numbers Disappeared in an Array
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-19 23:18:30
---

#### 思路
因为题目说只会出现一次或者两次，
所以思路就是遍历一遍每个数字，对每个数字作为index-1（因为从1开始）,然后翻转那个`nums[index]`
于是没有出现的index是不会被翻转的，所以之后便利一遍找到大于0的也就是没有被翻转的, 那个`i+1`，就是答案之一

#### Answer

```c
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        
        for(int i = 0; i < nums.size(); ++i) {
            int index = abs(nums[i]) - 1;  // 因为nums[i] 有可能是被翻转成负数的了
            if (nums[index] > 0) nums[index] = -nums[index];
        }
        
        vector<int> res;
        
        for(int i = 0; i < nums.size(); ++i) {
            if (nums[i] > 0) res.push_back(i + 1);
        }
        
        return res;
    }
};
```
