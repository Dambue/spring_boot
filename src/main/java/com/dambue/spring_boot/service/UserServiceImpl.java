package com.dambue.spring_boot.service;

import com.dambue.spring_boot.dao.RoleDAOImpl;
import com.dambue.spring_boot.dao.UserDAOImpl;
import com.dambue.spring_boot.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAOImpl userRepository;
    private final RoleDAOImpl roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAOImpl userRepository, RoleDAOImpl roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsersWithRoles() {
        return userRepository.getUsersWithRoles();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.getRoleById(2L)));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    @Transactional
    public void update(User updUser) {
        User actualUser = userRepository.getUserById(updUser.getId());
        if (!actualUser.getPassword().equals(updUser.getPassword())) {
            updUser.setPassword(passwordEncoder.encode(updUser.getPassword()));
        }
        userRepository.update(updUser);
    }
}
