---
title: Minimum Window Substring
tags:
  - Leetcode Hard
  - Hash Table
  - Two Pointers
  - String
  - Sliding Window
categories:
  - Leetcode
date: 2021-02-06 12:55:01
---

#### Description

[Link](https://leetcode.com/problems/minimum-window-substring/)


#### Answer
给定两个字符串`s`和`t`。要求返回包含`t`所有字符的`s`的最段子字符串。
主要思路是用`sliding window`来做。
思路是保持一个窗口，首先我们移动移动窗口的尾部使得能够包含`t`的所有字符，当我们找到之后，我们通过推进窗口的开始位置来求的最优解。

我们用一个变量`reuqired`来表示目前window内还需要匹配到多少字符才能符合题目要求。
我们再用一个`remaining`数组来表示`t`中每个字符的个数。

我们遍历`s`字符串的每一个字符`c`
1. 假设required不为0，我们需要延长窗口的大小，并且--`remaining`里的字符`c`，假设原来`s`就不包含`c`这个字符，那么--的结果就是负的。
因此如果--之后的结果是`>=0`就代表我们找到了需要的字符，因此`required--`

2. 假设required为0，代表我们窗口内以及包含了所有我们需要的字符了，那我们可以尝试把窗口缩小，也就是窗口`start`的位置往前进，在往前进的过程中，我们需要把`remaining`里的相应字符给`++`, 如果`++`的结果是正数（如果`t`中不包含这个字符的话在上一步会是负数），代表我们需要的字符少了一个，那我们需要把`required++`代表下一次我们需要再重复第一个步骤来找到所需要的字符。


```c
class Solution {
public:
    string minWindow(string s, string t) {
        vector<int> remaining(128, 0);
        for(auto c: t) remaining[c]++;
        int required = t.size();
        int start = 0, i = 0, left = 0, minLen = INT_MAX;
        
        // = 是为了防止在最后一位的时候刚好找到
        while(i <= s.size() && start < s.size()) {
            if (required) {
                if (i == s.size()) break;
                remaining[s[i]]--;
                if (remaining[s[i]] >= 0) required--;
                ++i;
            }else{
                if ( i - start < minLen) {
                    minLen = i-start;
                    left = start;
                }
                remaining[s[start]]++;
                if (remaining[s[start]] > 0) required++;
                start++;
            }
            
        }
        return minLen == INT_MAX ? "" : s.substr(left, minLen);
    }
};
```
