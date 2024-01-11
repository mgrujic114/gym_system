package sk2.userservice.runner;

import sk2.userservice.domain.Admin;
import sk2.userservice.domain.Role;
import sk2.userservice.domain.User;
import sk2.userservice.domain.UserStatus;
import sk2.userservice.repository.RoleRepository;
import sk2.userservice.repository.UserRepository;
import sk2.userservice.repository.UserStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private UserStatusRepository userStatusRepository;

    public TestDataRunner(RoleRepository roleRepository, UserRepository userRepository, UserStatusRepository userStatusRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Insert roles
        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleManager = new Role("ROLE_MANAGER", "Manager role");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleManager);
        //Insert admin
        User admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);
        userRepository.save(admin);
        //User statuses
        userStatusRepository.save(new UserStatus(0, 8, 0));
        userStatusRepository.save(new UserStatus(9, 16, 10));
        userStatusRepository.save(new UserStatus(17, 22, 20));
    }
}
