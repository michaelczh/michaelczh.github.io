---
title: 1085. Sum of Digits in the Minimum Number
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-11 22:24:55
---

#### Answer
```c
// 复杂度O(N)
class Solution {
public:
    int sumOfDigits(vector<int>& A) {
        
        int minNum = INT_MAX;
        for(auto& num : A) minNum = min(num, minNum);
        
        int sum = 0;
        while(minNum > 0) {
            sum += minNum%10;
            minNum = minNum / 10;
        }
        
        return 1- sum%2;
    }
};
```


#### Think
好简单的一道题目，一看竟然是亚麻的，不可思议。这样不可以诶。
不过今晚没时间了还是水一道题目去看其他的吧
hope everything fine tomorrow.
