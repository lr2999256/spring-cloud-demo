package com.rui.zuul.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Rui
 * @Date : 2018/11/7
 * @Time : 9:53
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // 创建用户
        manager.createUser(User.withUsername("admin")
                //密码
                .password("123456")
                //权限
                .authorities(new SimpleGrantedAuthority("admin"))
                .authorities("ROLE_ADMIN").build());
        return manager;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.
                requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/index","/oauth/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/admin/welcome")
                .and()
                .httpBasic();
//                .disable()
//                .exceptionHandling()
//                .accessDeniedPage("/login?authorization_error=true");
//                .loginPage("/login")
//                .failureUrl("/login?authentication_error=true")
//        .httpBasic();
        // @formatter:on
    }
}
