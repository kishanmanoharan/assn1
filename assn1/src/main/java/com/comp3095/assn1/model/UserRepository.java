package com.comp3095.assn1.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User getUserById(Integer id);

    User findUserByUsernameAndPassword(String username, String password);
}
