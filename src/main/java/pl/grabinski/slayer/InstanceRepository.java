package pl.grabinski.slayer;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InstanceRepository extends Repository<Instance, String> {

    Optional<Instance> findOne(String id);
    List<Instance> findAll(Sort sort);
    void save(Instance instance);

    @Modifying
    @Transactional
    void deleteByIdNotIn(Collection<String> ids);
}
