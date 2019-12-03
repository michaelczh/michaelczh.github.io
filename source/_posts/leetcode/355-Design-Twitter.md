---
title: 355. Design Twitter
tags:
  - Leetcode
categories:
  - Leetcode Medium
date: 2019-06-25 17:18:27
---

#### Problem
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

1.  **postTweet(userId, tweetId)**: Compose a new tweet.
2.  **getNewsFeed(userId)**: Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
3.  **follow(followerId, followeeId)**: Follower follows a followee.
4.  **unfollow(followerId, followeeId)**: Follower unfollows a followee.



#### Example
 * Your Twitter object will be instantiated and called as such:
 * Twitter* obj = new Twitter();
 * obj->postTweet(userId,tweetId);
 * vector<int> param_2 = obj->getNewsFeed(userId);
 * obj->follow(followerId,followeeId);
 * obj->unfollow(followerId,followeeId);

> Twitter twitter = new Twitter();
> 
> // User 1 posts a new tweet (id = 5).
> twitter.postTweet(1, 5);
> 
> // User 1's news feed should return a list with 1 tweet id -> [5].
> twitter.getNewsFeed(1);
> 
> // User 1 follows user 2.
> twitter.follow(1, 2);
> 
> // User 2 posts a new tweet (id = 6).
> twitter.postTweet(2, 6);
> 
> // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
> // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
> twitter.getNewsFeed(1);
> 
> // User 1 unfollows user 2.
> twitter.unfollow(1, 2);
> 
> // User 1's news feed should return a list with 1 tweet id -> [5],
> // since user 1 is no longer following user 2.
> twitter.getNewsFeed(1);


#### Explanation
其实这道题相对简单。
四个功能
1. 关注
关注的功能比较简单，用`unodered_map<userId, unodered_set<followeeId>>`

2. 取消关注
取消关注的话只需要把对应的`unodered_set<followeeId>`里面的`id`删掉就可以了。
3. 发推文（把`userId`和`tweetId`绑定）
由于最后需要返回最近的推文，和时间有关，因此定义一个`pair<tweetId, time>`来表示一个推文，`time`由全局控制。
然后同样用`unodered_map<userId, unodered_set<tweetId>>` 来储存发过的推特。
4. 返回自己和关注的人最近的10条推文`vector[tweetId]`
定义一个`priority_queue<pair<tweetId, time>>`来根据时间排序。
遍历自己发过的推，以及关注的人发过的推。然后储存到`pq`，当`pq`大于10个的时候，推出一个。


#### Answer

```c++
struct CustomCompare
{
    bool operator()(const pair<int,int>& tw1, const pair<int,int>& tw2)
    {
        return tw1.second > tw2.second;
    }
};

struct pair_hash {
    inline std::size_t operator()(const std::pair<int,int> & v) const {
        return v.first;
    }
};

class Twitter {
public:
    #define TWEET pair<int, int>
    /** Initialize your data structure here. */
    int time = 0;
    unordered_map<int, unordered_set<int>> followMap;
    unordered_map<int, unordered_set<TWEET,pair_hash>> postMap;
    
    Twitter() {
        
    }
    
    /** Compose a new tweet. */
    void postTweet(int userId, int tweetId) {
        TWEET tweet = make_pair(tweetId, time++);
        postMap[userId].insert(tweet);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    vector<int> getNewsFeed(int userId) {
        priority_queue<pair<int,int>, vector<pair<int,int>>, CustomCompare> pq;
        unordered_set<TWEET,pair_hash> selfPost = postMap[userId];
        for (auto& it : selfPost) {
            pq.push(it);
            if (pq.size() > 10) pq.pop();
        }
        
        for (auto& followeeId: followMap[userId]) {
            for (auto& it : postMap[followeeId]) {
                pq.push(it);
                if (pq.size() > 10) pq.pop();
            }
        }
        int j = pq.size();
        vector<int> res(j);
        while (!pq.empty()) {
            res[--j] = pq.top().first;
            // res.push_front();
            pq.pop();
        }
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        followMap[followerId].insert(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    void unfollow(int followerId, int followeeId) {
        followMap[followerId].erase(followeeId);
    }
};
```

