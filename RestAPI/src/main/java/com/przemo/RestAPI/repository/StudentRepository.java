package com.przemo.RestAPI.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.przemo.RestAPI.entity.user.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
