package by.andrus.askit.service;

import by.andrus.askit.model.User;
import by.andrus.askit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    public User update(User user) {
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
}
