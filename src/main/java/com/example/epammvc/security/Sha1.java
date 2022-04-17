package com.example.epammvc.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1 {
    public Sha1() {

    }
    public String cipher(String password){
        MessageDigest sha1= null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes=sha1.digest(password.getBytes());
        StringBuilder builder=new StringBuilder();
        for(byte cipherPassword:bytes){
            builder.append(String.format("%02X",cipherPassword));
        }
        return builder.toString();
    }
}
