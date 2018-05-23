package io.github.ankushs92.handler

import groovy.util.logging.Slf4j
import io.github.ankushs92.util.Json
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.ext.dropwizard.MetricsService
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

import javax.annotation.PreDestroy
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import static io.github.ankushs92.constants.HttpHeaderValues.getAPPLICATION_JSON
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE

/**
 * Created by Ankush on 04/05/17.
 */
@Component
@Slf4j
class MetricsHandler implements Handler<RoutingContext> {

    //Since this handler would be returning a big fat JSON, we attempt to keep its serialization out of event loop's path by using a single threaded ExecutorService
    private static final ExecutorService serializationExecutor = Executors.newSingleThreadExecutor()

    private final MetricsService metricsService
    private final Vertx vertx

    MetricsHandler(
            Vertx vertx,
            MetricsService metricsService
    )
    {
        this.metricsService = metricsService
        this.vertx = vertx
    }

    @PreDestroy
    void cleanup() {
        log.info "Closing Executor pool for class {}", MetricsHandler
        serializationExecutor.shutdown()
    }

    @Override
    void handle(RoutingContext rc) {
        def resp = rc.response()
        serializationExecutor.execute {
            def jsonMetrics = metricsService.getMetricsSnapshot(vertx)
            def json = Json.encodePretty(jsonMetrics)
            resp.putHeader(CONTENT_TYPE, APPLICATION_JSON)
                .end(json)
        }
    }
}

