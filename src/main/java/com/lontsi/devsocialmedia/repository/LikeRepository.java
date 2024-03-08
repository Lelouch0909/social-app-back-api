package com.lontsi.devsocialmedia.repository;

import com.lontsi.devsocialmedia.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {


    @Query("SELECT I FROM Like I WHERE I.user.id = :userId AND I.tweet.id = :tweetId")
    public Like isLikeExists(@Param("userId") Long userId, @Param("tweetId") Long tweetId);

    @Query("SELECT I FROM Like I WHERE I.tweet.id = :tweetId")
    public List<Like> findByTweetId(@Param("tweetId") Long tweetId);

}
