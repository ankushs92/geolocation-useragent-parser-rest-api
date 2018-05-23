package io.github.ankushs92.service

import io.github.ankushs92.domain.BrowserCapabilities
import io.vertx.core.Future

interface UaParserService {

    /**
     * Parse a user agent string and get details
     * @param ua the user agent to parse
     * @return A Future containing a BrowserCapabilities object, or an error if the op failed
     */
    Future<BrowserCapabilities> parse(String ua)
}
