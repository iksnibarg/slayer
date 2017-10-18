package pl.grabinski.slayer;

import org.openstack4j.model.compute.Server;
import org.springframework.stereotype.Component;
import pl.grabinski.slayer.openstack.OpenStackComputeService;
import pl.grabinski.slayer.util.performance.LogExecutionTime;

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


    @LogExecutionTime
    public List<Instance> getInstances() {
        List<Server> servers = openStackComputeService.getServers();
        return servers.stream().map(instanceFactory::fromServer).collect(Collectors.toList());
    }
}
