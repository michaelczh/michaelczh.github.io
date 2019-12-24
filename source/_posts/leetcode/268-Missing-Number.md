---
title: 268. Missing Number
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-24 22:30:23
---

#### Answer 1
```c
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        
        int tmp = 0;
        for(int i = 0; i < nums.size(); ++i) {
            tmp += nums[i] - i;
            
        }
        
        return nums.size() - tmp;
    }
};

```


#### Answer 2
```c
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        
        int res = nums.size();
        for(int i = 0; i < nums.size(); ++i) {
            res = res ^ i;
            res = res ^ nums[i];
        }
        
        return res;
    }
};

```
