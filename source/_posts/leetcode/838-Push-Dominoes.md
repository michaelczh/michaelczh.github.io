---
title: 838. Push Dominoes
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Medium
date: 2019-07-06 16:27:02
---

#### Problem
![Figure](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/05/18/domino.png)


There are `N` dominoes in a line, and we place each domino vertically upright.

In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. `S[i] = 'L'`, if the i-th domino has been pushed to the left; `S[i] = 'R'`, if the i-th domino has been pushed to the right; `S[i] = '.'`, if the i-th domino has not been pushed.

Return a string representing the final state


#### Example
> **Input**: ".L.R...LR..L.."
**Output**: "LL.RR.LLRRLL.."


#### Explanation
就只有四种情况
> L...L = LLLLL
> L...R = L...R
> R...L = RR.LL
> R...R = R...R

分别对这四种情况进行讨论就行了。
找到一个`L`或`R`，继续寻找一个`L`或`R`，我们只关心他们两个的距离，根据上面四种可能性，分别添加字符
具体见代码

#### Answer
```c
class Solution {
public:
    // L...L = LLLLL
    // L...R = L...R
    // R...L = RR.LL
    // R...R = R...R
    
    string pushDominoes(string d) {
        d = 'L' + d + 'R';
        string res = "";
        for (int i = 0, j = 1; j < d.length(); ++j){
            if (d[j] == '.') continue;
            int middleNum = j - i - 1;
            if (i > 0) res += d[i];
            if (d[i] == d[j]) res += string(middleNum, d[i]);
            else if (d[i] == 'L' && d[j] == 'R') res += string(middleNum, '.');
            else res+= string(middleNum/2, 'R') + string( middleNum%2, '.') + string(middleNum/2, 'L');
            i = j;
        }
        return res;
    }
};
````
