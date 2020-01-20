---
title: Contains Duplicate II
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-20 23:38:42
---

#### Answer 1
用map来做
```c
class Solution {
public:
    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        
        unordered_map<int,int> last;
        
        for(int i = 0; i < nums.size(); ++i) {
            
            if (last.count(nums[i])) {
                if ((i - last[nums[i]]) <= k) return true;
            }
            
            last[nums[i]] = i;
        }
        
        return false;
    }
};
```

#### Answer 2
第二种方法是保持一个滑动窗口，用set来做
```c
class Solution {
public:
    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        
        unordered_set<int> wd;
        
        for(int i = 0; i < nums.size(); ++i) {
            if (i > k) wd.erase(nums[i-k-1]);
            if (wd.count(nums[i])) return true;
            wd.insert(nums[i]);
        }
        
        return false;
    }
};
```