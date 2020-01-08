---
title: 661. Image Smoother
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-24 09:41:43
---

#### Answer 1
时间复杂度O(N^2), 空间复杂度也是O(N^2)
没有智慧的一种解法
```c
class Solution {
public:
    vector<vector<int>> imageSmoother(vector<vector<int>>& M) {
        vector<vector<int>> res = M;
        for(int i = 0; i < M.size(); ++i) {
            for(int j = 0; j < M[0].size(); ++j) {
                res[i][j] = sumOfNine(M, i, j);
            }
        }
        return res;
    }
    
    
    int sumOfNine(vector<vector<int>>& M, int a, int b) {
        
        int cul = 0;
        int cnt = 0;
        for(int i = a-1; i <= a + 1; ++i) {
            for(int j = b-1; j <= b+1; ++j) {
                if (i < 0 || i >= M.size() || j < 0 || j >= M[0].size()) continue;
                cul += M[i][j];
                cnt ++;
            }
        }
        return cul / cnt;
    }
};
```

#### Answer 2

另外一种方法可以做到O(1)空间复杂度，因为题目给了数值是[0-255]，所以我们可以用8bits来储存原来的数字。
剩余9-16bit来储存新的数字。
```c
class Solution {
public:
    vector<vector<int>> imageSmoother(vector<vector<int>>& M) {
        
        vector<vector<int>> dirs = {{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{1,1},{-1,1},{1,-1}};
        
        for (int i = 0; i < M.size(); ++i) {
            for(int j = 0; j < M[0].size(); ++j) {
                
                int cnt = 1,  tmp = M[i][j];
                for(auto dir: dirs) {
                    int a = i + dir[0], b = j + dir[1];
                    if (a < 0 || a >= M.size() || b < 0 || b >= M[0].size()) continue;
                    cnt++;
                    tmp += M[a][b] & 0xFF;
                }
                
                M[i][j] |= (tmp/cnt) << 8;
                
            }
        }
        
        for (int i = 0; i < M.size(); ++i) {
            for(int j = 0; j < M[0].size(); ++j) {
                M[i][j] >>= 8;
            }
        }
        
        return M;
    }
    
    
};
```
