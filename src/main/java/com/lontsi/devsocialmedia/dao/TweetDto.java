package com.lontsi.devsocialmedia.dao;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class TweetDto {

    private Long id;

    private UserDto user;

    private String content;

    private List<TweetDto> replyTweets;

    private List<Long> retweetUsersId;

    private boolean isRetweet;


    private boolean isLiked;


    private LocalDateTime createdAt;
    private String image;
    private String video;

    private int totalLikes;
    private int totalRetweets;

    private int totalReplies;

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
