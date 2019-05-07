package com.przemo.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.przemo.RestAPI.entity.user.Admin;
import com.przemo.RestAPI.entity.user.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
