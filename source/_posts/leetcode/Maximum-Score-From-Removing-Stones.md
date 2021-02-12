---
title: Maximum Score From Removing Stones
tags:
  - Leetcode Medium
  - Math
  - Heap
categories:
  - Leetcode
date: 2021-02-07 16:38:15
---

#### Description

[Link](https://leetcode.com/problems/maximum-score-from-removing-stones/)
有三个数，每一次操作我们可以选择任意两个数减，求最多能玩几次。


#### Answer

这道题的策略应该是优先减少最大的和第二大的。因为如果从最小的开始减，那么游戏很快就会结束，例如`1, 2, 10`。
每一次减少后，我们都回归求新的三个数的答案+1。 

followUp：由于我们本质上是扣除最大和第二大的数，然后循环再一直求下去，因此我们可以一次性剪去多个数，只要能保证最大数和第二大数在减去该数之后还能保持第一大和第二大。

```c
class Solution {
public:
    int maximumScore(int a, int b, int c) {
        if (a < b)
            return maximumScore(b, a, c);
        if (b < c)
            return maximumScore(a, c, b);
        if (b == 0)
            return 0;
        int cnt = max(1, b-c);
        return cnt + maximumScore(a - cnt, b - cnt, c); 
    }
};
```
