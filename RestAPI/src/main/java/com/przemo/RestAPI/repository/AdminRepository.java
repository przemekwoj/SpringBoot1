package com.przemo.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przemo.RestAPI.entity.user.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}