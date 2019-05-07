package com.przemo.RestAPI.repository.parent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.przemo.RestAPI.entity.parent.Subject;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Integer> {

}
