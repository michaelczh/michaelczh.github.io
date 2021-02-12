---
title: Pseudo-Palindromic Paths in a Binary Tree
tags:
  - Leetcode Easy
categories:
  - Leetcode
date: 2021-02-12 16:02:58
---

#### Description

[Link](https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/)
给定一棵树，求可以变为Palindrom路径的数量。

#### Answer1
可以用backtrace来做，但是效率比较差
时间: `O(N)`
空间: `O(9+H)` `H`是数的高度
```c
class Solution {
public:
    unordered_set<int> record;
    int res = 0;
    int pseudoPalindromicPaths (TreeNode* root) {
        helper(root);
        return res;
    }
    
    void helper(TreeNode* root) {
        if (!root) return;
        
        if (record.count(root->val)) {
            record.erase(root->val);
        }else{
            record.insert(root->val);
        }
        
        helper(root->left);
        helper(root->right);
        
        if (!root->left && !root->right) {
            if (record.size() == 0 || record.size() == 1) {
                res++;
            }
        }
        
        if (record.count(root->val)) {
            record.erase(root->val);
        }else{
            record.insert(root->val);
        }
    }
};
```

#### Answer2

还有一种方法我们不用`map`来保存而是用`bitmask`来保存每一条路径的数，用`^`来抵消已经出现过一次的数。最后到达根节点的时候我们只需要利用`count&(count-1) == 0`就可以判断`count`的是否只有一位为`1`。

```c
class Solution {
public:
    int pseudoPalindromicPaths (TreeNode* root, int count = 0) {
        if (!root) return 0;
        count ^= 1 << (root->val - 1);
        int res = pseudoPalindromicPaths(root->left, count) + pseudoPalindromicPaths(root->right, count);
        if (root->left == NULL && root->right == NULL && (count & (count - 1)) == 0) {
            res++;
        }
        return res;
    }
};
```