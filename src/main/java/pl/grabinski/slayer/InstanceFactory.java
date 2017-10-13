package pl.grabinski.slayer;

import org.openstack4j.model.compute.Server;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class InstanceFactory {

    public Instance fromServer(Server server) {
        Instance instance = new Instance();
        instance.setId(server.getId());
        instance.setName(server.getName());
        instance.setImageName(server.getImage().getName());
        instance.setFlavor(getFlavor(server));
        instance.setCreated(OffsetDateTime.ofInstant(server.getCreated().toInstant(), ZoneOffset.UTC));
        instance.setStatus(server.getStatus().name());
        return instance;
    }

    private Flavor getFlavor(Server server) {
        org.openstack4j.model.compute.Flavor osFlavor = server.getFlavor();
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
