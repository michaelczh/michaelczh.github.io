---
title: 1133. Largest Unique Number
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-20 22:28:37
---

#### Answer
很简单的一道题目。用`map`来储存数据和出现的次数，然后便利判断就可以了
不过似乎题目不够严谨，没有说一定都是大于0的数。
因为在test case 是`[-5 -6]`的时候，出来的结果是`-1`显然不正确
```c
// 复杂度:O(N LogN)
class Solution {
public:
    int largestUniqueNumber(vector<int>& A) {
        unordered_map<int, int> map;
        for(auto& num : A) map[num]++;
        int res = INT_MIN;
        for(auto it: map) {
            if (it.second > 1) continue;
            res = max(it.first, res);
        }
        return res == INT_MIN ? -1 : res;
    }
};
```
