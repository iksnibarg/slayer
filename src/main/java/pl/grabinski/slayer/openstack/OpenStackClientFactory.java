package pl.grabinski.slayer.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.grabinski.slayer.util.performance.LogExecutionTime;

import java.time.Duration;
import java.time.Instant;

@Component
@CacheConfig(cacheNames = "osclient")
public class OpenStackClientFactory {

    private static final Logger log = LoggerFactory.getLogger(OpenStackClientFactory.class);
    private final OpenStackCredentials openStackCredentials;
    private final long cacheClearingInterval;

    public OpenStackClientFactory(OpenStackCredentials openStackCredentials,
                                  @Value("${os.client.cache.clearing.interval.seconds}") long cacheClearingInterval) {
        this.openStackCredentials = openStackCredentials;
        this.cacheClearingInterval = cacheClearingInterval;
    }

    @Cacheable
    @LogExecutionTime
    public OSClient.OSClientV2 getOsClientV2() {
        OSClient.OSClientV2 osClient = OSFactory.builderV2()
                .endpoint(openStackCredentials.getAuthUrl())
                .credentials(openStackCredentials.getUsername(), openStackCredentials.getPassword())
                .tenantName(openStackCredentials.getTenantName())
                .authenticate();

        verifyIfClientCanBeCached(osClient);

        return osClient;
    }

    private void verifyIfClientCanBeCached(OSClient.OSClientV2 osClient) {
        Instant tokenExpiryTime = osClient.getAccess().getToken().getExpires().toInstant();
        long tokenValidity = Duration.between(Instant.now(), tokenExpiryTime).getSeconds();
        if (tokenValidity < cacheClearingInterval) {
            log.error("OpenStack auth token validity ({} seconds) is smaller than cache clearing interval ({} seconds).",
                    tokenValidity, cacheClearingInterval);
        }
    }

    @CacheEvict(allEntries = true)
    @Scheduled(fixedDelayString = "#{${os.client.cache.clearing.interval.seconds} * 1000}")
    public void evictOsClientCache() {
        log.debug("Clearing OS Client cache");
    }
}
