package com.przemo.RestAPI.repository.parent;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przemo.RestAPI.entity.classes.Klasa;
import com.przemo.RestAPI.entity.parent.Subject;

public interface KlasaRepository extends JpaRepository<Klasa, Integer> {

}
