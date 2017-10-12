package pl.grabinski.slayer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceService {

    private final InstanceRepository instanceRepository;

    public InstanceService(InstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    public List<Instance> findAll() {
        return instanceRepository.findAll();
    }

}
