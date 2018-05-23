package io.github.ankushs92

import io.vertx.core.Verticle
import io.vertx.core.spi.VerticleFactory
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * Created by Ankush on 20/04/17.
 */
@Component
 class SpringVerticleFactory implements VerticleFactory, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
     boolean blockingCreate() {
        // Usually verticle instantiation is fast but since our verticles are Spring Beans,
        // they might depend on other beans/resources which are slow to build/lookup.
        return true;
    }

    @Override
     String prefix() {
        // Just an arbitrary string which must uniquely identify the verticle factory
        return "myapp";
    }

    @Override
     Verticle createVerticle(String verticleName, ClassLoader classLoader) throws Exception {
        // Our convention in this example is to give the class name as verticle name
        def clazz = removePrefix(verticleName);
        return (Verticle) applicationContext.getBean(Class.forName(clazz))
    }

    @Override
     void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
