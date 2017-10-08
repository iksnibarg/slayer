package pl.grabinski.slayer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstanceListUpdater {

    private final InstanceRetriever instanceRetriever;
    private final InstanceRepository instanceRepository;

    public InstanceListUpdater(InstanceRetriever instanceRetriever, InstanceRepository instanceRepository) {
        this.instanceRetriever = instanceRetriever;
        this.instanceRepository = instanceRepository;
    }

    @Scheduled(fixedDelay = 10000)
    public void updateInstanceList() {
        List<Instance> instances = instanceRetriever.getInstances();
        instanceRepository.deleteByIdNotIn(getIds(instances));
        instances.forEach(instanceRepository::save);
    }

    private List<String> getIds(List<Instance> instances) {
        return instances.stream().map(Instance::getId).collect(Collectors.toList());
    }

}
