package com.example.examportal.service;

import com.example.examportal.model.exam.Question;
import com.example.examportal.model.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    public Question addQuestion(Question question) throws Exception;
    public Question getQuestion(Long qno) throws Exception;
    public List<Question> getQuestios() throws Exception;
    Question updateQuestion(Question question, Long id) throws Exception;
    public void deleteQuestion(Long qno) throws Exception;
    public List<Question> getQuestionsOfQuiz(Quiz quiz) throws Exception;
}
