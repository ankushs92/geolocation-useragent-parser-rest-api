package io.github.ankushs92.util

import io.github.ankushs92.BaseSpockSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment

/**
 * Created by ankushsharma on 30/01/18.
 */
class EnvironmentUtilSpec extends BaseSpockSpec {

    @Autowired
    private Environment env

    def "Current running profile is 'dev'."() {
        when:
        def result = env.getActiveProfiles()

        then:
        result.find() == 'dev'

    }


}
