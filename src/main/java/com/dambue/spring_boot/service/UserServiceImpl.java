package com.dambue.spring_boot.service;

import com.dambue.spring_boot.model.Role;
import com.dambue.spring_boot.model.User;
import com.dambue.spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<User> index() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User show(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public void update(Long id, User updUser) {
        userRepository.getOne(id);
        userRepository.save(updUser);
    }
}
