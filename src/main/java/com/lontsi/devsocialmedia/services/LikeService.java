package com.lontsi.devsocialmedia.services;

import com.lontsi.devsocialmedia.exception.TweetException;
import com.lontsi.devsocialmedia.exception.UserException;
import com.lontsi.devsocialmedia.models.Like;
import com.lontsi.devsocialmedia.models.User;

import java.util.List;

public interface LikeService {

    public Like likeTweet(Long tweetId, User user) throws TweetException , UserException;

    public List<Like> getAllLikes(Long tweetId) throws TweetException;

}
