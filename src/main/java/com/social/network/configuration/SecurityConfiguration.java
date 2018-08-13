package com.social.network.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected  void configure(HttpSecurity http) throws  Exception {
       http
            .authorizeRequests()
               .antMatchers("/{userId}", "/user").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and()
               .csrf().disable()
            .logout()
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .permitAll();
    }

    @Autowired
    private AccountDetailsService accountDetailsService;

//    @Bean
//    public PasswordEncoder bcryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(accountDetailsService);
                //.passwordEncoder(bcryptPasswordEncoder());
    }
}
