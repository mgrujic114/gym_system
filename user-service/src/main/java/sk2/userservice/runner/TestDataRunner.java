package sk2.userservice.runner;

import sk2.userservice.domain.*;
import sk2.userservice.repository.RoleRepository;
import sk2.userservice.repository.UserRepository;
import sk2.userservice.repository.UserStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        //Insert users
        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);
        userRepository.save(admin);

        Manager manager = new Manager();
        manager.setEmail("manager@gmail.com");
        manager.setUsername("manager");
        manager.setPassword("manager");
        manager.setRole(roleManager);
        manager.setGym("Gym");
        manager.setDateOfEmployment(LocalDate.parse("01/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        userRepository.save(manager);

        Client client = new Client();
        client.setEmail("client@gmail.com");
        client.setUsername("client");
        client.setPassword("client");
        client.setRole(roleUser);
        client.setNumberOfReservations(7);
        client.setRestricted(false);
        userRepository.save(client);
        //User statuses
        userStatusRepository.save(new UserStatus(0, 8, 0));
        userStatusRepository.save(new UserStatus(9, 16, 10));
        userStatusRepository.save(new UserStatus(17, 22, 20));
    }
}
