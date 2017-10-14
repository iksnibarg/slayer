package pl.grabinski.slayer;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Instance instance = instanceRepository.findOne(id);
        if(instance != null) {
            instance.setNotes(notes);
            instanceRepository.save(instance);
        }
    }
}
