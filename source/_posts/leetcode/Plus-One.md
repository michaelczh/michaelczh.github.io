---
title: Plus One
tags:
  - Leetcode - Array
categories:
  - Leetcode Easy
  - Array
date: 2020-01-16 22:00:21
---

#### Answer 1
这道题目跟之前两个`list`相加的做法很像，不过更简单。
```c
class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
    
        int add = 0;
        int n = digits.size();
        digits[n-1] += 1;  // since list is not empty
        for(int i = digits.size()-1; i >=0; --i) {
            digits[i] += add;
            add = digits[i] / 10;
            digits[i] = digits[i] % 10;
        }
        if (add) digits.insert(digits.begin(), add);
        
        return digits;
    }
};
```

### Answer 2
不过看论坛的其实更简单的做法是每一位判断是不是小于9，如果是就直接加一返回。
循环外的话就在前面差一个1
```java
public int[] plusOne(int[] digits) {
        
    int n = digits.length;
    for(int i=n-1; i>=0; i--) {
        if(digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        
        digits[i] = 0;
    }
    
    int[] newNumber = new int [n+1];
    newNumber[0] = 1;
    
    return newNumber;
}
```
