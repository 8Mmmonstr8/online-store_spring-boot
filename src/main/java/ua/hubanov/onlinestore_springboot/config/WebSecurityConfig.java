package ua.hubanov.onlinestore_springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.hubanov.onlinestore_springboot.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/", "/tools/**", "/registration/**",
                            "/cart/**", "/login/**")
                    .permitAll()
                    .antMatchers("/admin", "/admin/**").hasAnyAuthority("ADMIN")
                    .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/j_spring_security_logout")
                    .permitAll()
                    .logoutSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
//        auth.inMemoryAuthentication().withUser("user")
//                .password("{noop}password").roles("USER");
    }
}
