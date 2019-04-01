package com.przemo.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.przemo.RestAPI.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
