package mk.ukim.finki.wp.lab1.service;


import mk.ukim.finki.wp.lab1.model.User;

public interface AuthService {

    User login(String username, String password);

}
