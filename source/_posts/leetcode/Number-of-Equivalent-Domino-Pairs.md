---
title: Number of Equivalent Domino Pairs
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-09 22:27:20
---

#### Answer
用了一个`index`来识别是否是相同的domino

```c
class Solution {
public:
    int numEquivDominoPairs(vector<vector<int>>& dominoes) {
        int res = 0;
        unordered_map<int, int> count;
        
        for(auto& domino: dominoes) {
            int index = max(domino[0], domino[1]) * 10 + min(domino[0], domino[1]);
            count[index]++;
        }
        
        for(auto& it: count) {
            res += it.second * (it.second - 1) / 2;
        }
        
        return res;
    }
};
```
