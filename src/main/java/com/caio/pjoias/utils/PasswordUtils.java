package com.caio.pjoias.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    public static String encode(String password) {
        var bcrypt = new BCryptPasswordEncoder();
        return bcrypt.encode(password);
    }
}
