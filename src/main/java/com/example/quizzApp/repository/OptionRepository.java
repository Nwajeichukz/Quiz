package com.example.quizzApp.repository;

import com.example.quizzApp.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<QuestionOption, Long> {
    Optional<QuestionOption> findById(long id);

}