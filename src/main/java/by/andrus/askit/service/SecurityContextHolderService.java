package by.andrus.askit.service;

import by.andrus.askit.model.User;
import by.andrus.askit.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextHolderService {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityContextHolderService.class);

    public User getWhoIsCalling() {
        LOG.info("Getting the authenticated user who initiated the request");
        var principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return User.builder().id(Long.valueOf(principal.getUsername())).nickname(principal.getNickname()).build();
    }
}
