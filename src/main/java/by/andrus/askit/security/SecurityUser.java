package by.andrus.askit.security;

import by.andrus.askit.model.enums.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class SecurityUser implements UserDetails {
    private final String id;
    private final String nickname;
    private final String password;
    private final Set<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(Long id, String nickname, UUID jwtSecret, Roles roles, boolean isActive) {
        this.id = id.toString();
        this.nickname = nickname;
        this.password = jwtSecret.toString();
        this.authorities = roles.getAuthorities();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.name()));
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
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isActive() {
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

    @Override
    public String toString() {
        return "SecurityUser{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", isActive=" + isActive +
                '}';
    }
}