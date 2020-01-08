---
title: '[Array][Easy] Check If It Is a Straight Line'
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-01 17:28:38
---

#### Answer
为了避免除以0，所以用乘法
```c
class Solution {
public:
    bool checkStraightLine(vector<vector<int>>& cord) {
        
        
        for(int i = 0; i < cord.size()-2; ++i) {
            
            int k1 = (cord[i+1][0] - cord[i][0]) * (cord[i+2][1] - cord[i+1][1]);
            int k2 = (cord[i+1][1] - cord[i][1]) * (cord[i+2][0] - cord[i+1][0]);
            
            if (k1 != k2) return false;
        }
        
        return true;
    }
};


```
