---
title: Path With Minimum Effort
tags:
  - Leetcode
  - Dijkstra
categories:
  - Leetcode Medium
date: 2021-02-04 22:05:11
---

#### Description

[Link](https://leetcode.com/problems/path-with-minimum-effort/)
题目要求从最左上角走到最右下角，找到一条路可以使得每一步的差的值的最大的最小值。

#### Answer 1

没有做出来，看了答案用了dijkstra算法。
设置一个 $m*n$ 的数组`dist`，表示每一个元素到$(0, 0)$的阶段
`d`的最大差值。初始化都设为`INT_MAX`.
1. 设置一个`pq`, 把最左上角的点推入，设置距离为0，
2. 循环这个`pq`，我们要优先考虑`d`最小的元素，因为只有从最小找才有可能找到最小的最大`d`.
3. 到遍历到一个点的时候，我们看它的四周的四个点，计算他们的阶段差`d`, 如果小于数组`dist`则更新目前的值，并把他们加入到`pq`中。

其实`pq`里面取出来的第一个的点是最有可能是答案要求的最优解，我们找到有可能的最优解，并且去遍历他们的身边的四个点来更新四个点的距离信息，并且加入到`pq`中，假设周边四个点突然出现了很大的抖动，那么`pq`就会把他们滞后，而开始遍历其它的点。如果把所有的点都遍历完，那么最后`dist`里面所储存的就是初始点到每一个点的最优解。

空间复杂度: `O(MN)`
时间复杂度: `O(MN)`
```c
int minimumEffortPath(vector<vector<int>>& heights) {
    int dirs[5] = {-1, 0, 1, 0, -1};
    int n = heights.size();
    int m = heights[0].size();
    vector<vector<int>> dist(n, vector<int>(m, INT_MAX));        
    priority_queue<pair<int, int>, vector<pair<int,int>>, greater<pair<int, int>>> pq;

    pq.push(pair{0, 0});

    while(!pq.empty()) {

        int d = pq.top().first, r = pq.top().second/100, c = pq.top().second%100;
        pq.pop();

        if (d > dist[r][c]) continue;

        if (r == n-1 && c == m-1 ) return d;

        for(int i = 0; i < 4; ++i) {
            int nr = r+dirs[i], nc = c+dirs[i+1];

            if (nr >= 0 && nc >=0 && nr < n && nc < m) {

                int newDist = max(d, abs(heights[r][c]-heights[nr][nc]));

                if (newDist < dist[nr][nc]) {
                    dist[nr][nc] = newDist;

                    pq.push(make_pair(newDist, nr*100+nc));
                }
            }
        }
    }

    return 0;

}
```
