package com.example.RedSet.LogIn_SignUp_Pass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class textValidation {
    public static boolean isValid(String str, String regex){
        Pattern fmt = Pattern.compile(regex);
        Matcher matcher = fmt.matcher(str);
        return matcher.matches();
    }
}
