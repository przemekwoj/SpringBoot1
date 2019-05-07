package com.przemo.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.przemo.RestAPI.entity.user.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}