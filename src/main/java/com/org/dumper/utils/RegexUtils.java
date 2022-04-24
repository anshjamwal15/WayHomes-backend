package com.org.dumper.utils;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class RegexUtils {

    public static boolean isValidUsername(String name)
    {

        // Regex to check valid username.
        String regex = "^[A-Za-z, ]++$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the username is empty
        // return false
        if (name == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given username
        // and regular expression.
        Matcher m = p.matcher(name);

        // Return if the username
        // matched the ReGex
        return m.matches();
    }

    public static boolean isValidEmail(String email) {

        String regex = "^(.+)@(.+)$";

        Pattern p = Pattern.compile(regex);

        // If the username is empty
        // return false
        if (email == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given username
        // and regular expression.
        Matcher m = p.matcher(email);

        // Return if the username
        // matched the ReGex
        return m.matches();
    }
}
