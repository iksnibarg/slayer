package pl.grabinski.slayer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface InstanceRepository extends CrudRepository<Instance, String> {

    List<Instance> findAll();

    @Modifying
    @Transactional
    void deleteByIdNotIn(Collection<String> ids);
}
