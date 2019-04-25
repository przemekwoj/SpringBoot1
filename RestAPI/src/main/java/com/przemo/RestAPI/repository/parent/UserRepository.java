package com.przemo.RestAPI.repository.parent;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przemo.RestAPI.entity.parent.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
