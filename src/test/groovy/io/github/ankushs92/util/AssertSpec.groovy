package io.github.ankushs92.util

import io.github.ankushs92.BaseSpockSpec

/**
 * Created by Ankush on 15/04/17.
 */
class AssertSpec extends BaseSpockSpec {

    def "Pass null obj. IllegalArgumentException would be thrown"() {

        when:
        Assert.notNull(null, "Some error msg")

        then:
        thrown(IllegalArgumentException)
    }


    def "Pass in an empty string. IllegalArgumentException will be thrown"() {
        when:
        Assert.nonEmptyString(' ', 'Some msg')
        then:
        thrown(IllegalArgumentException)

    }

    def "Pass in null. IllegalArgumentException will be thrown"() {
        when:
        Assert.nonEmptyString(null, 'Some msg')
        then:
        thrown(IllegalArgumentException)

    }

}
