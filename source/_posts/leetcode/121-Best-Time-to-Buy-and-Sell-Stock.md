---
title: 121. Best Time to Buy and Sell Stock
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-26 09:41:41
---

#### Answer
因为只能交易一次，所以肯定是一个最大值减去最小值就是答案。
但是有个条件是最小值必须在最大的左边。
所以遍历这个数列，然后更新最小值。同时计算最大利益
```c
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        
        int res = 0;
        int minPrice = INT_MAX;
        for(int i = 0; i < prices.size(); ++i) {
            res = max(prices[i] - minPrice, res);
            minPrice = min(prices[i], minPrice);
        }
        
        return res;
    }
};
```
