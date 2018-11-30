package commonplayground.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findAllByUsername(String username);
    User findAllById(Long id);
}
