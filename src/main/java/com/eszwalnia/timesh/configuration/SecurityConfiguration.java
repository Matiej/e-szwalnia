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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
            "/h2/**",
//            "/"
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
                .csrf().disable()
                .headers().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WISHLIST).permitAll()
//                .antMatchers("/**/*").denyAll()
                .anyRequest().hasAnyAuthority("ADMIN", "USER", "TESTER")
                .and()
                .formLogin()
//                .loginPage("/users/login").permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .permitAll();
//                .logoutSuccessUrl("/users/logout");
    }

    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/");
            request.getSession().setAttribute("flash", new FlashMessage("Login successful", FlashMessage.Status.SUCCESS));
            authentication.getDetails();
        };
    }

    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute("flash", new FlashMessage("Wrong login or password", FlashMessage.Status.FAILURE));
            response.sendRedirect("/");
        };
    }


}
