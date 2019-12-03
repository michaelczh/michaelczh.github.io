---
title: 16. 3Sum Closest
tags:
  - Leetcode
  - 3sum
categories:
  - Leetcode Medium
date: 2019-06-28 01:20:10
---

#### Problem
Given an array `nums` of n integers and an integer `target`, find three integers in `nums` such that the sum is closest to `target`. Return the sum of the three integers. You may assume that each input would have exactly one solution.


#### Example
> Given array nums = [-1, 2, 1, -4], and target = 1.
>
> The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


#### Explanation
这道题目意外简单。最先能想到的解法应该是**brute-force**。但是复杂度`O(N^3)`明显不会是题目要求的。
直接告诉我这类题目应该要先排序一下，复杂度`N(LogN)`
一时间没有什么好想法，于是就想我们可以从第一位到最后一位先扫一遍。确定三个数中的第一个数
`i: 0 ~ N-1`, 在扫的过程中增加两个指针`p1`和`p2`。因为我们已经对数组进行排序了，所以可以根据`nums[i]`,`nums[p1]`和`nums[p2]`三者的和于`target`的差值来确定`p1`,`p2`指针的移动方向。并且保存与`target`最相近的那个值。算法复杂度为`O(N^2)`

`ps:` 为了更快，我们可以在每次计算完三个数的`sum`时，判断一下是不是等于`target`的值，如果等于`target`的值，直接返回就可以了。



#### Answer
```c
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.size()-2; ++i) {
            int p1 = i + 1;
            int p2 = nums.size()-1;
            while(p1 < p2) {
                int currSum = nums[i] + nums[p1] + nums[p2];
                if (currSum == target) return target;
                if ( abs(currSum - target) < abs(res - target) ) 
                    res = currSum;
                if (currSum < target) p1++;
                else p2--;
            }
        }
        
        return res;
    }
};
````
