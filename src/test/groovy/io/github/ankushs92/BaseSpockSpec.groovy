package io.github.ankushs92

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

/**
 * Created by ankushsharma on 21/11/17.
 */
@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BaseSpockSpec extends Specification {


}
