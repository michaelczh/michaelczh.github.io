---
title: Find Pivot Index
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-15 22:56:42
---

#### Answer 1
很自然能想到的一种方法。
```c
class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        
        int left = 0, right = 0;
        for(int num: nums) right += num;
        
        for(int i = 0; i < nums.size(); ++i) {
            
            if (i > 0) left += nums[i-1];
            right -= nums[i];
            
            if (left == right) return i;
        }
        
        return -1;
    }
};
```

#### Answer 2
参考discuss的答案。感觉用`while`比`for`更好
```c
class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        
        int total = 0;
        for(int num: nums) total += num;
        
        int i = 0, currSum = 0;
        while( i < nums.size()) {
            if (currSum * 2 == total - nums[i]) return i;
            currSum += nums[i++];
        }
        
        return -1;
    }
};
```