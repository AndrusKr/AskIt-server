package by.andrus.askit.security.jwt;

import by.andrus.askit.service.UserService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class JwtWebSocketAuthenticationInterceptor implements ChannelInterceptor {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public JwtWebSocketAuthenticationInterceptor(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT == stompHeaderAccessor.getCommand()) {
            String jwtHeader = stompHeaderAccessor.getFirstNativeHeader("Authorization");
            JwtUtils.getToken(jwtHeader).ifPresent(jwtToken -> {
                userService.getById(jwtProvider.getUserId(jwtToken))
                        .filter(user -> jwtProvider.validateJwt(jwtToken, user))
                        .map(jwtProvider::getAuthentication)
                        .ifPresent(stompHeaderAccessor::setUser);
            });
        }
        return message;
    }
}
