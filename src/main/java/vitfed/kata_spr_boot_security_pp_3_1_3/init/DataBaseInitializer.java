package vitfed.kata_spr_boot_security_pp_3_1_3.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vitfed.kata_spr_boot_security_pp_3_1_3.models.Role;
import vitfed.kata_spr_boot_security_pp_3_1_3.models.User;
import vitfed.kata_spr_boot_security_pp_3_1_3.repository.RoleRepository;
import vitfed.kata_spr_boot_security_pp_3_1_3.repository.UserRepository;


import javax.annotation.PostConstruct;
import java.util.Set;

@Configuration
public class DataBaseInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataBaseInitializer(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);


        User adminUser = new User( "admin@mail.com", passwordEncoder.encode("admin"), "adminFirstName", "adminLastName", 30);
        adminUser.setRoles(Set.of(adminRole, userRole));
        userRepository.save(adminUser);

        User user = new User( "user1@mail.com", passwordEncoder.encode("user1"), "userFirstName", "userLastName", 36);
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
    }

}

