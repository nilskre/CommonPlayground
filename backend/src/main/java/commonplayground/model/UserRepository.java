package commonplayground.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findAllByEmail(String email);
    User findAllById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
}
