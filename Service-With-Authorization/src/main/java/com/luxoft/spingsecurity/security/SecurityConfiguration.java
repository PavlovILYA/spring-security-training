package com.luxoft.spingsecurity.security;

import com.luxoft.spingsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${security.remember.me.key}")
    private String rememberMeKey;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
//                .csrf().disable()
                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/company/**", "/user/**").authenticated()
                .antMatchers("/login", "/deny.html", "/logout", "/whoami").permitAll()
                .antMatchers("/info").hasAuthority("ROLE_ANON")
                .antMatchers("/**").denyAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .failureUrl("/deny.html")
                .defaultSuccessUrl("/company", true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .anonymous()
                .authorities("ROLE_ANON") // ROLE_ANONYMOUS by default
                .principal(new UserDetailsAdapter(anonymous()))
                .and()
                .rememberMe()
                .alwaysRemember(true)
                .key(rememberMeKey);
        // @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder(10));
    }

    private static User anonymous() {
        User user = new User();
        user.setId(-1);
        user.setLogin("anonymous");
        user.setPassword(""); user.setRoles(Collections.singletonList("ROLE_ANON"));
        return user;
    }
}
