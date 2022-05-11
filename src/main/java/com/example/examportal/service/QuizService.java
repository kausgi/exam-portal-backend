package com.example.examportal.service;

import com.example.examportal.model.exam.Category;
import com.example.examportal.model.exam.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz) throws Exception;
    public Quiz getQuiz(Long id) throws Exception;
    public void deleteQuiz(Long id) throws Exception;
    public Quiz updateQuiz(Quiz quiz, Long id) throws Exception;
    public List<Quiz> getQuizes() throws Exception;

    List<Quiz> getQuizzesOfCategory(Category category);

    List<Quiz> getActiveQuizzes();

    List<Quiz> getActiveQuizzesByCategory(Category category);
}
