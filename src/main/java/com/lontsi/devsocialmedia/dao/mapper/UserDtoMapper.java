package com.lontsi.devsocialmedia.dao.mapper;

import com.lontsi.devsocialmedia.dao.UserDto;
import com.lontsi.devsocialmedia.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {

    public static UserDto toUserDto(User user) {

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setImage(user.getImage());
        dto.setBackgroundImage(user.getBackgroundImage());
        dto.setBio(user.getBio());
        dto.setBirthDate(user.getBirthDate());
        dto.setFollowers(toUserDtos(user.getFollowers()));
        dto.setFollowings(toUserDtos(user.getFollowings()));
        dto.setLogin_with_google(user.isLogin_with_google());
        dto.setLocation(user.getLocation());
        //dto.setVerified(false);
        return dto;
    }

    public static List<UserDto> toUserDtos(List<User> followers) {
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : followers) {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setFullName(user.getFullName());
            dto.setImage(user.getImage());
            userDtos.add(dto);
        }
        return userDtos;
    }
}
