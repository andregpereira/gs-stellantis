package com.stellantis.dataanalytics.apidataanalyticsstellantis.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder password = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("Stellantis")
                .password(password.encode("Stellantis"))
                .roles("USER", "ADMIN");
        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		 http
		    .cors()
		 	.and()
		 	.csrf().disable()
		 	.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
    }

}
