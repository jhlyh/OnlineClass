package com.example.onlineclass.security;

import com.example.onlineclass.handler.MyAuthenticationFailureHandler;
import com.example.onlineclass.handler.MyAuthenticationSuccessHandler;
import com.example.onlineclass.service.UserDetailService;
import com.example.onlineclass.service.imp.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    DataSource dataSource;
    UserDetailService userDetailService;
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private MyAuthenticationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    public SecurityConfig(DataSource dataSource, UserDetailService userDetailService, MyAuthenticationSuccessHandler myAuthenticationSuccessHandler, MyAuthenticationFailureHandler myAuthenctiationFailureHandler) {
        this.dataSource = dataSource;
        this.userDetailService = userDetailService;
        this.myAuthenctiationFailureHandler = myAuthenctiationFailureHandler;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test")
                .hasRole("ADMIN")
                .antMatchers("/","/**").permitAll()

                .and()
                .formLogin()
//                .loginPage("/DK")  //指定登录页的路径
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
                .permitAll()

                .and()
                .logout()
                .permitAll()

                .and()
                .csrf()
                .disable();
    }
}
