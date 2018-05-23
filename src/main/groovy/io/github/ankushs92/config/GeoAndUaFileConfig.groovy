package io.github.ankushs92.config

import com.blueconic.browscap.BrowsCapField
import com.blueconic.browscap.UserAgentParser
import com.blueconic.browscap.UserAgentService
import groovy.util.logging.Slf4j
import in.ankushs.dbip.api.DbIpClient
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader

@Configuration
@Slf4j
class GeoAndUaFileConfig {

    @Autowired
    private ResourceLoader resourceLoader

    @Bean(name = "dbIpClient")
    DbIpClient getDbIpClient() throws IOException {
        def is = resourceLoader.getResource("classpath:dbip.csv.gz").inputStream
        def targetFile = resourceLoader.getResource("classpath:dbip-Target.csv.gz").filename
        def file = new File(targetFile)
        FileUtils.copyInputStreamToFile(is, file)
        new DbIpClient(file)
    }

    @Bean
    UserAgentParser uaParser() {
        log.info "Parsing Browscap file"
        //Read extensive documentation here : https://github.com/blueconic/browscap-java
        def fields = [
                BrowsCapField.IS_MASTER_PARENT,
                BrowsCapField.IS_LITE_MODE,
                BrowsCapField.PARENT,
                BrowsCapField.COMMENT,
                BrowsCapField.BROWSER,
                BrowsCapField.BROWSER_TYPE,
                BrowsCapField.BROWSER_BITS,
                BrowsCapField.BROWSER_MAKER,
                BrowsCapField.BROWSER_MODUS,
                BrowsCapField.BROWSER_VERSION,
                BrowsCapField.BROWSER_MAJOR_VERSION,
                BrowsCapField.BROWSER_MINOR_VERSION,
                BrowsCapField.PLATFORM,
                BrowsCapField.PLATFORM_VERSION,
                BrowsCapField.PLATFORM_DESCRIPTION,
                BrowsCapField.PLATFORM_BITS,
                BrowsCapField.PLATFORM_MAKER,
                BrowsCapField.IS_ALPHA,
                BrowsCapField.IS_BETA,
                BrowsCapField.IS_WIN16,
                BrowsCapField.IS_WIN32,
                BrowsCapField.IS_WIN64,
                BrowsCapField.IS_IFRAMES,
                BrowsCapField.IS_FRAMES,
                BrowsCapField.IS_TABLES,
                BrowsCapField.IS_COOKIES,
                BrowsCapField.IS_BACKGROUND_SOUNDS,
                BrowsCapField.IS_JAVASCRIPT,
                BrowsCapField.IS_VBSCRIPT,
                BrowsCapField.IS_JAVA_APPLETS,
                BrowsCapField.IS_ACTIVEX_CONTROLS,
                BrowsCapField.IS_MOBILE_DEVICE,
                BrowsCapField.IS_TABLET,
                BrowsCapField.IS_SYNDICATION_READER,
                BrowsCapField.IS_CRAWLER,
                BrowsCapField.IS_FAKE,
                BrowsCapField.IS_ANONYMIZED,
                BrowsCapField.IS_MODIFIED,
                BrowsCapField.CSS_VERSION,
                BrowsCapField.AOL_VERSION,
                BrowsCapField.DEVICE_NAME,
                BrowsCapField.DEVICE_MAKER,
                BrowsCapField.DEVICE_TYPE,
                BrowsCapField.DEVICE_POINTING_METHOD,
                BrowsCapField.DEVICE_CODE_NAME,
                BrowsCapField.DEVICE_BRAND_NAME,
                BrowsCapField.RENDERING_ENGINE_NAME,
                BrowsCapField.RENDERING_ENGINE_VERSION,
                BrowsCapField.RENDERING_ENGINE_DESCRIPTION,
                BrowsCapField.RENDERING_ENGINE_MAKER
        ].asImmutable()
        def parser = new UserAgentService().loadParser(fields)
        log.info "Parsing of Browscap file finished"
        parser
    }

}
