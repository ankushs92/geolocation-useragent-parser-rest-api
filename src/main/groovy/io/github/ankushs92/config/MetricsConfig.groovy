package io.github.ankushs92.config

import io.vertx.core.Vertx
import io.vertx.ext.dropwizard.MetricsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by Ankush on 11/04/17.
 */
@Configuration
class MetricsConfig {

    @Autowired
    private Vertx vertx

    @Bean("metricsService")
    MetricsService metrics(){
        MetricsService.create(vertx)
    }
}
