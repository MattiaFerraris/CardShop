package ch.supsi.web.cardgames.configuration;

import ch.supsi.web.cardgames.model.Role;
import ch.supsi.web.cardgames.model.User;
import ch.supsi.web.cardgames.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfig {
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
                String username = "admin";
                String rawPassword = "admin";

                if (userRepository.findByUsername(username).isEmpty()) {
                    User admin = new User();
                    admin.setUsername(username);
                    admin.setPassword(passwordEncoder.encode(rawPassword));
                    admin.setRole(Role.ADMIN);

                    userRepository.save(admin);
                    System.out.println("Admin user created.");
                }
        };
    }
}
