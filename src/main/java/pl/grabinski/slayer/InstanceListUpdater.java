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

    @Scheduled(fixedDelayString = "#{${instance.list.update.interval.seconds} * 1000}")
    public void updateInstanceList() {
        List<Instance> instances = instanceRetriever.getInstances();
        instanceRepository.deleteByIdNotIn(getIds(instances));
        for (Instance instance : instances) {
            update(instance);
        }
    }

    void update(Instance openStackOriginatedInstance) {
        Instance repositoryInstance = instanceRepository.findOne(openStackOriginatedInstance.getId()).orElseGet(Instance::new);
        copyAttributes(openStackOriginatedInstance, repositoryInstance);
        instanceRepository.save(repositoryInstance);
    }

    private void copyAttributes(Instance source, Instance target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setImageName(source.getImageName());
        target.setFlavor(source.getFlavor());
        target.setCreated(source.getCreated());
        target.setStatus(source.getStatus());
    }

    private List<String> getIds(List<Instance> instances) {
        return instances.stream().map(Instance::getId).collect(Collectors.toList());
    }

}
