---
title: 169. Majority Element
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-18 22:44:35
---

#### Answer 1
第一种方法很简单，很容易想到。
时间复杂度是O(N)，但是空间复杂度也是O(N)
```c
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int n = nums.size();
        
        unordered_map<int,int> map;
        
        for(auto& n: nums) map[n]++;
        
        for(auto& it: map) {
            if (it.second > n/2) return it.first;
        }
        
        return 0;
    }
};
```

#### Answer 2
在论坛还有一种很聪明的方法.空间复杂度是O(N),
当前遍历的值等于目前的major的话就加1，否则就减1，
对于数列在[0...i] 里面寻找major,
那么在什么情况下就知道目前的major不是真的major了呢，就是count减到0的时候。因为题目是major是> n/2的
```c
class Solution {
public:
    int majorityElement(vector<int>& nums) {

        int major = nums[0], count = 1;
        
        for(int i = 1; i < nums.size(); ++i) {
            
            if (count == 0) {
                count++;
                major = nums[i];
            }else if (nums[i] == major) count++;
            else count--;
        }
        
        return major;
    }
};
```

