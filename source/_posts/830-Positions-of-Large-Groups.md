---
title: 830. Positions of Large Groups
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-27 17:13:30
---

#### Answer
```c
class Solution {
public:
    vector<vector<int>> largeGroupPositions(string S) {
    	vector<vector<int>> res;
        
        for(int i = 0, j = 0; i < S.size(); i = j) {

        	while(S[i] == S[j]) j++;
        	if (j - i >= 3)
        		res.push_back(vector<int>{i, j-1});
        }

        return res;
        
    }
};
```
