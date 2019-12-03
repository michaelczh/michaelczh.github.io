---
title: 1160. Find Words That Can Be Formed by Characters
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-20 23:11:38
---

#### Answer
比较简单一道题，但是写的没有很爽。因为感觉逻辑有点混乱
```c
class Solution {
public:
    int countCharacters(vector<string>& words, string chars) {
        // restore
        vector<int> record(26,0);
        for(auto& c: chars) record[c - 'a']++;
        
        int res = 0;
        for (auto& word : words) {
            int len = 0;
            vector<int> tmp = record;
            while(len < word.size() && tmp[word[len] - 'a'] > 0) {
                tmp[word[len++] - 'a']--;
            }
            if (len == word.length()) res+= len;
        }
        
        return res;
    }
};
```
