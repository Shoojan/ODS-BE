package fonepay.task.ODSBE.service;

import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.model.User;
import fonepay.task.ODSBE.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public record UserService(UserRepository userRepository) {

    public List<User> findAllUsers() {
        return userRepository.findAllByDeletedAt(null);
    }

    public User findUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("User of id " + id + " was not found!"));
        if (user.getDeletedAt() == null)
            return user;
        else throw new ApiRequestException("User not valid!");
    }

    public User addUser(User user) {
        user.setCreatedAt(LocalDate.now());
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        user.setUpdatedAt(LocalDate.now());
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        User user = findUserById(id);
        user.setDeletedAt(LocalDate.now());
        userRepository.save(user);
    }
}
