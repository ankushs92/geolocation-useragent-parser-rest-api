package io.github.ankushs92.domain

import com.blueconic.browscap.Capabilities
import groovy.transform.ToString
import io.github.ankushs92.util.Assert

import static com.blueconic.browscap.BrowsCapField.*

@ToString(includePackage = false)
class BrowserCapabilities {

    final String parent
    final String comment
    final String browser
    final String browserBits
    final String browserType
    final String browserMaker
    final String browserModus
    final String browserVersion
    final String browserMajorVersion
    final String browserMinorVersion
    final String platform
    final String platformVersion
    final String platformDesc
    final String platformBits
    final String platformMaker
    final String isAlpha
    final String isBeta
    final String cssVersion
    final String aolVersion
    final String deviceName
    final String deviceMaker
    final String deviceType
    final String devicePointingMethod
    final String deviceCodeName
    final String deviceBrandName
    final String renderingEngineName
    final String renderingEngineVersion
    final String renderingEngineDesc
    final String renderingEngineMaker
    final Boolean isWin16
    final Boolean isWin32
    final Boolean isWin64
    final Boolean isIframes
    final Boolean isFrames
    final Boolean isTables
    final Boolean isCookies
    final Boolean isBackgroundSounds
    final Boolean isJavascript
    final Boolean isVbScript
    final Boolean isJavaApplets
    final Boolean isActiveXControls
    final Boolean isMobileDevice
    final Boolean isTablet
    final Boolean isSyndicationReader
    final Boolean isCrawler
    final Boolean isFake
    final Boolean isAnonymized
    final Boolean isModified
    final Boolean isMasterParent
    final Boolean isLiteMode


    BrowserCapabilities(Capabilities capabilities) {
        Assert.notNull(capabilities, "capabilities cannot be null")
        this.parent = capabilities.getValue(PARENT)
        this.comment = capabilities.getValue(COMMENT)
        this.browser = capabilities.getValue(BROWSER)
        this.browserType = capabilities.getValue(BROWSER_TYPE)
        this.browserBits = capabilities.getValue(BROWSER_BITS)
        this.browserMaker = capabilities.getValue(BROWSER_MAKER)
        this.browserModus = capabilities.getValue(BROWSER_MODUS)
        this.browserVersion = capabilities.getValue(BROWSER_VERSION)
        this.browserMajorVersion = capabilities.browserMajorVersion
        this.browserMinorVersion = capabilities.getValue(BROWSER_MINOR_VERSION)
        this.platform = capabilities.getValue(PLATFORM)
        this.platformVersion = capabilities.getValue(PLATFORM_VERSION)
        this.platformDesc = capabilities.getValue(PLATFORM_DESCRIPTION)
        this.platformBits = capabilities.getValue(PLATFORM_BITS)
        this.platformMaker = capabilities.getValue(PLATFORM_MAKER)
        this.cssVersion = capabilities.getValue(CSS_VERSION)
        this.aolVersion = capabilities.getValue(AOL_VERSION)
        this.deviceName = capabilities.getValue(DEVICE_NAME)
        this.deviceMaker = capabilities.getValue(DEVICE_MAKER)
        this.deviceType = capabilities.getValue(DEVICE_TYPE)
        this.devicePointingMethod = capabilities.getValue(DEVICE_POINTING_METHOD)
        this.deviceCodeName = capabilities.getValue(DEVICE_CODE_NAME)
        this.deviceBrandName = capabilities.getValue(DEVICE_BRAND_NAME)
        this.renderingEngineName = capabilities.getValue(RENDERING_ENGINE_NAME)
        this.renderingEngineVersion = capabilities.getValue(RENDERING_ENGINE_VERSION)
        this.renderingEngineDesc = capabilities.getValue(RENDERING_ENGINE_DESCRIPTION)
        this.renderingEngineMaker = capabilities.getValue(RENDERING_ENGINE_MAKER)

        this.isAlpha = capabilities.getValue(IS_ALPHA)?.toBoolean()
        this.isBeta = capabilities.getValue(IS_BETA)?.toBoolean()
        this.isWin16 = capabilities.getValue(IS_WIN16)?.toBoolean()
        this.isWin32 = capabilities.getValue(IS_WIN32)?.toBoolean()
        this.isWin64 = capabilities.getValue(IS_WIN64)?.toBoolean()
        this.isIframes = capabilities.getValue(IS_IFRAMES).toBoolean()
        this.isFrames = capabilities.getValue(IS_FRAMES).toBoolean()
        this.isTables = capabilities.getValue(IS_TABLES).toBoolean()
        this.isCookies = capabilities.getValue(IS_COOKIES).toBoolean()
        this.isBackgroundSounds = capabilities.getValue(IS_BACKGROUND_SOUNDS).toBoolean()
        this.isJavascript = capabilities.getValue(IS_JAVASCRIPT).toBoolean()
        this.isVbScript = capabilities.getValue(IS_VBSCRIPT).toBoolean()
        this.isJavaApplets = capabilities.getValue(IS_JAVA_APPLETS).toBoolean()
        this.isActiveXControls = capabilities.getValue(IS_ACTIVEX_CONTROLS).toBoolean()
        this.isMobileDevice = capabilities.getValue(IS_MOBILE_DEVICE).toBoolean()
        this.isTablet = capabilities.getValue(IS_TABLET).toBoolean()
        this.isSyndicationReader = capabilities.getValue(IS_SYNDICATION_READER).toBoolean()
        this.isCrawler = capabilities.getValue(IS_CRAWLER).toBoolean()
        this.isFake = capabilities.getValue(IS_FAKE).toBoolean()
        this.isAnonymized = capabilities.getValue(IS_ANONYMIZED).toBoolean()
        this.isModified = capabilities.getValue(IS_MODIFIED).toBoolean()
        this.isMasterParent = capabilities.getValue(IS_MASTER_PARENT).toBoolean()
        this.isLiteMode = capabilities.getValue(IS_LITE_MODE).toBoolean()

    }

}
