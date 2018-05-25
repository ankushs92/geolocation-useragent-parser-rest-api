package io.github.ankushs92.service.impl

import com.blueconic.browscap.UserAgentParser
import io.github.ankushs92.domain.BrowserCapabilities
import io.github.ankushs92.service.UaParserService
import io.github.ankushs92.util.Assert
import io.vertx.core.Future
import org.springframework.stereotype.Service

@Service
class UaParserServiceImpl implements UaParserService {

    private final UserAgentParser uaParser

    UaParserServiceImpl(UserAgentParser uaParser) {
        this.uaParser = uaParser
    }

    @Override
    Future<BrowserCapabilities> parse(String ua) {
        Assert.nonEmptyString(ua, "userAgent cannot be null")
        def future = Future.future()
        try {
            def capabilities = uaParser.parse(ua)
            future.complete(new BrowserCapabilities(capabilities))
        }
        catch (ex) {
            future.fail(ex)
        }
        future
    }
}
