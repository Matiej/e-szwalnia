package com.eszwalnia.timesh.configuration;

import com.eszwalnia.timesh.authUser.domain.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthUserService authUserService;

    private static final String[] AUTH_WISHLIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui.html/**",
            "/swagger-ui.html#/**",
            "/mrapi/",
            "/mrapi/**",
            "/swagger-ui.html#/swagger-welcome-controller/**",
            "/v2/api-docs",
            "/webjars/**",
            "/sw",
//            "/users/adduser_n",
//            "/online/movielist_n",
//            "/online/onlinedetail_n",
//            "/home_n",
//            "/users/login",
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void confGlobalSec(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(authUserService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(AUTH_WISHLIST).permitAll()
                .antMatchers("/**/*").denyAll()
                .anyRequest().hasAnyAuthority("ADMIN", "USER", "TESTER")
                .and()
                .formLogin()
                .loginPage("/users/login").permitAll()
//                .successHandler(loginSuccessHandler())
//                .failureHandler(loginFailureHandler())
                .and()
                .logout()
                .permitAll();
//                .logoutSuccessUrl("/users/logout");
    }




}
