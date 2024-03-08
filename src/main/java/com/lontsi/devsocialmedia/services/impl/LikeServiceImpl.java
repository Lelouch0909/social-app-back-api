package com.lontsi.devsocialmedia.services.impl;

import com.lontsi.devsocialmedia.exception.TweetException;
import com.lontsi.devsocialmedia.exception.UserException;
import com.lontsi.devsocialmedia.models.Like;
import com.lontsi.devsocialmedia.models.Tweet;
import com.lontsi.devsocialmedia.models.User;
import com.lontsi.devsocialmedia.repository.LikeRepository;
import com.lontsi.devsocialmedia.repository.TweetRepository;
import com.lontsi.devsocialmedia.services.LikeService;
import com.lontsi.devsocialmedia.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Like likeTweet(Long tweetId, User user) throws TweetException, UserException {

        Like isLikeExist = likeRepository.isLikeExists(user.getId(), tweetId);

        if (isLikeExist != null) {
            likeRepository.deleteById(isLikeExist.getId());
            return isLikeExist;
        }
        Tweet tweet = tweetService.findById(tweetId);
        Like like = new Like();
        like.setTweet(tweet);
        like.setUser(user);

        Like savedLike = likeRepository.save(like);

        tweet.getLikes().add(savedLike);
        tweetRepository.save(tweet);

        return savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long tweetId) throws TweetException {


        Tweet tweet = tweetService.findById(tweetId);
        List<Like> likes = likeRepository.findByTweetId(tweet.getId());

        return likes;
    }
}
