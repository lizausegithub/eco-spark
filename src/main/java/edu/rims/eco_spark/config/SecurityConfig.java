package edu.rims.eco_spark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.rims.eco_spark.entity.User;
import edu.rims.eco_spark.repository.UserRepository;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserRepository userRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request.requestMatchers("/logIn/home", "/logIn/sign", "/style/**",
                "/script/**", "/images/**", "/customer/home", "/product/category/home", "/product/search",
                "/product/pdp", "/admin/productimage/{productId}").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated());
        http.formLogin(form -> form.loginPage("/logIn/home").defaultSuccessUrl("/customer/home"));
        http.logout(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    UserDetailsService detailsService() {
        return (username) -> {
            User user = userRepository.findByUserEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("userNotFound"));
            return org.springframework.security.core.userdetails.User.builder()
                    .username(username)
                    .password(user.getUserPassword())
                    .roles(user.getUserRole().toString())
                    .build();
        };
    }
}
