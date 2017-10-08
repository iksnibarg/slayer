package pl.grabinski.slayer.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenStackComputeService {

    private final OpenStackClientFactory openStackClientFactory;

    public OpenStackComputeService(OpenStackClientFactory openStackClientFactory) {
        this.openStackClientFactory = openStackClientFactory;
    }

    public List<? extends Server> getServers() {
        OSClient.OSClientV2 osClient = openStackClientFactory.getOsClientV2();
        return osClient.compute().servers().list();
    }
}
