---
title: 3Sum With Multiplicity
tags:
  - Leetcode Medium
categories:
  - Leetcode
date: 2021-02-14 14:35:31
---

#### Description

[Link](https://leetcode.com/problems/3sum-with-multiplicity/)

`3Sum`的升级版，给定一个数列，然后求所有加起来和是`target`的子数列的个数。


#### Answer
由于答案不是单一的，肯定根每个数字出现的次数有关，所以用一个`map`来储存所有数字的个数。然后我们分3种情况。假设`nums[i]+nums[j]+nums[k]=target`

1. 假设三个数字都是一样的。即`nums[i] == nums[j] == nums[k]`, 那么就是组合问题，从`N`个数字里面挑出3个。

2. 假设两个数字一样。即`nums[i] == nums[j] != nums[k]`, 也就是从`N`个数字里面挑出2个乘以`nums[k]`出现的次数。

3. 假设三个数字都不一样。这种比较复杂。嫁入给定的数组是`[1,2,3]`, `target=6`, 如果我们双循环数组，我们会得到`[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]`. 但是其实他们只能算一种。所以我们应该做个简单限制，当`nums[i] < nums[j] < nums[k]`或者`nums[i] > nums[j] > nums[k]`时候，我们才计算。

时间复杂度: $O(N^2)$
空间复杂度: $O(N)$
```c
class Solution {
public:
    int threeSumMulti(vector<int>& A, int target) {
        
        unordered_map<int,long> m;
        for(auto c: A) {
            m[c]++;
        }
        long res = 0;
        for(auto it1: m){
            for(auto it2: m) {
                int i = it1.first, j = it2.first, k = target - i - j;
                
                if (!m.count(k)) continue;
                if (i == j && j == k) {
                    res += m[i] * (m[i]-1) * (m[i]-2) / 6;
                }else if (i == j && i != k) {
                    res += m[i] * (m[i]-1) / 2 * m[k];
                }else if (i < j && j < k) {
                    res += m[i] * m[j] * m[k];
                }
            }
        }
        return res % int(1e9+7);
    }
};
```
