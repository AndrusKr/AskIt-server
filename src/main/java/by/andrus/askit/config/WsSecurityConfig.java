package by.andrus.askit.config;

import by.andrus.askit.model.enums.Roles;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import static org.springframework.messaging.simp.SimpMessageType.CONNECT;

@Configuration
public class WsSecurityConfig
        extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.simpSubscribeDestMatchers("/topic/**").hasAnyRole(Roles.ADMIN.toString(), Roles.USER.toString())
                .simpDestMatchers("/app/**").hasAnyRole(Roles.ADMIN.toString(), Roles.USER.toString())
                .simpTypeMatchers(CONNECT).permitAll()
                .nullDestMatcher().authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true; // CSRF for WS disabled
    }
}
