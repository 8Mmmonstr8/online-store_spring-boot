package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Bean
//    public PasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @PostConstruct
//    public void init() {
//        userRepository.findByEmail("qq@ww").ifPresent(user -> {
//                    user.setPassword(new BCryptPasswordEncoder().encode("password"));
//                    userRepository.save(user);
//        });
//
//    }

    public boolean saveUser(User user) {
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());

        if (userFromDb.isPresent())
            return false;

        final String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user " + email + "was not found"));
    }
}
