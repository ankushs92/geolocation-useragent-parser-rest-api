package io.github.ankushs92.service.impl

import in.ankushs.dbip.api.DbIpClient
import in.ankushs.dbip.api.GeoEntity
import io.github.ankushs92.service.GeoLookupService
import io.github.ankushs92.util.Assert
import io.vertx.core.Future
import org.springframework.stereotype.Service


@Service
class GeoLookupServiceImpl implements GeoLookupService {

    private final DbIpClient dbIpClient

    GeoLookupServiceImpl(DbIpClient dbIpClient) {
        this.dbIpClient = dbIpClient
    }

    @Override
    Future<GeoEntity> lookup(String ip) {
        Assert.nonEmptyString(ip, "ip cannot be null or empty")
        def future = Future.future()
        try {
            def geoEntity = dbIpClient.lookup(ip)
            future.complete(geoEntity)
        }
        catch (ex) {
            future.fail(ex)
        }
        future
    }
}
