package com.thanhtoan.first.idiot.configuration;

import javax.sql.DataSource;

import com.thanhtoan.first.idiot.login.CustomUserDetailsService;

// import com.thanhtoan.first.idiot.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigurationWeb extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("ENCODEDING");
        auth.authenticationProvider(authenticationProvider());
        // auth.inMemoryAuthentication()
        // .withUser("juan@luv2code.com").password(passwordEncoder().encode("Thanhtoan@1")).roles("ADMIN")
        // .and()
        // .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
        // .and()
        // .withUser("admin").password(passwordEncoder().encode("thanhtoan")).roles("ADMIN");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // .antMatchers("/admin/**").hasRole("ADMIN")
                // .antMatchers("/anonymous*").anonymous()
                .antMatchers("/list_users").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/")
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
                
    }

   
}