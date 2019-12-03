---
title: 72. Edit Distance
tags:
  - Leetcode
  - DP
categories:
  - Leetcode Hard
date: 2019-06-24 15:41:24
---

#### Problem
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character


#### Example
> **Input**: word1 = "horse", word2 = "ros"
**Output**: 3
**Explanation**:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
#### Explanation
这是一道DP问题。
要求输入从**word1**变化到**word2**的最少步骤。
题目定义了有三种修改方式分别是插入，替换和删除。
我们设定一个DP数组，首先明确
**DP[i][j]** 代表从**word1[0...i-1]**变化到**word2[0...j-1]**的最少步骤。
##### DP初值
很明显N长度的字符串变化到空字符串需要N个步骤。所以
**DP[i][0]=i**
同理，空字符串转化到N个字符串也需要N个步骤，所以有
**DP[0][i]=i**

##### DP更新
假设我们已经知道**DP[i-1][j-1]**
1) 如果**word1[i] = words[j]**，则**DP[i][j]=DP[i-1][j-1]**表示无需变化。
2) 如果**word1[i] != words[j]**，分为三种情况
a. 替换: **word1[i] ==> word2[j]**，**DP[i][j]=DP[i-1][j-1]+1**
b. 删除: 删除**word1[i-1]**,  则**DP[i][j]=DP[i-1][j]+1**
c. 增加: 增加**word2[j-1]**, 则**DP[i][j]=DP[i][j-1]+1**
我们只要比较上面三种情况哪种比较小，并更新到**DP**就可以了。
最后返回**DP[n][m]**



#### Answer
```c++
class Solution {
public:
    int minDistance(string word1, string word2) {
        int n = word1.size();
        int m = word2.size();
        vector<vector<int>> dp(n+1, vector<int>(m+1,0));
        // dp[i][j] indicated the minimum steps 
        // from word1[0...i) to word1[0...j)
        for (int i = 1; i <= n; ++i) dp[i][0] = i; 
        
        for (int i = 1; i <= m; ++i) dp[0][i] = i;
        
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (word1[i-1] == word2[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int case1 = dp[i-1][j-1];
                    int case2 = dp[i-1][j];
                    int case3 = dp[i][j-1];
                    dp[i][j] = min(case1, min(case2, case3)) + 1;
                }
            }
        }
        return dp[n][m];
    }
};
```

