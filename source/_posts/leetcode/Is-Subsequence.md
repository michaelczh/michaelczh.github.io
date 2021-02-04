---
title: Is Subsequence
tags:
  - Leetcode
  - DP
categories:
  - Leetcode Easy
  - DP
date: 2020-02-16 17:04:28
---

#### Answer
也不知道用DP是什么意思。
```c
class Solution {
public:
    bool isSubsequence(string s, string t) {
        if (s.size() == 0 && t.size() == 0) return true;
        
        int n = s.size();
        int j = 0;
        for(int i = 0; i < t.size(); ++i) {
            if (t[i] == s[j]) ++j;
            if (j == n) return true;
        }
        
        return false;
    }
};
```
