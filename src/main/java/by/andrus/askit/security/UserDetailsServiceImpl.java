//package by.andrus.askit.security;
//
//import by.andrus.askit.model.User;
//import by.andrus.askit.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserService userService;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserService userService) {
//        this.userService = userService;
//
//        @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User named:" + username + " not found");
//        }
//        JwtUser jwtUser = JwtUserFactory.create(user);
//        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
//        return jwtUser;
//    }
//}