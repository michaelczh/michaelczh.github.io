---
title: Pascal's Triangle II
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-11 22:48:44
---

#### Answer
```c
class Solution {
public:
    vector<int> getRow(int rowIndex) {

        vector<int> res(rowIndex+1,0);
        res[0] = 1;
        for(int i = 1; i < rowIndex + 1; ++i) {
        	
        	for(int j = i; j >= 1; --j) {

        		res[j] += res[j-1];
        	}
        }

        return res;
    }
};
```
