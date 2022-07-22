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



       public void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/api/**").hasRole(ApplicationUserRole.USER.name())
                    .antMatchers("/registration").hasRole(ApplicationUserRole.USER.name())
                    //Доступ только для пользователей с ролью Администратор
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/news").hasRole("USER")
                    //Доступ разрешен всем пользователей
                    .antMatchers("/", "/resources/**").permitAll()
                    //Все остальные страницы требуют аутентификации
                    .anyRequest().authenticated()
                    .and()
                    //Настройка для входа в систему
                    .formLogin()
                    .loginPage("/login.html")
                    //Перенарпавление на главную страницу после успешного входа
                    .defaultSuccessUrl("//api/doLogin")
                    .failureUrl("/loginPage?error")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/")
                    .and()
                    .exceptionHandling().accessDeniedPage("/403");
    }
}
