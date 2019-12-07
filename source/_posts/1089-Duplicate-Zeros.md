---
title: 1089. Duplicate Zeros
tags:
  - Leetcode
  - Array
  - review
categories:
  - Leetcode Easy
  - Array
date: 2019-12-07 13:51:42
---

#### Answer
这道题其实不是特别能理解
```c
class Solution {
public:
    void duplicateZeros(vector<int>& arr) {
        
        int shift = 0;
        for(auto& num : arr) shift += (num == 0?  1 : 0);
        
        // i 是旧的arr的index, j是新的index
        int n = arr.size();
        for (int i = n-1, j = n+shift-1; i < j; --i,--j) {

            if(arr[i] != 0) {。// 如果不是0 且 j 小于n了 那么就复制
                if (j < n) arr[j] = arr[i];
            }else{ // 
                if (j < n){  // 如果是0 且j < n 那我们就让它为0
                    arr[j] = 0;
                }
                if (--j < n) { // 如果--j 小于n 那复制一遍0
                    arr[j] = 0;
                }
            }
        }
        
        
    }
};
```
