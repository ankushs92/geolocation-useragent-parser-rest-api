package io.github.ankushs92.handler

import groovy.util.logging.Slf4j
import io.github.ankushs92.exception.MandatoryParamMissingException
import io.github.ankushs92.service.GeoLookupService
import io.github.ankushs92.service.impl.UaParserServiceImpl
import io.github.ankushs92.util.Strings
import io.vertx.core.CompositeFuture
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
class GeoAndUserAgentAnalysisHandler implements Handler<RoutingContext> {

    private final GeoLookupService geoLookupService
    private final UaParserServiceImpl uaParserService

    GeoAndUserAgentAnalysisHandler(
            GeoLookupService geoLookupService,
            UaParserServiceImpl uaParserService
    )
    {
        this.geoLookupService = geoLookupService
        this.uaParserService = uaParserService
    }

    @Override
    void handle(RoutingContext rc) {
        def req = rc.request()
        def resp = rc.response()

        def ip = req.getParam('ip')
        def userAgent = req.getParam('ua')
        validate(ip, userAgent)

        def geoFuture = geoLookupService.lookup(ip)
        def uaAnalysisFuture = uaParserService.parse(userAgent)

        CompositeFuture.all(geoFuture, uaAnalysisFuture)
                       .setHandler { ar ->
                            if(ar.succeeded()) {
                                def results = ar.result().list()
                                def geo = results[0]
                                def browserCapabilities = results[1]

                                log.debug 'Geo {}', geo
                                log.debug 'BrowserCapabilities {}', browserCapabilities

                                def json = Json.encodePrettily([
                                        geo : geo,
                                        uaAnalysis : browserCapabilities
                                ])
                                resp.putHeader(CONTENT_TYPE, APPLICATION_JSON)
                                    .putHeader(CONNECTION, KEEP_ALIVE)
                                    .end(json)
                            }
                            else {
                                rc.fail(ar.cause())
                            }
                       }

    }


    private static void validate(String ip, String userAgent) {
        if(!(Strings.hasText(ip) && Strings.hasText(userAgent))) {
            throw new MandatoryParamMissingException("One or both out of required params were missing : ip , ua")
        }
    }
}
