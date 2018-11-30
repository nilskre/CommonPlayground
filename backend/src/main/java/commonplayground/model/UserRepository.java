package commonplayground.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findAllByUsername(String username);
    User findAllByEmail(String email);
    User findAllById(Long id);
}
