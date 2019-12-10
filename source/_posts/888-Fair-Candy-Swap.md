---
title: 888. Fair Candy Swap
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-10 13:23:05
---

#### Answer
```c
//    sumA - cA[i] + cB[j] = sumB - cB[j] + cA[i]
// => cA[i] - cB[j] = (sumA - sumB)/2  
class Solution {
public:
    vector<int> fairCandySwap(vector<int>& A, vector<int>& B) {
        unordered_set<int> setA;
        unordered_set<int> setB;
        int sumA = 0, sumB;
        for(auto& n : A) {
        	setA.insert(n);
        	sumA += n;
        } 
        for(auto& n : B) {
        	setB.insert(n);
        	sumB += n;
        } 


        for(int value : setA) {
        	int target = 2*value + sumB - sumA;
        	if (setB.count(target) != 0) return vector<int>{value, target};
        }

        return vector<int>{0, 0};

    }
};

```
