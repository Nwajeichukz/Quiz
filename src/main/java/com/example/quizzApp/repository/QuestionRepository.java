package com.example.quizzApp.repository;

import com.example.quizzApp.entity.QuestionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QuestionRepository extends JpaRepository<QuestionForm, Integer> {
    Optional<QuestionForm> findById(int id);
}