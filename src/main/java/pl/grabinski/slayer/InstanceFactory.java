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
        instance.setCreated(OffsetDateTime.ofInstant(server.getCreated().toInstant(), ZoneOffset.UTC));
        instance.setStatus(server.getStatus().name());
        return instance;
    }
}
