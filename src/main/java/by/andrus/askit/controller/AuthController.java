package by.andrus.askit.controller;

import by.andrus.askit.dto.request.AuthRequest;
import by.andrus.askit.dto.response.AuthResponse;
import by.andrus.askit.model.User;
import by.andrus.askit.security.SecurityUser;
import by.andrus.askit.security.jwt.JwtProvider;
import by.andrus.askit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Autowired
    public AuthController(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<AuthResponse> signUp(@RequestBody AuthRequest authRequest) {
        User user = userService.create(authRequest.toUser());
        System.out.println("user-print " + user);
        String jwt = jwtProvider.createJwt(user);
        System.out.println("jwt-print " + jwt);
        return ResponseEntity.ok(new AuthResponse(user.getId(), user.getNickname(), jwt));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("user")
    public ResponseEntity<Map<Object, Object>> user() {
        var principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<Object, Object> response = new HashMap<>();
        response.put("id", principal.getUsername());
        response.put("nickname", principal.getNickname());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("sign-out")
    public void signOut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, null);
    }
}
