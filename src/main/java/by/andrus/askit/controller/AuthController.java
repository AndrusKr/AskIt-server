package by.andrus.askit.controller;

import by.andrus.askit.dto.AuthRequestDto;
import by.andrus.askit.model.User;
import by.andrus.askit.security.SecurityUser;
import by.andrus.askit.security.jwt.JwtProvider;
import by.andrus.askit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
@RequestMapping(value = "/api/auth/")
public class AuthController {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Autowired
    public AuthController(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("signin")
    public ResponseEntity<Map<Object, Object>> signin(@RequestBody AuthRequestDto authRequestDto) {
        User user = userService.create(authRequestDto.toUser());
        String jwt = jwtProvider.createJwt(user);
        Map<Object, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("nickname", user.getNickname());
        response.put("jwt", jwt);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("user")
    public ResponseEntity<Map<Object, Object>> user() {
        var principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<Object, Object> response = new HashMap<>();
        response.put("id", principal.getUsername());
        response.put("nickname", principal.getNickname());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("signout")
    public void signout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, null);
    }
}
