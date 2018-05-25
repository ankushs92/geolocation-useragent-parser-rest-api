package io.github.ankushs92.handler

import groovy.json.JsonSlurper
import io.github.ankushs92.BaseSpockSpec

/**
 * Tests out different error responses
 * Created by ankushsharma on 01/02/18.
 */
class FailureHandlerSpec extends BaseSpockSpec {

    def "Pass in a creative id value to a macro. Creative doesn't exist in the system, so 'creative with id doesnt exist' error msg should be sent"() {

        when:
        def creativeId = '4444'
        def urlConn = new URL("http://localhost:8080/?cr_id={creative_id}".replace("{creative_id}", creativeId))


        then:
        urlConn.text.contains("doesnt exist")
    }

    def "Simulating an Internal Server Error message by sending an invalid creative id(a valid creative id is of type int)"() {

        when:
        def creativeId = 'Invalid'
        def urlConn = new URL("http://localhost:8080/?cr_id={creative_id}".replace("{creative_id}", creativeId))

        then:
        def errorMsg = new JsonSlurper().parseText(urlConn.text).msg
        errorMsg == GlobalFailureHandler.GLOBAL_ERROR_MSG

    }
}
