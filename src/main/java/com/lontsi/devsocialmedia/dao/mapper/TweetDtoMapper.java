package com.lontsi.devsocialmedia.dao.mapper;

import com.lontsi.devsocialmedia.dao.TweetDto;
import com.lontsi.devsocialmedia.dao.UserDto;
import com.lontsi.devsocialmedia.models.Tweet;
import com.lontsi.devsocialmedia.models.User;
import com.lontsi.devsocialmedia.util.TweetUtil;

import java.util.ArrayList;
import java.util.List;

public class TweetDtoMapper {

    public static TweetDto toReplyTweetDto(Tweet tweet, User reqUser) {
        UserDto user = UserDtoMapper.toUserDto(tweet.getUser());

        boolean isLiked = TweetUtil.isLikedByReqUser(reqUser, tweet);

        boolean isRetweeted = TweetUtil.isRetweetByReqUser(reqUser, tweet);
        List<Long> retweetUserId = new ArrayList<>();

        for (User user1 : tweet.getRetweetUser()) {
            retweetUserId.add(user1.getId());
        }
        TweetDto tweetDto = new TweetDto();

        tweetDto.setId(tweet.getId());
        tweetDto.setUser(user);
        tweetDto.setRetweet(isRetweeted);
        tweetDto.setLiked(isLiked);
        tweetDto.setImage(tweet.getImage());
        tweetDto.setContent(tweet.getContent());
        tweetDto.setCreatedAt(tweet.getCreatedAt());
        tweetDto.setTotalLikes(tweet.getReplyTweets().size());
        tweetDto.setTotalRetweets(tweet.getRetweetUser().size());
        tweetDto.setTotalReplies(tweet.getReplyTweets().size());
        tweetDto.setVideo(tweetDto.getVideo());

        return tweetDto;
    }

    public static TweetDto toTweetDto(Tweet tweet, User reqUser) {

        UserDto user = UserDtoMapper.toUserDto(tweet.getUser());

        boolean isLiked = TweetUtil.isLikedByReqUser(reqUser, tweet);

        boolean isRetweeted = TweetUtil.isRetweetByReqUser(reqUser, tweet);
        List<Long> retweetUserId = new ArrayList<>();

        for (User user1 : tweet.getRetweetUser()) {
            retweetUserId.add(user1.getId());
        }

        TweetDto tweetDto = new TweetDto();

        tweetDto.setId(tweet.getId());
        tweetDto.setUser(user);
        tweetDto.setRetweet(isRetweeted);
        tweetDto.setLiked(isLiked);
        tweetDto.setImage(tweet.getImage());
        tweetDto.setContent(tweet.getContent());
        tweetDto.setCreatedAt(tweet.getCreatedAt());
        tweetDto.setTotalLikes(tweet.getReplyTweets().size());
        tweetDto.setTotalRetweets(tweet.getRetweetUser().size());
        tweetDto.setTotalReplies(tweet.getReplyTweets().size());
        tweetDto.setReplyTweets(toTweetDtos(tweet.getReplyTweets(), reqUser));
        tweetDto.setVideo(tweetDto.getVideo());

        return tweetDto;
    }

    public static List<TweetDto> toTweetDtos(List<Tweet> tweets, User reqUser) {

        List<TweetDto> tweetDtos = new ArrayList<>();

        for (Tweet tweet : tweets) {

            TweetDto tweetDto = toReplyTweetDto(tweet, reqUser);
            tweetDtos.add(tweetDto);
        }
        return tweetDtos;
    }
}
