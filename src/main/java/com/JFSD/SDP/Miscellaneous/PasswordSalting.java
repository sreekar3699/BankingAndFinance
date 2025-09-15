package com.JFSD.SDP.Miscellaneous;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordSalting {
	public String hash(String password) {
        String passwordToHash = password;
        String salt = "*#";
        String generatedPassword = null;
        try {
            String saltedPassword = addSaltToPassword(passwordToHash, salt);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(saltedPassword.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String addSaltToPassword(String password, String salt) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            sb.append(password.charAt(i));
            if ((i + 1) % 2 == 0) {
                sb.append(salt);
            }
        }
        return sb.toString();
    }
}