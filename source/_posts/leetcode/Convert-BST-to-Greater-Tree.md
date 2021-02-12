---
title: Convert BST to Greater Tree
tags:
  - Leetcode Easy
categories:
  - Leetcode
date: 2021-02-12 15:56:09
---

#### Description

[Link](https://leetcode.com/problems/convert-bst-to-greater-tree/)
给定一颗二分查找的数，对每一个数字进行更新，更新为自己的数+大于自己的节点的和。

#### Answer

我们可以从小到大遍历整棵树，维持一个`curSum`, 当遍历到一个节点后，节点值加上`curSum`然后更新为节点值即可

```c
class Solution {
public:
    int curSum = 0;
    TreeNode* convertBST(TreeNode* root) {
        if (root) {
            if (root->right) {
                convertBST(root->right);
            }
            curSum += root->val;
            root->val = curSum;
            if (root->left) {
                convertBST(root->left);
            }
        }
        return root;
    }
};
```
