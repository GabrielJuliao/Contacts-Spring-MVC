package com.gabrieljuliao.contacts;

import com.gabrieljuliao.contacts.model.UserDetailsServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Setter
@Getter
@ConfigurationProperties(prefix = "contacts.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final UserDetailsServiceImpl userDetailsService;
    //injected by config props
    private String usersQuery;
    //injected by config props
    private String authoritiesQuery;

    public SecurityConfig(DataSource dataSource, UserDetailsServiceImpl userDetailsService) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      //  auth.inMemoryAuthentication().withUser("username").password(encoder().encode("password")).authorities("USER");
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(authoritiesQuery);
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests().antMatchers("/", "/account", "/contact")
                .hasRole("USER").antMatchers("/sign_in", "/sign_up")
                .permitAll().and().formLogin()
                .loginPage("/sign_in")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/").and().logout().logoutSuccessUrl("/sign_in").and().csrf().disable();

//        security.authorizeRequests().antMatchers("/", "/**").permitAll().and().formLogin()
//                .loginPage("/sign_in")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/").and().logout().logoutSuccessUrl("/sign-in").and().csrf().disable();

        ///allow h2 console
        security.headers().frameOptions().sameOrigin();
    }

}
