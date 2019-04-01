package com.przemo.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przemo.RestAPI.entity.user.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
