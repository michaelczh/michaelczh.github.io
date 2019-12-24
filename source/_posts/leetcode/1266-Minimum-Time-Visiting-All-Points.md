---
title: 1266. Minimum Time Visiting All Points
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-28 23:03:55
---

#### Answer
刚开始看起来还挺唬人的，以为要用BFS什么的做，没想到很简单
```c
// 复杂度O(N)
class Solution {
public:
    int minTimeToVisitAllPoints(vector<vector<int>>& points) {
        int res = 0;
        for (int i = 0; i < points.size()-1; ++i) {
            int hor = abs(points[i][0] - points[i+1][0]);
            int ver = abs(points[i][1] - points[i+1][1]);
            res += max(hor, ver);
        }
        
        return res;
    }
};
```

[
[36,59,71,15,26,82,87],
[56,36,59,71,15,26,82],
[15,0 ,36,59,71,15,26]]