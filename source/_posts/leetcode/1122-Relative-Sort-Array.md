---
title: 1122. Relative Sort Array
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-20 23:36:58
---

#### Answer
```c
class Solution {
public:
    vector<int> relativeSortArray(vector<int>& arr1, vector<int>& arr2) {
        
        unordered_set<int> arr2Set(arr2.begin(), arr2.end());
        
        vector<int> notExist;
        unordered_map<int, int> times;
        for(auto& num : arr1) {
            times[num]++;
            if (arr2Set.count(num) == 0) notExist.push_back(num);
        }
        sort(notExist.begin(), notExist.end());
        
        vector<int> res;
        for(auto& num: arr2){
            while(times[num]-- > 0) res.push_back(num);
        }
        
        for(auto& num: notExist) res.push_back(num);
        
        return res;
    }
};
```
