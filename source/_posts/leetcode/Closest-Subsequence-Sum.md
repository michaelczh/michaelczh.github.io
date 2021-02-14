---
title: Closest Subsequence Sum
tags:
  - Leetcode Easy
  - DP
categories:
  - Leetcode
date: 2021-02-08 22:32:53
mathjax: true
---

#### Description

[Link](https://leetcode.com/problems/closest-subsequence-sum/)
题目给定一个数列`nums`和`goal`. 求`nums`的子数列和`goal`的差的最小值。
最直观的方法是把所有子数列的和全部求出来，然后一一和`goal`比较。
求子数列的复杂度是$O(2^N)$。很容易就TLE。所以我们可以拆成2部当成`2sum`来做。

首先数列对半砍，我们求出左半边和右半边所有的子数列`g1` `g2` 时间复杂度变成$O(2^{\frac{N}{2}})$
然后我们遍历`g2`来找到`g1`中的组合使得和最接近`goal`. 当然我们还需要保持一个`res`来更新我们的最优解。
如果只是双重循环`g2`和`g1`, 时间复杂度会是$O(N^2)$。因此我们选择对`g1`做个排序，然后通过二分查找来找。

二分查找的时候其实我们需要找到`upper_bound`, 也就是比和大于等于`goal`的第一个数。既然我们可以找到这个数，那么我们也需要找到这个数的前面那个数，来做一个比较，那个有可能是最优解并且更新我们的`res`


#### Answer
```c
class Solution {
public:
    int minAbsDifference(vector<int>& nums, int goal) {
        int n = nums.size();
        vector<int> g1;
        vector<int> g2;
        genreateSum(0, n/2, 0, nums, g1);
        genreateSum(n/2, n, 0, nums, g2);
        sort(g1.begin(), g1.end());
        
        int res = INT_MAX;
        for(auto num2: g2) {
            int target = goal - num2;
            
            if (g1[0] > target) {
                res = min(res, abs(g1[0]+num2-goal));
                continue;
            }
            if (g1[g1.size()-1] < target) {
                res = min(res, abs(g1[g1.size()-1]+num2-goal));
                continue;
            }
            // 如果任何一个数都比target小，那么idx会返回g1.size(), 由于我们已经在上面一步做处理的，所以在这里不会出现
            int idx = upperBound(g1, target);
            
            res = min(res, abs(g1[idx]+num2-goal));
            if (idx >= 1) {
                res = min(res, abs(g1[idx-1]+num2-goal));
            }
        }
        return res;
    }
    
    
    void genreateSum(int start, int end, int curSum, vector<int>& nums, vector<int>& list) {
        if (start == end) {
            list.push_back(curSum);
            return;
        }
        genreateSum(start+1, end, curSum+nums[start], nums, list);
        genreateSum(start+1, end, curSum, nums, list);
    }
    
    int upperBound(vector<int>& nums, int target) {
        int i = 0, j = nums.size();
        while( i < j ) {
            int mid = i + (j-i)/2;
            if (nums[mid] < target) {
                i = mid+1;
            }else {
                j = mid;
            }
        }
        return i;
    }
};
```