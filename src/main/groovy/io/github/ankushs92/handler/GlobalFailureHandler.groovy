package io.github.ankushs92.handler

import groovy.util.logging.Slf4j
import io.github.ankushs92.util.Json
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Created by Ankush on 26/06/17.
 */
@Component
@Slf4j
class GlobalFailureHandler implements Handler<RoutingContext> {

    @Override
    void handle(RoutingContext rq) {
        def response = rq.response()
        def error = rq.failure()
        log.error "DateTime {},Error : {}", LocalDateTime.now(ZoneId.of('Asia/Kolkata')), error.message
        //In case of Application error, we print the message on screen as String
        response
                .setStatusCode(500)
                .end(Json.encodePretty([message: error.message]))

    }
}
