package com.lontsi.devsocialmedia.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String location;
    private String website;
    private String birthDate;
    private String email;
    private String password;
    private String mobile;
    private String image;
    private String backgroundImage;
    private String bio;
    private boolean req_user;
    private boolean login_with_google;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Tweet> tweets = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @Embedded
    private  Verification verification;

    @ManyToMany
    @JsonIgnore
    private List<User> followers = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<User> followings = new ArrayList<>();



}
