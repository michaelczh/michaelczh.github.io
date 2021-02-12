---
title: Maximum Swap
tags:
  - Leetcode Medium
  - Array
  - Math
categories:
  - Leetcode
date: 2021-02-12 15:46:06
---

#### Description

[Link](https://leetcode.com/problems/maximum-swap/)

给定一个整数，要求对调两个字符使得这个值最大。如果不对调也是最大值的话，就返回原来的值。

#### Answer
要使得值最大的话，我们应该使得前面的小数字与后面的大数字对调。
1. 扫一次字符，记录每个字符最大的下标的数组`bucket`。
2. 再扫一次字符，对每一个字符，我们都对第一步得到的`bucket`来做遍历，看看有没有下标大于目前的，切数字也大于的，有的话我们对调，并且返回就可以了。

3. 如果遍历没能返回结果代表不存在答案，我们就返回原来的值

```c
class Solution {
public:
    int maximumSwap(int num) {
        
        string numStr = to_string(num);
        int n = numStr.size();
        
        vector<int> buckets(10, -1);
        for(int i = 0; i < n; ++i) {
            buckets[numStr[i]-'0'] = i;
        }
        
        for(int i = 0; i < n; ++i) {
            for(int j = 9; j > numStr[i]-'0'; --j) {
                if (buckets[j] > i) {
                    swap(numStr[buckets[j]], numStr[i]);
                    return stoi(numStr);
                }
            }
        }
        
        return num;
        
    }
};
```
