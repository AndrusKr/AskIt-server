package by.andrus.askit.security;

import by.andrus.askit.model.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
public class SecurityUser implements UserDetails {
    private final String username;
    private final String nickname;
    private final String password;
    private final Set<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(Long id, String nickname, UUID jwtSecret, Role role, boolean isActive) {
        this.username = id.toString();
        this.nickname = nickname;
        this.password = jwtSecret.toString();
        this.authorities = role.getAuthorities();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}