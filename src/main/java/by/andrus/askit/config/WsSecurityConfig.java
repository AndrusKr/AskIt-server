package by.andrus.askit.config;

import by.andrus.askit.model.enums.Roles;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import static org.springframework.messaging.simp.SimpMessageType.MESSAGE;
import static org.springframework.messaging.simp.SimpMessageType.SUBSCRIBE;

@Configuration
public class WsSecurityConfig
        extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .nullDestMatcher().authenticated()
                .simpSubscribeDestMatchers("/topic/**").hasAnyRole(Roles.ADMIN.toString(), Roles.USER.toString())
                .simpDestMatchers("/app/**").hasAnyRole(Roles.ADMIN.toString(), Roles.USER.toString())
                .simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll()
//                .anyMessage().denyAll()
        ;
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true; // CSRF for WS disabled
    }
}