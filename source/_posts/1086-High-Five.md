---
title: 1086. High Five
tags:
  - Leetcode
   - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-11-10 23:39:59
---

#### Problem


**Note:**


#### Example
> **Input**: 
**Output**: 
**Explanation**:


#### Explanation

#### Answer
```c
// 思路：比较简单的一道题目
//      创造一个map 来维护学生id和所有成绩
//      使用pq来维护前五大成绩
//      最后遍历pq来获得总和求得平均数

// 复杂度: O(N) 
//      虽然pq的复杂度应该是O(NlongN)，但是由于我们只维护了5个值，所以heap的深度是一定的
class Solution {
public:
    vector<vector<int>> highFive(vector<vector<int>>& items) {
        
        map<int, priority_queue<int, std::vector<int>, std::greater<int> >> map;
        
        for(auto& item: items) {
            map[item[0]].push(item[1]);
            if (map[item[0]].size() > 5) map[item[0]].pop();
        }
        
        vector<vector<int>> res;
        for (auto& item: map) {
            int tmp = 0;
            while(!map[item.first].empty()) {
                tmp += map[item.first].top();
                map[item.first].pop();
            }
            res.push_back(vector<int>{item.first,tmp/5});
        }
        
        return res;
    }
};
```
