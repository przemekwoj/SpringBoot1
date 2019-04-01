package com.przemo.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przemo.RestAPI.entity.user.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
