package com.lontsi.devsocialmedia.repository;

import com.lontsi.devsocialmedia.models.Like;
import com.lontsi.devsocialmedia.models.Tweet;
import com.lontsi.devsocialmedia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface  TweetRepository extends JpaRepository<Tweet,Long> {

    List<Tweet> findAllByIsTweetTrueOrderByCreatedAtDesc();

    List<Tweet> findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(User user,Long userId);

    List<Tweet> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("SELECT t FROM Tweet t JOIN t.likes I WHERE I.user.id = :userId")
    List<Tweet> findByLikesUser_id (Long userId);
}
