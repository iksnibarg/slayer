package pl.grabinski.slayer;

import org.openstack4j.model.compute.Server;
import org.springframework.stereotype.Component;
import pl.grabinski.slayer.openstack.OpenStackComputeService;
import pl.grabinski.slayer.openstack.OpenStackImageService;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class InstanceFactory {

    private final OpenStackComputeService openStackComputeService;
    private final OpenStackImageService openStackImageService;

    public InstanceFactory(OpenStackComputeService openStackComputeService, OpenStackImageService openStackImageService) {
        this.openStackComputeService = openStackComputeService;
        this.openStackImageService = openStackImageService;
    }

    public Instance fromServer(Server server) {
        Instance instance = new Instance();
        instance.setId(server.getId());
        instance.setName(server.getName());
        instance.setImageName(openStackImageService.getImage(server.getImageId()).getName());
        instance.setFlavor(getFlavor(server.getFlavorId()));
        instance.setCreated(OffsetDateTime.ofInstant(server.getCreated().toInstant(), ZoneOffset.UTC));
        instance.setStatus(server.getStatus().name());
        return instance;
    }

    private Flavor getFlavor(String flavorId) {
        org.openstack4j.model.compute.Flavor osFlavor = openStackComputeService.getFlavor(flavorId);
        Flavor flavor = new Flavor();
        flavor.setId(osFlavor.getId());
        flavor.setName(osFlavor.getName());
        flavor.setVcpus(osFlavor.getVcpus());
        flavor.setRam(osFlavor.getRam());
        flavor.setDisk(osFlavor.getDisk());
        flavor.setSwap(osFlavor.getSwap());
        return flavor;
    }
}
