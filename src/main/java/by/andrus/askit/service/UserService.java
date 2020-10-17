package by.andrus.askit.service;

import by.andrus.askit.model.User;
import by.andrus.askit.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        User createdUser = userRepository.save(user);
        log.info("IN UserService.create user: {} successfully created", createdUser);
        return createdUser;
    }

    public User update(User user) {
        User updatedUser = userRepository.save(user);
        log.info("IN UserService.update user: {} successfully updated", updatedUser);
        return updatedUser;
    }

    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.get() == null) {
            log.info("IN UserService.getById - no user found by id: {}", id);
            return null;
        }
        log.info("IN UserService.getById - user: {} found by id: {}", user.get(), id);
        return user.get();
    }
}
