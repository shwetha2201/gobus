package com.gobus.gobusservice.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class GobusPasswordEncoder implements PasswordEncoder  {

    @Override
    public String encode(CharSequence rawPassword) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] messageDigest = md.digest(rawPassword.toString().getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
