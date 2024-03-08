package com.lontsi.devsocialmedia.util;

import com.lontsi.devsocialmedia.models.Like;
import com.lontsi.devsocialmedia.models.Tweet;
import com.lontsi.devsocialmedia.models.User;

public class TweetUtil {

    public final static boolean isLikedByReqUser(User reqUser, Tweet tweet) {

        for (Like like : tweet.getLikes()) {
            if (like.getUser().getId().equals(reqUser.getId())) {
                return true;
            }
        }
        return false;

    }

    public final static boolean isRetweetByReqUser(User reqUser, Tweet tweet) {

        for (User user : tweet.getRetweetUser()) {
            if (user.getId().equals(reqUser.getId())) {
                return true;
            }
        }

        return false;
    }

}
