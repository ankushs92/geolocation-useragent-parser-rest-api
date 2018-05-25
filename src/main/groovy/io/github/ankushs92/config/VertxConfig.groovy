package io.github.ankushs92.config

import groovy.util.logging.Slf4j
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.http.HttpServer
import io.vertx.ext.dropwizard.DropwizardMetricsOptions
import io.vertx.ext.web.Router
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by Ankush on 07/03/17.
 */
@Configuration
@Slf4j
class VertxConfig {

    @Bean("vertx")
    Vertx vertx() {
        Vertx.vertx(
                new VertxOptions()
                        .setMetricsOptions(
                        new DropwizardMetricsOptions()
                                .setEnabled(true)
                )
                //TODO: This is a hack, and not a good idea. I'm doing this to avoid exceptions that will most definitely be thrown by Vert.x
                //while loading the dbip file
                        .setBlockedThreadCheckInterval(100000000)
        )
    }

    @Bean
    HttpServer httpServer() {
        vertx().createHttpServer().requestHandler(router().&accept).listen(8080)
    }

    @Bean
    Router router() {
        Router.router(vertx())
                .exceptionHandler { ex ->
            log.error 'Unhandled {}', ex
        }
    }

}
