package pl.grabinski.slayer;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface InstanceRepository extends PagingAndSortingRepository<Instance, String> {

    List<Instance> findAll(Sort sort);

    @Modifying
    @Transactional
    void deleteByIdNotIn(Collection<String> ids);
}
