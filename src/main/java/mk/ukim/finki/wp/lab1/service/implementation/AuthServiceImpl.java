package mk.ukim.finki.wp.lab1.service.implementation;

import mk.ukim.finki.wp.lab1.model.User;
import mk.ukim.finki.wp.lab1.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab1.service.AuthService;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidArgumentException("Invalid username or password"));
    }
}
