package io.github.ankushs92.util

/**
 * Created by Ankush on 11/04/17.
 */
class Assert {

    static <T> void notNull(T t, String errorMsg){
        if(t == null){
            throw new IllegalArgumentException(errorMsg)
        }
    }

    static void nonEmptyString(String str, String errorMsg){
        if(!Strings.hasText(str)){
            throw new IllegalArgumentException(errorMsg)
        }
    }

}
