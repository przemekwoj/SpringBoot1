package com.przemo.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.przemo.RestAPI.entity.Note;
import com.przemo.RestAPI.entity.classes.Klasa;

@Repository
public interface KlasaRepository extends JpaRepository<Klasa, Integer> {

}
