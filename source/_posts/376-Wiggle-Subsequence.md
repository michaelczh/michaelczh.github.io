---
title: 376. Wiggle Subsequence
tags:
  - Leetcode
  - DP
categories:
  - Leetcode Medium
date: 2019-06-23 00:14:33
---

#### Problem
A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

**Note:**


#### Example
> **Input**: [1,7,4,9,2,5]
**Output**: 6
**Explanation**: The entire sequence is a wiggle sequence.


#### Answer
```c++
class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        int n = nums.size();
        if (n==0) return 0;
        
        vector<int> up(n);
        vector<int> down(n);
        
        // set initial values
        up[0] = 1;
        down[0] = 1;
        
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i-1]) {
                up[i]   = down[i-1] + 1;
                down[i] = down[i-1];
            }else if (nums[i] < nums[i-1]){
                down[i] = up[i-1] + 1;
                up[i]   = up[i-1];
            }else{
                down[i] = down[i-1];
                up[i]   = up[i-1];
            }
        }
        
        // return the max result
        return max(up[n-1], down[n-1]);
    }
};
````
#### Explanation

这题采用**DP**来完成.
因为对于某一个数，它可能是下降或者上升，所以定义2个数组，**up[]**和**down[]**，

**up[i]**和**down[i]**表示的是，对于**nums[i]**，如果它是下降或是上升的话，分别最大长度是多少。
判断**nums[i]**和**nums[i-1]**的关系，
如果**nums[i]>nums[i-1]**: 那就让**up[i]=down[i-1]+1**，小于的情况同理。

在返回结果的时候，返回最大长度的那一种可能。