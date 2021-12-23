package by.andrus.askit.service;

import by.andrus.askit.model.User;
import by.andrus.askit.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User create(User user) {
        User createdUser = usersRepository.save(user);
        return createdUser;
    }

    public User update(User user) {
        User updatedUser = usersRepository.save(user);
        return updatedUser;
    }

    public Optional<User> getById(Long id) {
        return usersRepository.findById(id);
    }
}
