package com.example.quizzApp.repository;

import com.example.quizzApp.entity.QuestionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionForm, Integer> {
}
