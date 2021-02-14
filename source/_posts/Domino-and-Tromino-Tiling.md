---
title: Domino and Tromino Tiling
tags:
  - Leetcode Medium
categories:
  - Leetcode
date: 2021-02-14 14:59:54
---

#### Description

[Link](https://leetcode.com/problems/domino-and-tromino-tiling/)

很有趣的一道题，给定一个`2*N`的格子，一共有2种形状，问有几种拼法。

#### Answer
虽然只有2种形状，一种是`domino`, 一种是`tromino`, 但是由于可以旋转，所以拼的时候一共有`domino`2种+`tromino`4种=6种。最开始也是想用`dp`做。但是对于`tromino`就不适用了。论坛有个人定一个三个`dp`来做。
https://leetcode.com/problems/domino-and-tromino-tiling/

时间: $O(N)$
空间: $O(3N)$
由于只需要最多前两种的状态，所以我们也可以用常数来保存状态使得空间复杂度只有$O(1)$

```c
class Solution {
public:
    int numTilings(int N) {
        
        if (N == 1) return 1;
        int mod = 1e9+7;
        vector<long> dp(N+1, 0);
        vector<long> dpUp(N+1, 0);
        vector<long> dpDown(N+1, 0);
        
        dp[1] = 1;
        
        dp[2] = 2;
        dpUp[2] = 1;
        dpDown[2] = 1;
        
        for(int i = 3; i <= N; ++i) {
            dp[i] = (dp[i-1] + dp[i-2] + dpUp[i-1] + dpDown[i-1]) % mod;
        
            dpUp[i] = (dp[i-2] + dpDown[i-1]) % mod;
        
            dpDown[i] = (dp[i-2] + dpUp[i-1]) % mod;
        }
        
        return dp[N];
    }
};
```
