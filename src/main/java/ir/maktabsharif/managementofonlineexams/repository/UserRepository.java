package ir.maktabsharif.managementofonlineexams.repository;

import ir.maktabsharif.managementofonlineexams.model.enums.Role;
import ir.maktabsharif.managementofonlineexams.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> ,
        JpaSpecificationExecutor<User> {
    List<User> findByRole(Role role);
    Page<User> findAll(Specification<User> spec, Pageable pageable);

    Optional<User> findByEmail(String username);

}
