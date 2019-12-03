---
title: 1002. Find Common Characters
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-22 22:56:50
---

#### Answer
很多有字母相关的题目都可以塑造一个26长度的`array`来完成。
```c
class Solution {
public:
    vector<string> commonChars(vector<string>& A) {
        
        vector<int> cnt(26, INT_MAX);
        
        for(auto& word: A) {
            vector<int> cnt0(26, 0);
            for(auto& c : word) cnt0[c - 'a']++;
            for(int i = 0; i < 26; ++i) cnt[i] = min(cnt[i], cnt0[i]);
        }
        
        vector<string> res;
        for(int i = 0; i < 26; ++i) {
            for(int j = 0; j < cnt[i]; ++j) res.push_back(string(1, 'a' + i)); 
        }
        
        return res;
    }
};
```
