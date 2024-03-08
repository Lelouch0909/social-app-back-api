package com.lontsi.devsocialmedia.controllers;


import com.lontsi.devsocialmedia.dao.TweetDto;
import com.lontsi.devsocialmedia.dao.mapper.TweetDtoMapper;
import com.lontsi.devsocialmedia.exception.TweetException;
import com.lontsi.devsocialmedia.exception.UserException;
import com.lontsi.devsocialmedia.models.Tweet;
import com.lontsi.devsocialmedia.models.User;
import com.lontsi.devsocialmedia.request.TweetReplyRequest;
import com.lontsi.devsocialmedia.response.ApiResponse;
import com.lontsi.devsocialmedia.services.TweetService;
import com.lontsi.devsocialmedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TweetDto> createTweet(@RequestBody Tweet req, @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.findUserByJwt(jwt);

        Tweet tweet = tweetService.createTweet(req, user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto, HttpStatus.CREATED);
    }


    @PostMapping("/reply")
    public ResponseEntity<TweetDto> replyTweet(@RequestBody TweetReplyRequest req, @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.findUserByJwt(jwt);

        Tweet tweet = tweetService.createReply(req, user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto, HttpStatus.CREATED);
    }


    @PutMapping("/{tweetId}/retweet")
    public ResponseEntity<TweetDto> retweet(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.findUserByJwt(jwt);

        Tweet tweet = tweetService.retweet(tweetId, user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto, HttpStatus.OK);
    }


    @GetMapping("/{tweetId}")
    public ResponseEntity<TweetDto> findTweetById(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.findUserByJwt(jwt);

        Tweet tweet = tweetService.findById(tweetId);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto, HttpStatus.OK);
    }


    @DeleteMapping("/{tweetId}/delete")
    public ResponseEntity<ApiResponse> deleteTweet(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.findUserByJwt(jwt);

        tweetService.deleteTweetById(tweetId, user.getId());

        ApiResponse res = new ApiResponse("Tweet deleted successfully", true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TweetDto>> getAllTweets(@RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.findUserByJwt(jwt);

        List<Tweet> tweets = tweetService.findAllTweet();

        List<TweetDto> tweetDtos = TweetDtoMapper.toTweetDtos(tweets, user);

        return new ResponseEntity<>(tweetDtos, HttpStatus.OK);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TweetDto>> getUserAllTweets(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.findUserByJwt(jwt);

        List<Tweet> tweets = tweetService.getUserTweet(user);

        List<TweetDto> tweetDtos = TweetDtoMapper.toTweetDtos(tweets, user);

        return new ResponseEntity<>(tweetDtos, HttpStatus.OK);
    }

    @GetMapping(
            "/user/{userId}/likes")
    public ResponseEntity<List<TweetDto>> findTweetByLikesContainsUser(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.findUserByJwt(jwt);

        List<Tweet> tweets = tweetService.findByLikesContainsUser(user);

        List<TweetDto> tweetDtos = TweetDtoMapper.toTweetDtos(tweets, user);

        return new ResponseEntity<>(tweetDtos, HttpStatus.OK);
    }


}
