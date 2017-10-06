package pl.grabinski.slayer;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class InstanceService {

    public List<Instance> findAll() {

        return Arrays.asList(
                new Instance("11111", "First instance", OffsetDateTime.now().minus(Duration.ofDays(3)), "ACTIVE"),
                new Instance("22222", "Second instance", OffsetDateTime.now().minus(Duration.ofDays(2)), "ACTIVE")
        );
    }

}
