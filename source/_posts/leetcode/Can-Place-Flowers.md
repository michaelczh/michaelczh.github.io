---
title: Can Place Flowers
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-23 21:53:49
---

#### Answer
```c
class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int k) {
        
        int n = flowerbed.size();
        int res = 0;
        
        for(int i = 0; i < n && res < k; ++i) {
            int next   = (i == n-1) ? 0 : flowerbed[i+1];
            int before = (i == 0)   ? 0  :flowerbed[i-1];
            if (next == 0 && before == 0 && flowerbed[i] == 0) {
                res++;
                flowerbed[i] = 1;
            }
            
        }
        return res == k;
    }
};
```
