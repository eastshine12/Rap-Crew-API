package com.eastshine.rapcrewapi.repository;


import com.eastshine.rapcrewapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);


}
