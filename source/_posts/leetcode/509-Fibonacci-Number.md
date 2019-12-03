---
title: 509. Fibonacci Number
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-20 22:48:17
---

#### Description
经典的递归问题了

#### Answer 1
```c
class Solution {
public:
    int memo[31];
    int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        if (memo[N] == 0) memo[N] = fib(N-1) + fib(N-2);
        return memo[N];
    }
};
```


#### Answer 2
```c
class Solution {
public:
    int memo[31];
    int fib(int N) {
        if (N <= 1) return N;
        int i = 0, j = 1;
        int sum = 0;
        while(N-- > 1) {
            sum = i + j;
            i = j;
            j = sum;
        }
        return sum;
    }
};
```
