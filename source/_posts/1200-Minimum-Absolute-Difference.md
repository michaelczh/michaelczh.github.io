---
title: 1200. Minimum Absolute Difference
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-22 22:13:28
---

#### Answer
看完觉得比较简单。但是感觉没什么挑战。又是亚麻的题目O.O
```c
// 复杂度 O(NlogN)
class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        vector<vector<int>> res;
        sort(arr.begin(), arr.end());
        
        // find the minimum value
        int minDist = INT_MAX;
        for(int i = 0; i < arr.size()-1; ++i) {
            minDist = min(minDist, arr[i+1] - arr[i]);
        }
        
        // return res
        for (int i = 0; i < arr.size()-1; ++i) {
            if (arr[i+1] - arr[i] == minDist)
                res.push_back(vector<int>{arr[i], arr[i+1]});
        }
        
        return res;
    }
};
```
