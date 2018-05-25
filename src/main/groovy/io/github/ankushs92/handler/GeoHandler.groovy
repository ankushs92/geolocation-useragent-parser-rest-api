package io.github.ankushs92.handler

import groovy.util.logging.Slf4j
import io.github.ankushs92.exception.MandatoryParamMissingException
import io.github.ankushs92.service.GeoLookupService
import io.github.ankushs92.util.Strings
import io.vertx.core.Handler
import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

import static io.github.ankushs92.constants.HttpHeaderValues.APPLICATION_JSON
import static io.github.ankushs92.constants.HttpHeaderValues.KEEP_ALIVE
import static io.vertx.core.http.HttpHeaders.CONNECTION
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE

@Component
@Slf4j
class GeoHandler implements Handler<RoutingContext> {

    private final GeoLookupService geoLookupService

    GeoHandler(GeoLookupService geoLookupService) {
        this.geoLookupService = geoLookupService
    }

    @Override
    void handle(RoutingContext rc) {
        def req = rc.request()
        def resp = rc.response()
        def ip = req.getParam('ip')
        log.debug "Ip {}", ip
        validate(ip)

        def geoFuture = geoLookupService.lookup(ip)
        geoFuture.setHandler { ar ->
            if (ar.succeeded()) {
                def geo = ar.result()
                log.debug 'GeoEntity {}', geo
                def json = Json.encodePrettily(geo)
                resp.putHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .putHeader(CONNECTION, KEEP_ALIVE)
                        .end(json)
            } else {
                rc.fail(ar.cause())
            }
        }

    }

    private static void validate(String ip) {
        if (!Strings.hasText(ip)) {
            throw new MandatoryParamMissingException("No ip was passed as param")
        }
    }
}
