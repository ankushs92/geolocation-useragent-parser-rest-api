package io.github.ankushs92.handler

import groovy.util.logging.Slf4j
import io.github.ankushs92.exception.MandatoryParamMissingException
import io.github.ankushs92.service.UaParserService
import io.github.ankushs92.util.Strings
import io.vertx.core.Handler
import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

import static io.github.ankushs92.constants.HttpHeaderValues.getAPPLICATION_JSON
import static io.github.ankushs92.constants.HttpHeaderValues.getKEEP_ALIVE
import static io.vertx.core.http.HttpHeaders.CONNECTION
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE

@Component
@Slf4j
class UserAgentAnalysisHandler implements Handler<RoutingContext> {

    private final UaParserService uaParserService

    UserAgentAnalysisHandler(UaParserService uaParserService) {
        this.uaParserService = uaParserService
    }

    @Override
    void handle(RoutingContext rc) {
        def req = rc.request()
        def resp = rc.response()
        def userAgent = req.getParam('ua')
        log.debug 'userAgent {} ', userAgent
        validate(userAgent)

        def uaAnalysisFuture = uaParserService.parse(userAgent)
        uaAnalysisFuture.setHandler { ar ->
            if(ar.succeeded()) {
                def browserCapabilities = ar.result()
                log.debug 'BrowserCapabilities {}', browserCapabilities
                def json = Json.encodePrettily(browserCapabilities)
                resp.putHeader(CONTENT_TYPE, APPLICATION_JSON)
                    .putHeader(CONNECTION, KEEP_ALIVE)
                    .end(json)
            }
            else {
                rc.fail(ar.cause())
            }
        }

    }

    private static void validate(String ua) {
        if(!Strings.hasText(ua)) {
            throw new MandatoryParamMissingException("No ua was passed as param")
        }
    }

}
