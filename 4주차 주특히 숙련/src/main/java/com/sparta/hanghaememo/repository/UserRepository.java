package com.sparta.hanghaememo.repository;

import com.sparta.hanghaememo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {   //????? JpaRepository ? -JpaRepository를 상속받도록 함으로써 기본적인 동작이 모두 가능해진다!

    Optional<User> findByUsername(String username);



    boolean existsByUsername(String username);  ///??????
}