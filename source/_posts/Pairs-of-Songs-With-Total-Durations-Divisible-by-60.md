---
title: Pairs of Songs With Total Durations Divisible by 60
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-09 22:05:55
---

#### Answer
这道题可以想象成是2sum，但是两个相加的数其实不是数字本身，需要做一定的处理
首先是要满足能被60整除，需要找到两个数满足 
`(x+t) % 60`
等价于
`x%60 = 60 - t%60`
`x%60`的范围是 `[0,59]`
`60 - t%60`的范围是 `[1,60]`

所以等式应该变换成
`x%60 = (60 - t%60)%60`

之后遍历储存`x%60`
然后计算 `(60 - t%60)%60`是否存在，如果存在就代表找到了相应的可以和`x`相加被60整除的数字


```c
class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        
        unordered_map<int,int> count;
        int res = 0;
        
        for(auto& t : time) {
            
            int target = (60 - t % 60)%60;
            
            res += count[target];
            
            count[t%60]++;
        }
        
        return res;
    }
};
```
