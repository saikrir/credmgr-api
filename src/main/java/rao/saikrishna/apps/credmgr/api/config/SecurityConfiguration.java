package rao.saikrishna.apps.credmgr.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rao.saikrishna.apps.credmgr.api.service.ApplicationUserService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationUserService applicationUserService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("Security Configured");
        auth.userDetailsService(applicationUserService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/adminPing").hasAuthority("ADMIN")
                .antMatchers("/ping").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/").permitAll()
                .antMatchers("/**").denyAll()
                .and().formLogin();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
