---
title: 1013. Partition Array Into Three Parts With Equal Sum
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-12 14:37:45
---

#### Answer
```c
// O(n)
class Solution {
public:
    bool canThreePartsEqualSum(vector<int>& A) {
    	int average = 0;
    	for(auto& n : A) average += n;
    	average /= 3; 

    	int cnt = 0;
    	int curr = 0;


        for (int i = 0; i < A.size(); ++i)
        {
        	curr += A[i];
        	if(curr != average) continue; 
        	else if(++cnt == 3) return true;
        	curr = 0;
        }

        return false;
    }
};
```
