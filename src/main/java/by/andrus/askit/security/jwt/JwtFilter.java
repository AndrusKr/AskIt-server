package by.andrus.askit.security.jwt;

import by.andrus.askit.config.SecurityConfig;
import by.andrus.askit.model.User;
import by.andrus.askit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final AntPathMatcher pathMatcher;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public JwtFilter(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return pathMatcher.match(SecurityConfig.SIGNIN_ENDPOINT, request.getServletPath());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = jwtProvider.resolveJwt(request);
        try {
            User claimingUser = userService.getById(jwtProvider.getUserId(jwt));
            if (jwt != null && jwtProvider.validateJwt(jwt, claimingUser)) {
                Authentication authentication = jwtProvider.getAuthentication(claimingUser);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            response.sendError(e.getHttpStatus().value());
            throw new JwtAuthenticationException("JWT is expired or invalid");
        }
        filterChain.doFilter(request, response);
    }
}
