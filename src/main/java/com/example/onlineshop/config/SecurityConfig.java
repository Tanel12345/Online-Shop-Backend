package com.example.onlineshop.config;



import com.example.onlineshop.entity.UserEntity;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
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

/**
 * Overall, this class sets up the security configurations for your Spring application, specifying which endpoints require authentication,
 * how authentication should be handled, and how successful logins are processed. It uses the UserDetailsServiceImpl service to manage user
 * details and the BCryptPasswordEncoder to hash and verify passwords securely. It also handles exceptions related to authentication.
 */

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private static final String REGISTER_ENDPOINT = "/customers/create";
    private static final String CATEGORY_ENDPOINT = "/category";
    private static final String LOGIN_ENDPOINT = "/customers/login";
    private static final int COOKIE_VALIDITY_HOURS = 24;

    /*private UserDetailsServiceImpl userDetailsService: This is a service class
     that provides user-related details.
     It's used by Spring Security for user authentication.*/
    private UserDetailsServiceImpl userDetailsService;

    /*private ObjectMapper mapper: An object mapper for converting objects to JSON and vice versa.*/
    private ObjectMapper mapper;

    /*private CustomerMapper: A mapper for converting between different
     representations of customer entities.*/
    private CustomerMapper customerMapper;






    /**
     * passwordEncoder tagastab BCrypt tüüpi passwordiencooderi, millega luuakse parooli hash
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





    /**
     * authenticationProvider on objekt, mis teostab autenteerimist
     * DaoAuthenticationProvider tegeleb kasutajatega mis on lisatud enda andmebaasi
     * UserDetailsServiceImplst UserDetailsService on springi interface mis ühendab kasutajat ja spring securytit
     *Kuna spring ei tea sinu tabeli veerge ja muid andmeid siis UserDetailsServicest Overridetud meetodid, näiteks
     * loadUserByUsername, loovad ühenduse sinu andmebaasis olevate kasutajate ja spring sec vahel
     *
     * authenticationProvider(): Creates an instance of DaoAuthenticationProvider, which is responsible for
     * user authentication. It configures the userDetailsService and passwordEncoder for this provider.
     * @param passwordEncoder
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }






    /**
     *Sisse logimise meetod. Mitte õnnestunud sisse logimisel saadetakse veateade päringu vastuseks
     * SecurityFilterChain aitab securitil sisselogimist teostada
     * csrf on suletud kui me ei saada formilt peidetud inputiga infot, mis eristab formiga saadetut,
     * näiteks kellegi poolt postmaniga saadetu omast, kus peidetud input info puudub.
     * Eesmärgiga tuvastada et info tuleks õgest kohast. Sellist varianti kasutatakse backendi poolt
     * view genereerimisel.
     *
     * SecurityFilterChain Bean:
     *
     * filterChain(HttpSecurity http): Configures the security filter chain for HTTP requests.
     * authorizeHttpRequests(authRequests()): Specifies which requests should be authorized (public vs. authenticated).
     * .csrf(AbstractHttpConfigurer::disable): Disables CSRF protection.
     * .formLogin(successLogin()): Configures the form-based login settings, including login URL and success handler.
     * .rememberMe(rememberMe()): Configures remember-me functionality with a secure cookie.
     * .exceptionHandling(...): Defines how authentication exceptions are handled, sending an unauthorized response.
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authRequests())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(successLogin())
                .rememberMe(rememberMe())
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint((request, response, authException) -> {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                    });
                })
                .build();
    }

    //Sedasi genereeritakse sisse logimise mitte õnnestumisel springi poolt bootstrapiga login leht
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(authRequests())
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(successLogin())
//                .rememberMe(rememberMe())
//                .build();
//    }

    private Customizer<RememberMeConfigurer<HttpSecurity>> rememberMe() {
        return rememberMe -> rememberMe
                .tokenValiditySeconds(COOKIE_VALIDITY_HOURS * 60 * 60)
                .useSecureCookie(true);
    }





    /**
     * Kui login on succesful, mida ja kuidas siis teha
     *
     * successLogin() Method:
     *formLogin -> formLogin...: Configures what happens when a login is successful. It specifies the
     * login processing URL and success handler, which returns a JSON response with user details upon successful login.
     * @return
     */
    private Customizer<FormLoginConfigurer<HttpSecurity>> successLogin() {

        return formLogin -> formLogin
                .loginProcessingUrl(LOGIN_ENDPOINT)
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    System.out.println("Formloginmeetod "+userDetails.getUsername());
                    UserEntity userEntity = userDetailsService.findUserEntityByUsername(userDetails.getUsername());
                    String json = mapper.writeValueAsString(
                            customerMapper.userEntityToResponseDto(userEntity)
                    );
                    response.getWriter().write(json);
                });
    }



    /**
     * Siin authRequests() meetodis me saame öelda millised endpoindid on lubatud ja millised vajavad autenteerimist
     * authRequests() Method:
     *
     * authorizeRequests -> authorizeRequests...: Defines which endpoints are permitted without authentication
     * (e.g., registration) and which require authentication for any other request.
     * @return
     */

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authRequests() {
        return authorizeRequests -> authorizeRequests
                .requestMatchers(new AntPathRequestMatcher(REGISTER_ENDPOINT, "POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher(CATEGORY_ENDPOINT, "GET")).permitAll()
                .anyRequest().authenticated();
    }

//    private CustomerResponseDTO userEntityToResponseDto(User userEntity) {
//
//        return new CustomerResponseDTO()
//            .setId(userEntity.getId())
//            .setUsername(userEntity.getUsername())
//            .setEmail(userEntity.getEmail())
//            .setUserType(userEntity.getUserType())
//            .setFirstName(userEntity.getFirstName())
//            .setLastName(userEntity.getLastName())
//            .setCreatedAt(userEntity.getCreatedAt());
//    }

}
