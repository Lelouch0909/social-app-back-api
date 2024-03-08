package com.lontsi.devsocialmedia.services.impl;

import com.lontsi.devsocialmedia.exception.TweetException;
import com.lontsi.devsocialmedia.exception.UserException;
import com.lontsi.devsocialmedia.models.Tweet;
import com.lontsi.devsocialmedia.models.User;
import com.lontsi.devsocialmedia.repository.TweetRepository;
import com.lontsi.devsocialmedia.request.TweetReplyRequest;
import com.lontsi.devsocialmedia.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Tweet createTweet(Tweet req, User user) throws UserException {
        Tweet tweet = new Tweet();
        tweet.setCreatedAt(req.getCreatedAt());
        tweet.setTweet(true);
        tweet.setReply(false);
        tweet.setUser(user);
        tweet.setImage(req.getImage());
        tweet.setVideo(req.getVideo());
        tweet.setContent(req.getContent());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setTweet(true);

        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> findAllTweet() {
        return tweetRepository.findAllByIsTweetTrueOrderByCreatedAtDesc();
    }

    @Override
    public Tweet retweet(Long tweetId, User user) throws UserException, TweetException {

        Tweet tweet = findById(tweetId);
        if (tweet.getReplyTweets().contains(user)) {
            tweet.getRetweetUser().remove(user);
        } else tweet.getRetweetUser().add(user);
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet findById(Long tweetId) throws TweetException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetException("Tweet not found " + tweetId));
        return tweet;

    }

    @Override
    public void deleteTweetById(Long tweetId, Long userId) throws TweetException, UserException {
        Tweet tweet = findById(tweetId);
        if (!userId.equals(tweet.getUser().getId())) {
            throw new UserException("you cant delete another user's tweet");
        }
        tweetRepository.deleteById(tweet.getId());
    }

    @Override
    public Tweet removeFromRetweet(Long tweetId, Long userId) throws TweetException, UserException {
        return null;
    }

    @Override
    public Tweet createReply(TweetReplyRequest req, User user) throws TweetException {

        Tweet replyFor = findById(req.getTweetId());

        Tweet tweet = new Tweet();
        tweet.setCreatedAt(req.getCreatedAt());
        tweet.setTweet(false);
        tweet.setReply(true);
        tweet.setUser(user);
        tweet.setImage(req.getImage());
        tweet.setReplyFor(replyFor);

        Tweet savedReply = tweetRepository.save(tweet);
        tweet.getReplyTweets().add(savedReply);
        tweetRepository.save(replyFor);
        return replyFor;
    }

    @Override
    public List<Tweet> getUserTweet(User user) {
        return tweetRepository.findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(user, user.getId());
    }

    @Override
    public List<Tweet> findByLikesContainsUser(User user) {
        return tweetRepository.findByLikesUser_id(user.getId());
    }
}
