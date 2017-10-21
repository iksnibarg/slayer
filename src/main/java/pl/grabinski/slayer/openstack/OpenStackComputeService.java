package pl.grabinski.slayer.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.grabinski.slayer.util.performance.LogExecutionTime;

import java.util.ArrayList;
import java.util.List;

@Component
@CacheConfig(cacheNames = "flavors")
public class OpenStackComputeService {

    private static final Logger log = LoggerFactory.getLogger(OpenStackComputeService.class);
    private final OpenStackClientFactory openStackClientFactory;

    public OpenStackComputeService(OpenStackClientFactory openStackClientFactory) {
        this.openStackClientFactory = openStackClientFactory;
    }

    @LogExecutionTime
    public List<Server> getServers() {
        OSClient.OSClientV2 osClient = openStackClientFactory.getOsClientV2();
        return new ArrayList<>(osClient.compute().servers().list());
    }

    @Cacheable
    @LogExecutionTime
    public Flavor getFlavor(String id) {
        OSClient.OSClientV2 osClient = openStackClientFactory.getOsClientV2();
        return osClient.compute().flavors().get(id);
    }

    @CacheEvict(allEntries = true)
    @Scheduled(fixedDelayString = "#{${flavor.cache.clearing.interval.seconds} * 1000}")
    public void evictFlavorsCache() {
        log.debug("Clearing flavors cache");
    }
}
