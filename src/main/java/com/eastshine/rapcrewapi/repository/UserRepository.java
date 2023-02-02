package com.eastshine.rapcrewapi.repository;


import com.eastshine.rapcrewapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
