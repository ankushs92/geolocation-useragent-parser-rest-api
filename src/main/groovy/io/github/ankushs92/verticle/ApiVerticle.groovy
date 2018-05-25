package io.github.ankushs92.verticle

import io.github.ankushs92.handler.*
import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class ApiVerticle extends AbstractVerticle {

    private final Router router
    private final GeoHandler geoHandler
    private final UserAgentAnalysisHandler userAgentAnalysisHandler
    private final GeoAndUserAgentAnalysisHandler geoAndUserAgentAnalysisHandler
    private final GlobalFailureHandler failureHandler
    private final MetricsHandler metricsHandler

    ApiVerticle(
            Router router,
            GeoHandler geoHandler,
            UserAgentAnalysisHandler userAgentAnalysisHandler,
            GeoAndUserAgentAnalysisHandler geoAndUserAgentAnalysisHandler,
            GlobalFailureHandler failureHandler,
            MetricsHandler metricsHandler
    ) {
        this.router = router
        this.geoHandler = geoHandler
        this.userAgentAnalysisHandler = userAgentAnalysisHandler
        this.geoAndUserAgentAnalysisHandler = geoAndUserAgentAnalysisHandler
        this.failureHandler = failureHandler
        this.metricsHandler = metricsHandler
    }


    @Override
    void start() {

        router.get("/geo")
                .handler(geoHandler)
                .failureHandler(failureHandler)

        router.get('/metrics')
                .handler(metricsHandler)
                .failureHandler(failureHandler)

        router.get('/uaAnalysis')
                .handler(userAgentAnalysisHandler)
                .failureHandler(failureHandler)

        router.get('/geoAndUserAgentAnalysis')
                .handler(geoAndUserAgentAnalysisHandler)
                .failureHandler(failureHandler)

    }
}
