---
title: 216. Combination Sum III
tags:
  - Leetcode
  - Medium
categories:
  - Leetcode
date: 2019-06-22 21:22:00
---
#### Problem
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

**Note:**

All numbers will be positive integers.
The solution set must not contain duplicate combinations.


#### Example
> **Input**: **k** = 3, **n** = 7
**Output**: [[1,2,4]]
**Explanation**:
#### Answer
```c
class Solution {
public:
    vector<vector<int>> combinationSum3(int k, int n) {
        
        vector<vector<int>> res;
        vector<int> sol;
        helper(res, sol, k, n);
        return res;
    }
    
    void helper(vector<vector<int>> &res, vector<int> &sol, int k, int n) {
        
        if (sol.size() == k && n == 0) {
            res.push_back(sol);
            return;
        }
        
        for (int i = sol.empty() ? 1 : sol.back() + 1; i <= 9; ++i) {
                if (n - i < 0) return;
                sol.push_back(i);
                helper(res, sol, k, n-i);
                sol.pop_back();
        }
        
    }
};
````
#### Explanation
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190618135246402.png)


