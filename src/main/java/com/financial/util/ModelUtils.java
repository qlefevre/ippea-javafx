package com.financial.util;

public class ModelUtils {

    public static String unescape(String line){
        return line.replace('\"',' ').trim();
    }

    public static String defaultString(String string, String defaultString){
        return string != null ? string : defaultString ;
    }
}
