---
title: 1170. Compare Strings by Frequency of the Smallest Character
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-09 20:11:52
---

#### Answer 1 
思想是计算`words`每个词的频率然后累加，最后计算`queries`里的每个`query`频率然后根据累加的累加的结果就可以知道比自己频率大的数量有多少
复杂度是O(N)
```c
class Solution {
public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        
        int n = queries.size();
        int m = words.size();
        
        vector<int> accumFreq(2000,0);
        for(auto word: words) {
            accumFreq[getF(word)]++;
        }
        
        for (int i = 1; i < 2000; ++i) accumFreq[i] += accumFreq[i-1];
        
        vector<int> res(n,0);
        for(int i = 0; i < n; ++i) {
            int f = getF(queries[i]);
            res[i] = m-accumFreq[f];
        }
        return res;
        
    }
    
    int getF(string query) {
        vector<int> freqs(26,0);
        for(auto& c : query) freqs[c - 'a']++;
        
        for(int freq: freqs) {
            if (freq!=0) return freq;
        }
        
        return 0;
    }
};
```

#### Answer 1 
思想是计算`words`每个词的频率然后累加，最后计算`queries`里的每个`query`频率然后根据累加的累加的结果就可以知道比自己频率大的数量有多少
复杂度是O(N)
```c
class Solution {
public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        
        int n = queries.size();
        int m = words.size();
        
        vector<int> accumFreq(2000,0);
        for(auto word: words) {
            accumFreq[getF(word)]++;
        }
        
        for (int i = 1; i < 2000; ++i) accumFreq[i] += accumFreq[i-1];
        
        vector<int> res(n,0);
        for(int i = 0; i < n; ++i) {
            int f = getF(queries[i]);
            res[i] = m-accumFreq[f];
        }
        return res;
        
    }
    
    int getF(string query) {
        vector<int> freqs(26,0);
        for(auto& c : query) freqs[c - 'a']++;
        
        for(int freq: freqs) {
            if (freq!=0) return freq;
        }
        
        return 0;
    }
};
```
