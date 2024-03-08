package com.lontsi.devsocialmedia.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private String content;

    @OneToMany(mappedBy = "tweet",cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();


    @OneToMany
    private List<Tweet> replyTweets = new ArrayList<>();

    @ManyToMany
    private List<User> retweetUser = new ArrayList<>();


    @ManyToOne
    private Tweet replyFor;

    private boolean isReply;

    private boolean isTweet;

    private LocalDateTime createdAt;
    private String image;
    private String video;



}
