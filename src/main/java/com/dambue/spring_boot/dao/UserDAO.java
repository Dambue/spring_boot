package com.dambue.spring_boot.dao;



import com.dambue.spring_boot.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsersWithRoles();
    void save(User user);
    void delete(Long id);
    User getUserById(Long id);
    void update(User updUser);
}
