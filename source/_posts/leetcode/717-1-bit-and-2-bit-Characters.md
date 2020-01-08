---
title: 717. 1-bit and 2-bit Characters
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-26 00:19:53
---

#### Answer
```c
class Solution {
public:
    bool isOneBitCharacter(vector<int>& bits) {
        
        int cnt = 0;
        for(int i = bits.size()-2; i >= 0; --i) {
            if (bits[i] == 1) ++cnt;
            else break;
        }
        
        return cnt % 2 == 0;
    }
};
```
