package fonepay.task.ODSBE.service;

import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.model.User;
import fonepay.task.ODSBE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("User of id " + id + " was not found!"));
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
