---
title: Maximize Distance to Closest Person
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-16 21:45:16
---

#### Answer
```c
class Solution {
public:
    int maxDistToClosest(vector<int>& seats) {
        
        int n = seats.size();
        int last = -1;
        int res = INT_MIN;
        for(int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                res = last < 0 ? i : max(res, (i - last)/2);
                last = i;
            }
        }
        
        return max(res, n - last - 1);
    }
};

```
