package com.example.BondApi2.usecases;

import com.example.BondApi2.models.entities.Server;
import com.example.BondApi2.models.entities.User;
import com.example.BondApi2.models.requests.CreateUserInput;
import com.example.BondApi2.repositories.ServerRepository;
import com.example.BondApi2.repositories.UserRepository;

import java.util.Optional;

public record CreateUserUseCase(
        UserRepository userRepository,
        ServerRepository serverRepository,
        CreateUserInput createUserInput) {

    public User execute() {

        Optional<Server> serverOpt = serverRepository.findById(1);//TODO Server selection logic

        User user = new User();
        user.setName(createUserInput.getName());
        user.setPublic_key(createUserInput.getPublic_key());
        user.setDescription(createUserInput.getDescription());
        user.setApi_key(createUserInput.getApi_key());
        serverOpt.ifPresent(user::setServer);
        userRepository.save(user);

        return user;
    }
}
