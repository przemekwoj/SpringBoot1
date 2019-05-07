package com.przemo.RestAPI.repository.parent;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.przemo.RestAPI.entity.grade.Grade;
import com.przemo.RestAPI.entity.parent.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
}
