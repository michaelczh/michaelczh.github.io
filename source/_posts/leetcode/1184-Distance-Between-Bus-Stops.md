---
title: 1184. Distance Between Bus Stops
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-17 23:11:46
---

#### Answer
很普通的一道题目，画个图就知道了
```c
class Solution {
public:
    int distanceBetweenBusStops(vector<int>& distance, int start, int destination) {
        
        int small = min(start, destination);
        int large = max(start, destination);
        int case1 = 0;
        for (int i = 0; i < distance.size(); ++i) {
            if (i >= small && i < large) continue;
            case1 += distance[i];
        }
        
        int case2 = 0;
        for(int i = small; i < large; ++i) case1 += distance[i];
        
        return min(case1, case2);
    }
};
```
