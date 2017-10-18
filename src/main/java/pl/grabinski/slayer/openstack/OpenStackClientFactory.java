package pl.grabinski.slayer.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Component;
import pl.grabinski.slayer.util.performance.LogExecutionTime;

@Component
public class OpenStackClientFactory {

    private final OpenStackCredentials openStackCredentials;

    public OpenStackClientFactory(OpenStackCredentials openStackCredentials) {
        this.openStackCredentials = openStackCredentials;
    }

    @LogExecutionTime
    public OSClient.OSClientV2 getOsClientV2() {
        return OSFactory.builderV2()
                .endpoint(openStackCredentials.getAuthUrl())
                .credentials(openStackCredentials.getUsername(), openStackCredentials.getPassword())
                .tenantName(openStackCredentials.getTenantName())
                .authenticate();
    }
}
