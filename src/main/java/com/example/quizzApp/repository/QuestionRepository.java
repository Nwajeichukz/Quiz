package com.example.quizzApp.repository;

import com.example.quizzApp.entity.QuestionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<QuestionForm, Integer> {
    QuestionForm findById(int id);
    @Modifying
    @Query(value = "select * from QUIZ_QUESTIONS q where q.question_category = :category", nativeQuery = true)
    List<QuestionForm> findByCategory(String category);

}
