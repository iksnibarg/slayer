package pl.grabinski.slayer;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstanceService {

    private final OpenStackClientFactory openStackClientFactory;
    private final InstanceFactory instanceFactory;

    public InstanceService(OpenStackClientFactory openStackClientFactory, InstanceFactory instanceFactory) {
        this.openStackClientFactory = openStackClientFactory;
        this.instanceFactory = instanceFactory;
    }

    public List<Instance> findAll() {
        OSClient.OSClientV2 osClient = openStackClientFactory.getOsClientV2();
        List<? extends Server> servers = osClient.compute().servers().list();
        return servers.stream().map(instanceFactory::fromServer).collect(Collectors.toList());
    }

}
