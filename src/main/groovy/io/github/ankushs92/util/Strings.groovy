package io.github.ankushs92.util

/**
 * Created by Ankush on 11/04/17.
 */
class Strings {

    static final String EMPTY = ""

    /*
    * Does the String have text?
    * if str == null, then no
    * if str has only whitespace after trimming it, then no
    * Otherwise, hell yes!
    * */
    static boolean hasText(String str){
        if(!str){
            return false
        }
        int length = str.trim().length()
        if(length == 0){
            return false
        }
        return true
    }

}
