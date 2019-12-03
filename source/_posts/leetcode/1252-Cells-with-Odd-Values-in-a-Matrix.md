---
title: 1252. Cells with Odd Values in a Matrix
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-10 18:05:22
---

#### Problem


**Note:**


#### Example
> **Input**: 
**Output**: 
**Explanation**:


#### Explanation

#### Answer
```c
// 思路
// 1 分别创建行列数组，分别统计行列的相加情况，
//   *由于我们只需要只要是不是奇数偶数，所以使用^来保留0 或者1
// 2 最后遍历二维数组就，判断数值相加是不是偶数 来更新res

// 时间复杂度 O(nm) 
// 空间复杂度 O(n)
class Solution {
public:
    int oddCells(int n, int m, vector<vector<int>>& indices) {
        
        vector<int> rows(n, 0);
        vector<int> cols(m, 0);
        
        for(auto& indice: indices) {
            rows[indice[0]] ^= 1;  // 如果是偶数的话就会变成0
            cols[indice[1]] ^= 1;
        }
        
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                res += rows[i] ^ cols[j] == 0 ? 0 : 1;
            }
        }
        
        return res;
    }
};
```
