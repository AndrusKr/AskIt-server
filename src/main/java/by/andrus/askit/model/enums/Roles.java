package by.andrus.askit.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Roles {
    ROLE_USER(Set.of(Permission.OWN_QUESTIONS_CREATE, Permission.ALL_QUESTIONS_READ)),
    ROLE_ADMIN(Set.of(Permission.OWN_QUESTIONS_CREATE, Permission.ALL_QUESTIONS_READ,
            Permission.ALL_QUESTIONS_UPDATE, Permission.ALL_QUESTIONS_DELETE));

    private final Set<Permission> permissions;

    Roles(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
