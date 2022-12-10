/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.auth;

import jakarta.inject.Singleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author l5
 */
@Singleton
public class PasswordEncoder {

    BCryptPasswordEncoder b = new BCryptPasswordEncoder();

    public String encode(String s) {
        return b.encode(s);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return b.matches(rawPassword, encodedPassword);
    }

}
