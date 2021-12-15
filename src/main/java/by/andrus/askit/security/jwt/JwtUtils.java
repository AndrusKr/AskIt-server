package by.andrus.askit.security.jwt;

import com.google.common.base.Strings;

import java.util.Optional;

public final class JwtUtils {
    public static final String TOKEN_PREFIX = "Bearer ";

    private JwtUtils() {

    }

    public static Optional<String> getToken(String jwt) {
        return Strings.isNullOrEmpty(jwt) || !jwt.startsWith(TOKEN_PREFIX) ? Optional.empty()
                : Optional.of(jwt.replace(TOKEN_PREFIX, ""));
    }
}
