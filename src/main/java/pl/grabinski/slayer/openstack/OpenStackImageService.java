package pl.grabinski.slayer.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.grabinski.slayer.util.performance.LogExecutionTime;

@Component
@CacheConfig(cacheNames = "images")
public class OpenStackImageService {

    private static final Logger log = LoggerFactory.getLogger(OpenStackImageService.class);
    private final OpenStackClientFactory openStackClientFactory;

    public OpenStackImageService(OpenStackClientFactory openStackClientFactory) {
        this.openStackClientFactory = openStackClientFactory;
    }

    @Cacheable
    @LogExecutionTime
    public Image getImage(String id) {
        OSClient.OSClientV2 osClient = openStackClientFactory.getOsClientV2();
        return osClient.images().get(id);
    }

    @CacheEvict(allEntries = true)
    @Scheduled(fixedDelayString = "#{${image.cache.clearing.interval.seconds} * 1000}")
    public void evictImagesCache() {
        log.debug("Clearing images cache");
    }

}
