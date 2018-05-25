package io.github.ankushs92.util


import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Created by Ankush on 11/04/17.
 */
class Json {

    //Reasons for static initialization, check out this link:
    //https://stackoverflow.com/questions/18611565/how-do-i-correctly-reuse-jackson-objectmapper
    private static final ObjectMapper objectMapper = new ObjectMapper()

    /**
     * Serialize a POJO/Map to a pretty JSON String
     * @param object The Object to convert to a pretty JSON string
     * @return The JSON representation of @param object
     * @throws Exception
     */
    static String encodePretty(Object object) throws Exception {
        Assert.notNull(object, "object cannot be null");
        return objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object)
    }

    /**
     * Serialize a POJO/Map to JSON String
     * @param object The Object to convert to a JSON string
     * @return The JSON representation of @param object
     * @throws Exception
     */
    static String encode(Object object) throws Exception {
        Assert.notNull(object, "object cannot be null")
        return objectMapper
                .writeValueAsString(object)
    }

}

