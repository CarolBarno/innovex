package com.innovex.Innovex.UserManager.repository;

import com.innovex.Innovex.UserManager.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByIdNumberIgnoreCase(String idNumber);
}
