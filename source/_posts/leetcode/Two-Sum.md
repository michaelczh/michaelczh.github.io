---
title: Two Sum
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-14 21:50:08
---

#### Answer
很经典的一道题目了，这边用`hashmap`做。也可以用双指针来做。这样不用耗费额外的空间复杂度且时间复杂度也是O(n)
```c
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        
        unordered_map<int,int> map;
        
        for(int i = 0; i < nums.size(); ++i) {
            map[nums[i]] = i;
        }
        
        for(int i = 0; i < nums.size(); ++i) {
            int found = target - nums[i];
            if (map.count(found) != 0 && map[found] != i) return vector<int>{i, map[found]};
        }
    
        return vector<int>{-1,-1};
    }
};
```
