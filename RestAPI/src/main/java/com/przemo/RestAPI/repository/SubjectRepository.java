package com.przemo.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przemo.RestAPI.entity.parent.Subject;

public interface SubjectRepository  extends JpaRepository<Subject, Integer> {

}
