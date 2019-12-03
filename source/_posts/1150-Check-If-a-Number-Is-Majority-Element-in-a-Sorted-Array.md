---
title: 1150. Check If a Number Is Majority Element in a Sorted Array
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-03 20:24:38
---

#### Answer 1
复杂度O(N) 很容易想到的一道题目
```c
// 复杂度O(n)
class Solution {
public:
    bool isMajorityElement(vector<int>& nums, int target) {
        int res = 0;
        for(int& n: nums) res+= n == target ? 1 : 0;
        
        return res > nums.size()/2;
    }
};
```

#### Answer 2
因为有排序，复杂度变为O(logN)
```c
class Solution {
public:
    bool isMajorityElement(vector<int>& nums, int target) {
        
        int res = 0;
        int i = 0, j = nums.size()-1;
        
        while(i <= j) {
            int mid = i + (j-i)/2;
            if (nums[mid] == target) {
                res++;
                int right = mid+1, left = mid-1;
                while(right < nums.size() && nums[right++] == target) res++;
                while(left >=0 && nums[left--] == target)  res++;
                return res > nums.size()/2;
            }
            if (nums[mid] > target) j = mid-1;
            else i = mid+1;
        }
        return false;
        
    }
};
```