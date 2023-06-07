package com.example.BondApi2.services;

import com.example.BondApi2.models.entities.User;
import com.example.BondApi2.models.requests.CreateUserInput;
import com.example.BondApi2.repositories.ServerRepository;
import com.example.BondApi2.repositories.UserRepository;
import com.example.BondApi2.usecases.CreateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private ServerRepository serverRepository;

    @Autowired
    public UserService(UserRepository userRepository, ServerRepository serverRepository) {
        this.userRepository = userRepository;
        this.serverRepository = serverRepository;
    }

    public User create(CreateUserInput user) {
        return new CreateUserUseCase(userRepository, serverRepository, user).execute();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserResponseById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            optionalUser.get().setJid("JID");//TODO JID creation logic
            optionalUser.get().setImage_url("ImageURL");//TODO ImageUrl creation logic
        }

        return optionalUser;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
