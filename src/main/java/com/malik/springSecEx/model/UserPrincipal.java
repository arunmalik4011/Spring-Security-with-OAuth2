// ✅ Package declaration
package com.malik.springSecEx.model;

// ✅ Import necessary Spring Security interfaces/classes
import org.springframework.security.core.GrantedAuthority;               // Represents an authority granted to the user
import org.springframework.security.core.authority.SimpleGrantedAuthority; // A basic implementation of GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails;       // Core interface representing a user in Spring Security

import java.util.Collection;

import java.util.List;


// ✅ Custom implementation of Spring Security's UserDetails interface
// This class wraps your Users entity and adapts it to Spring Security's user model
public class UserPrincipal implements UserDetails {

    // ✅ Composition: holds a reference to your Users entity
    private final Users users;

    // ✅ Constructor: initializes the UserPrincipal with a Users object
    public UserPrincipal(Users users) {
        this.users = users;
    }

    // ✅ Returns the authorities (roles) granted to the user
    // In this case, a single hardcoded role "USER" is returned
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + users.getRole()));
    }

    // ✅ Returns the encrypted password from the Users entity
    @Override
    public String getPassword() {
        return users.getPassword();
    }

    // ✅ Returns the username (usually email or username) from the Users entity
    @Override
    public String getUsername() {
        return users.getUsername();
    }

    // ✅ Below methods return account status flags
    // These are typically used to disable or lock accounts in production systems
    // For now, returning `true` means account is valid in all respects

    @Override
    public boolean isAccountNonExpired() {
        return true; // account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // password is not expired
    }

    @Override
    public boolean isEnabled() {
        return true; // account is active/enabled
    }
}

