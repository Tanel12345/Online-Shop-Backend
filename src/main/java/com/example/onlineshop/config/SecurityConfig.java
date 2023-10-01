package com.example.onlineshop.config;


import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.entity.User;
import com.example.onlineshop.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {

//    private static final String REGISTER_ENDPOINT = "/api/v1/user/register";
//    private static final String LOGIN_ENDPOINT = "/api/v1/user/login";
//    private static final int COOKIE_VALIDITY_HOURS = 24;
//
//    private UserDetailsServiceImpl userDetailsService;
//
//    private ObjectMapper mapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//                public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
//                    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//                    authProvider.setUserDetailsService(userDetailsService);
//                    authProvider.setPasswordEncoder(passwordEncoder);
//                    return authProvider;
//                }
//
//
//                @Bean
//                public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//                    return http.authorizeHttpRequests(authRequests())
//                            .csrf(AbstractHttpConfigurer::disable)
//                            .formLogin(successLogin())
//                            .rememberMe(rememberMe())
//                            .build();
//                }
//
//                private Customizer<RememberMeConfigurer<HttpSecurity>> rememberMe() {
//                    return rememberMe -> rememberMe
//                            .tokenValiditySeconds(COOKIE_VALIDITY_HOURS * 60 * 60)
//                            .useSecureCookie(true);
//                }
//
//                private Customizer<FormLoginConfigurer<HttpSecurity>> successLogin() {
//                    return formLogin -> formLogin
//                            .loginProcessingUrl(LOGIN_ENDPOINT)
//                            .successHandler((request, response, authentication) -> {
//                                response.setContentType("application/json;charset=UTF-8");
//                                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//                User userEntity = userDetailsService.findUserEntityByUsername(userDetails.getUsername());
//                String json = mapper.writeValueAsString(userEntityToUserDto(userEntity));
//                response.getWriter().write(json);
//            });
//    }
//
//    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authRequests() {
//        return authorizeRequests -> authorizeRequests
//                .requestMatchers(new AntPathRequestMatcher(REGISTER_ENDPOINT, "POST")).permitAll()
//                .anyRequest().authenticated();
//    }
//
//    private UserDto userEntityToUserDto(User userEntity) {
//        return new UserDto()
//            .setId(userEntity.getId())
//            .setEmail(userEntity.getEmail())
//            .setUserType(userEntity.getUserType())
//            .setFirstName(userEntity.getFirstName())
//            .setLastName(userEntity.getLastName())
//            .setCreatedAt(userEntity.getCreatedAt());
//    }
//
}
