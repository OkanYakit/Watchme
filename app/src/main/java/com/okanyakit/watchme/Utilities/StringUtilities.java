package com.okanyakit.watchme.Utilities;

/**
 * Created by okanyakit on 15/05/15.
 */
public class StringUtilities {

    public static String getString(Object string){
        if(string!=null){
            return string.toString();
        }
        return "";
    }
}
