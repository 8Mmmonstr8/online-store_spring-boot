package ua.hubanov.onlinestore_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.naming.AuthenticationException;

@SpringBootApplication
public class OnlineStoreSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreSpringBootApplication.class, args);
    }

}
