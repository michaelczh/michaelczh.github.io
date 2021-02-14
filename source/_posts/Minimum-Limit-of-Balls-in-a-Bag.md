---
title: Minimum Limit of Balls in a Bag
tags:
  - Leetcode Medium
categories:
  - Leetcode
date: 2021-02-14 14:24:01
---

#### Description

[Link](https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/)
给定一个数组和一个最大操作数，每次操作都可以把其中一个数分割为任意两个正数，求最后数列里面最大的那个值的最小值。

#### Answer
在比赛的时候，刚开始是想通过`priority queue`来处理最大的数，然后每次对半分，这样就可以保证最后所有数都能够相对平均。但是明显和题目实例得出来的不一样，后面又发现，其他怎么分根据`maxOperation`的不同也会不同，所以也没有办法用`dp`来做。后面看论坛的答案使用`binary search`来做。也就是我假设一个处理过后的最大值`mid`，然后遍历整个数组，看看为了变成`mid`得操作几次。然后全部相加看看有没有大于`maxOperation`. 如果大于`maxOperation`就表示我们取的`mid`值太大了，所以要取`[mid+1, right]`

```c
class Solution {
public:
    int minimumSize(vector<int> nums, int maxOperations) {
        int left = 1, right = 1e9;
        
        while(left < right) {
            int mid = left + (right-left)/2;
            
            int operations = 0;
            for(auto num: nums) {
                // 需要几步才能分成小于等于mid
                operations += (num-1)/mid;
            }
            if (operations > maxOperations) {
                left = mid+1;
            }else {
                right = mid;
            }
        }
        return left;
    }
};
```
