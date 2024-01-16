package sk2.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.userservice.domain.Role;
import sk2.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    Page<User> findUserByRole(Role role, Pageable pageable);
    User findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);

}
