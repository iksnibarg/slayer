package pl.grabinski.slayer;

import org.openstack4j.model.compute.Server;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.grabinski.slayer.openstack.OpenStackComputeService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstanceListUpdater {

    private final OpenStackComputeService openStackComputeService;
    private final InstanceFactory instanceFactory;
    private final InstanceRepository instanceRepository;

    public InstanceListUpdater(OpenStackComputeService openStackComputeService, InstanceFactory instanceFactory, InstanceRepository instanceRepository) {
        this.openStackComputeService = openStackComputeService;
        this.instanceFactory = instanceFactory;
        this.instanceRepository = instanceRepository;
    }


    @Scheduled(fixedDelay = 10000)
    public void updateInstanceList() {
        List<? extends Server> servers = openStackComputeService.getServers();
        instanceRepository.deleteByIdNotIn(
                servers.stream().map(Server::getId).collect(Collectors.toList()));
        servers.stream().map(instanceFactory::fromServer).forEach(
                instance -> instanceRepository.save(instance));
    }

}
