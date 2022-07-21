package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
      UserDetails tuser = User.builder()
                .username("T")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.USER.name())
                .build();
        return new InMemoryUserDetailsManager(tuser);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
//                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.USER.name())
                .antMatchers("/registration.html").hasRole(ApplicationUserRole.USER.name())
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login.html")
                .loginProcessingUrl("/api/doLogin")
                .defaultSuccessUrl("//api/doLogin")
                .failureUrl("/loginPage?error")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}
