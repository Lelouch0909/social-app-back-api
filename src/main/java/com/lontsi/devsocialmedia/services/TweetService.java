package com.lontsi.devsocialmedia.services;

import com.lontsi.devsocialmedia.exception.TweetException;
import com.lontsi.devsocialmedia.exception.UserException;
import com.lontsi.devsocialmedia.models.Tweet;
import com.lontsi.devsocialmedia.models.User;
import com.lontsi.devsocialmedia.request.TweetReplyRequest;

import java.util.List;

public interface TweetService {

    public Tweet createTweet(Tweet req, User user) throws UserException;

    public List<Tweet> findAllTweet();
    public Tweet retweet (Long tweetId, User user ) throws  UserException, TweetException;

    public  Tweet findById(Long tweetId) throws TweetException;

    public void deleteTweetById(Long tweetId,Long userId) throws  TweetException, UserException;

    public Tweet removeFromRetweet(Long tweetId,Long userId) throws  TweetException, UserException;


    public  Tweet createReply(TweetReplyRequest req, User user) throws  TweetException;

    public List<Tweet> getUserTweet(User user);

    public  List<Tweet> findByLikesContainsUser(User user);

}
