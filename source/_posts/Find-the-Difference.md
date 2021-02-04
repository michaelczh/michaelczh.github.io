---
title: Find the Difference
tags:
  - Leetcode
  - Hash Table
  - Bit Manipulation
categories:
  - Leetcode Easy
date: 2021-02-04 21:42:50
---

#### Link

[Link](https://leetcode.com/tag/bit-manipulation/)

#### Answer

这道题目给定了两个字符串，`t`比`s`多一个字符，要求找出这个字符是什么。
自己做的时候用的是`Map`，我们可以把`t`的字符全部存起来，并且储存他们的数量。然后再遍历一遍`s`.
把`Map`里的元素再一一删除，最后剩下的那个便是答案。

当然也可以反过来，储存`s`遍历`t`。假设找不到的话就是目前的答案。
```c
class Solution {
public:
    char findTheDifference(string s, string t) {
        unordered_map<char, int> m;
        for(auto c: t) {
            m[c]++;
        }
        for(auto c: s) {
            if (!m.count(c)) return c;
            m[c]--;
            if (m[c] == 0) {
                m.erase(c);
            }
        }
        
        return m.begin()->first;;
    }
};
```

时间复杂度是`O(N)`，空间复杂度也是`O(N)`。看了论坛里的后发现了一种空间复杂度是`O(1)`的方法。
利用了异或的性质。

```c
class Solution {
public:
    char findTheDifference(string s, string t) {
                
        char res = 0;
        
        for(auto c: t) {
            res ^= c;
        }
        
        for(auto c: s) {
            res ^= c;
        }
        
        return res;
    }
};
```
