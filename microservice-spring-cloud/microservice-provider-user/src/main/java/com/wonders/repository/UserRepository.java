package com.wonders.repository;

import com.wonders.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


//Repository表示该类为一个dao
public interface UserRepository extends JpaRepository<User, Long> {



}
