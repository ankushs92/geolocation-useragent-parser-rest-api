package io.github.ankushs92

import groovy.util.logging.Slf4j
import io.github.ankushs92.verticle.ApiVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import javax.annotation.PostConstruct

@SpringBootApplication
@Slf4j
class GeoUaParserApiApplication {

	@Autowired
	private Vertx vertx

	@Autowired
	private SpringVerticleFactory springVerticleFactory

	static void main(String[] args) {
		SpringApplication.run GeoUaParserApiApplication, args
	}

	@PostConstruct
	void init() {
		vertx.registerVerticleFactory(springVerticleFactory)
		def cores = Runtime.runtime.availableProcessors()
		def numOfVerticlesToDeploy = cores * 2
		log.info "Number of cores available {} " , cores
		log.info "Deploying {} Verticles ", numOfVerticlesToDeploy
		def options = new DeploymentOptions().setInstances(cores)
		vertx.deployVerticle(springVerticleFactory.prefix() + ":" + ApiVerticle.class.name, options,{ deployment->
			if(deployment.succeeded()) {
				log.info "Deployment successful. Deployment Info {} ", deployment.result()
			}
			else {
				log.error "Deployment Failed with exception ", deployment.cause()
			}
		})
	}
}
