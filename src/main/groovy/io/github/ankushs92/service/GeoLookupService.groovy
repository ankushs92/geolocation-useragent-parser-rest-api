package io.github.ankushs92.service

import in.ankushs.dbip.api.GeoEntity
import io.vertx.core.Future

interface GeoLookupService {

    /**
     * Resolve an ip to its geolocation
     * @param ip the ip
     * @return A Future containing geolocation info as a GeoEntity object, or an error if the op failed
     */
    Future<GeoEntity> lookup(String ip)

}
