---
title: 243. Shortest Word Distance
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-06 23:55:23
---

#### Answer
```c
class Solution {
public:
    int shortestDistance(vector<string>& words, string word1, string word2) {
        int word1Index = -1;
        int word2Index = -1;
        int res = INT_MAX;
        for (int i = 0; i < words.size(); ++i) {
        	if (words[i] == word1) word1Index = i;
        	if (words[i] == word2) word2Index = i;
        	
        	if (word1Index != -1 && word2Index != -1) {
        		res = min(res, abs(word1Index-word2Index));
        	}
        }

        return res;
    }
};
```
