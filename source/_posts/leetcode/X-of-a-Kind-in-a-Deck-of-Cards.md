---
title: X of a Kind in a Deck of Cards
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-22 20:34:19
---

#### Answer
```c
class Solution {
public:
    bool hasGroupsSizeX(vector<int>& deck) {

    	// number, times
    	unordered_map<int,int> record;

    	for(auto& num: deck) record[num]++;

    	int minTimes = INT_MAX;
        
        for(auto& it: record) minTimes = min(minTimes, it.second);
    	if (minTimes < 2) return false;

    	for(int i = 2; i <= minTimes; ++i) {

    		bool pass = true;
    		for(auto& it: record) {
    			if (it.second % i !=0 ) {
    				pass = false;
    				break;
    			}
    		}

    		if (pass) return true;
    	}

    	return false;
        
    }
};
```

https://www.cnblogs.com/grandyang/p/11575613.html
