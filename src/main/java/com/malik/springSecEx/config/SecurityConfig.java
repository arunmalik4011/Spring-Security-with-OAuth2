// 📦 Package declaration
package com.malik.springSecEx.config;

// 📥 Required Spring and security imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 📌 Marks this class as a Spring configuration class
@Configuration
// 🔐 Enables Spring Security web configuration
@EnableWebSecurity
public class SecurityConfig {

    // 👤 Injects the custom implementation of UserDetailsService
    @Autowired
    private UserDetailsService userDetailsService;

    // 🔐 Main Security Filter Chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // ❌ Disable CSRF (for development/testing only — enable in production)
//                .csrf(csrf -> csrf.disable())

                // ✅ Define which URLs are publicly accessible and which require authentication
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",                // Root URL
                                "/register",        // Registration form
                                "/login",           // Custom login page
                                "/login-error",     // Error page on failed login
                                "/oauth2/**",       // OAuth2 URLs (Google/GitHub)
                                "/css/**"           // Public CSS files
                        ).permitAll()          // Allow access to these without login
                        .anyRequest().authenticated()  // All other URLs require authentication
                )

                // 🧠 Configure session management
                .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        // Session created only when needed
                )

                // 🔐 Configure custom form login
                .formLogin(form -> form
                        .loginPage("/login")            // Your custom login page
                        .defaultSuccessUrl("/home", true)  // Redirect here on successful login
                        .failureUrl("/login?error=true") // ✅ redirects back with error param
                        .permitAll()                    // Let everyone see the login page
                )

                // 🔐 Configure OAuth2 login (Google/GitHub)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")            // Use the same login page
                        .defaultSuccessUrl("/home", true)  // Go to home after OAuth login
                        .failureUrl("/login-error")     // In case OAuth login fails
                )

                // 🔐 Configure logout behavior
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true") // optional: logout success message
                        .invalidateHttpSession(true)        // Invalidate session
                        .clearAuthentication(true)          // Remove authentication object
                        .deleteCookies("JSESSIONID")        // Delete session cookie
                        .permitAll()                        // Logout endpoint is public
                )

                // 🏗️ Build the filter chain
                .build();
    }

    // 🔐 Bean for encoding passwords using BCrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 12 is the strength (can be between 4 and 31)
        return new BCryptPasswordEncoder(12);
    }

    // ✅ Defines the authentication provider (for username/password login)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // 🔑 Set the password encoder to BCrypt
        provider.setPasswordEncoder(passwordEncoder());

        // 👤 Set the service that will load user details from the database
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }
}
