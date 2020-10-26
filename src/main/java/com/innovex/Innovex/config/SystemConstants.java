/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.config;

/**
 *
 * @author
 */
public class SystemConstants {
    // Regex for acceptable logins

    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "System";
    public static final String ANONYMOUS_USER = "Anonymous";
    public static final String DEFAULT_LANGUAGE = "English";
    /**
     * Representing Date with pattern "yyyy-MM-dd"
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * Representing Time with pattern "HH:mm:ss"
     */
    public static final String TIME_PATTERN = "HH:mm:ss";
    /**
     * Representing Date and time with pattern "yyyy-MM-dd HH:mm:ss"
     */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private SystemConstants() {
    }
}
