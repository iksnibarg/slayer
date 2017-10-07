package pl.grabinski.slayer;

import org.openstack4j.api.OSClient;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Component;

@Component
public class OpenStackClientFactory {

    private final OpenStackCredentials openStackCredentials;

    public OpenStackClientFactory(OpenStackCredentials openStackCredentials) {
        this.openStackCredentials = openStackCredentials;
    }

    public OSClient.OSClientV2 getOsClientV2() {
        return OSFactory.builderV2()
                .endpoint(openStackCredentials.getAuthUrl())
                .credentials(openStackCredentials.getUsername(), openStackCredentials.getPassword())
                .tenantName(openStackCredentials.getTenantName())
                .authenticate();
    }
}
