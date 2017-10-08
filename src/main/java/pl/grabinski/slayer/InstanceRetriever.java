package pl.grabinski.slayer;

import org.openstack4j.model.compute.Server;
import org.springframework.stereotype.Component;
import pl.grabinski.slayer.openstack.OpenStackComputeService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstanceRetriever {

    private final OpenStackComputeService openStackComputeService;
    private final InstanceFactory instanceFactory;

    public InstanceRetriever(OpenStackComputeService openStackComputeService, InstanceFactory instanceFactory) {
        this.openStackComputeService = openStackComputeService;
        this.instanceFactory = instanceFactory;
    }


    public List<Instance> getInstances() {
        List<? extends Server> servers = openStackComputeService.getServers();
        return servers.stream().map(instanceFactory::fromServer).collect(Collectors.toList());
    }
}
