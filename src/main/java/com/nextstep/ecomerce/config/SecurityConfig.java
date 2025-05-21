package com.nextstep.ecomerce.config;

//import com.nextstep.ecomerce.config.CorsConfig;
import com.nextstep.ecomerce.filter.JwtAuthenticationFilter;
import com.nextstep.ecomerce.service.CustomUserDetailsService;
import com.nextstep.ecomerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
   // @Qualifier("corsConfig")
    CorsConfig customCorsConfiguration;
    @Autowired
    private CustomUserDetailsService userDetailsService; // Use the combined service

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/registerAdmin", "/api/auth/loginAdmin","/displayProducts","/user/registerUser","/user/loginUser","/saveImage",
                        "/v3/api-docs/**","/swagger-ui/**","/v2/api-docs/**","/swagger-resources/**").permitAll()

                //   .antMatchers("/api/auth/health").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .cors(c -> c.configurationSource(customCorsConfiguration))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
