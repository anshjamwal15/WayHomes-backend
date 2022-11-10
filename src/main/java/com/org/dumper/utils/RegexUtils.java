package com.org.dumper.utils;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class RegexUtils {

    public static boolean isValidUsername(String name) {

        String regex = "^[A-Za-z, ]++$";

        Pattern p = Pattern.compile(regex);

        if (name == null) {
            return false;
        }

        Matcher m = p.matcher(name);

        return m.matches();
    }

    public static boolean isValidEmail(String email) {

        String regex = "^(.+)@(.+)$";

        Pattern p = Pattern.compile(regex);

        if (email == null) {
            return false;
        }

        Matcher m = p.matcher(email);

        return m.matches();
    }
}
