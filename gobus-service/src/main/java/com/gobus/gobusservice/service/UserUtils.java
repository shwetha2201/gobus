package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.UserDto;
import com.gobus.gobusservice.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class UserUtils {

    private UserUtils() {
    }

    public static UserDto entityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setAge(user.getAge());
        dto.setFullName(user.getFullName());
        dto.setEmailId(user.getEmailId());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setUserType(user.getUserType());
        return dto;
    }

    public static User dtoToEntity(UserDto userdto) throws NoSuchAlgorithmException {
        User user = new User();
        user.setAge(userdto.getAge());
        user.setFullName(userdto.getFullName());
        user.setEmailId(userdto.getEmailId());
        user.setPhoneNumber(userdto.getPhoneNumber());
        user.setUserType(userdto.getUserType());
        user.setPassword(convertToMD5(userdto.getPassword()));
        return user;
    }

    public static void copyFromDto(UserDto dto, User user) {
        user.setAge(dto.getAge());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setFullName(dto.getFullName());
    }

    public static String convertToMD5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(data.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }
}
