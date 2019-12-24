---
title: 1099. Two Sum Less Than K
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-03 21:28:12
---

#### Answer 1
这道题用暴力很容易做出来，复杂度是O(N^2)
这边的思路是先排序，复杂度是(NlogN)
然后双重循环来判定值，这边一个好处是如果和大于`K`就可以判断后面的值也用不着了。
但是复杂度怎么说呢，也不知道。只知道比O(N^2)小。
```c
class Solution {
public:
    int twoSumLessThanK(vector<int>& A, int K) {
        
        sort(A.begin(), A.end());
        int res = -1;
        for(int i = 0; i < A.size(); ++i) {
            for (int j = i+1; j < A.size(); ++j) {
                int sum = A[i] + A[j]; 
                if (sum < K) res = max(res, sum);
                else break;
            }
        }
        
        return res;
    }
};
```

#### Answer 2
最好还是双指针做吧
```c
class Solution {
public:
    int twoSumLessThanK(vector<int>& A, int K) {
        
        sort(A.begin(), A.end());
        int res = -1;
        
        int i = 0, j = A.size()-1;
        
        while( i < j) {
            int sum = A[i] + A[j];
            if (sum < K) {
                res = max(res, sum);
                i++;
            }else j--;
        }
        
        return res;
    }
};
```
