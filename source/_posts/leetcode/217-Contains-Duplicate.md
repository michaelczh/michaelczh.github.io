---
title: 217. Contains Duplicate
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-20 21:28:39
---

#### Answer
时间复杂度O(N)，但是空间复杂度也是O(N)
似乎没有空间复杂度O(1)的方法
```c
class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        
        unordered_set<int> set(nums.begin(), nums.end());
        
        return set.size() != nums.size();
    }
};
```
