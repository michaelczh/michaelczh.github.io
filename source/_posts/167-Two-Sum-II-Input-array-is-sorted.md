---
title: 167. Two Sum II - Input array is sorted
tags:
  - Leetcode
  - Array
categories:
  - Leetcode Easy
  - Array
date: 2019-12-23 13:47:04
---

#### Answer 1
2sum是很经典的题目了，最简单的形式应该是用map最初。现在由于题目给了是排序过的，我们可以使用二分查找
复杂度是O(N)
```c
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        
        for(int i = 0; i < numbers.size(); ++i) {

        	int tar = target - numbers[i];

        	int found = binarySearch(numbers, tar, i+1, numbers.size()-1);

        	if (found != -1) return vector<int>{i+1, found+1};
        }

        return vector<int>{-1,-1};
    }
    
    int binarySearch(vector<int>& nums, int target, int i, int j) {

	while( i <= j) {
		int mid = i + (j-i)/2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) i = mid+1;
            else j = mid-1;
        }

        return -1;
    }
};
```

#### Answer 2
另外一种方法的复杂度也是O(N)，但是不需要二分查找
```c
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        
        int i = 0, j = numbers.size()-1;
        
        while( i < j ) {
            
            int currSum = numbers[i] + numbers[j];
            if ( currSum == target) return vector<int>{i+1,j+1};
            
            if (currSum > target) j--;
            else i++;
        }
        
        return vector<int>{-1,-1};
    }
};
```