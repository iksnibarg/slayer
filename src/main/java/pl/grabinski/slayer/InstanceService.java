package pl.grabinski.slayer;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceService {

    private final InstanceRepository instanceRepository;

    public InstanceService(InstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    public List<Instance> findAll() {
        return instanceRepository.findAll(new Sort("created"));
    }

    public void updateNotes(String id, String notes) {
        instanceRepository.findOne(id).ifPresent(instance -> {
            instance.setNotes(notes);
            instanceRepository.save(instance);
        });
    }
}
