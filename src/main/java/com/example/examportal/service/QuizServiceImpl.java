package com.example.examportal.service;

import com.example.examportal.model.exam.Category;
import com.example.examportal.model.exam.Quiz;
import com.example.examportal.repo.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService{

    final QuizRepository quizRepository;


    @Override
    public Quiz addQuiz(Quiz quiz) throws Exception {
        Quiz local = this.quizRepository.findByCode(quiz.getCode());
        if(local == null){
            local = new Quiz();
            if(Objects.nonNull(quiz.getCode()) && !"".equalsIgnoreCase(quiz.getCode())){
                local.setCode(quiz.getCode());
            }
            if(Objects.nonNull(quiz.getTitle()) && !"".equalsIgnoreCase(quiz.getTitle())){
                local.setTitle(quiz.getTitle());
            }
            if(Objects.nonNull(quiz.getDescription()) && !"".equalsIgnoreCase(quiz.getDescription())){
                local.setDescription(quiz.getDescription());
            }
            if(Objects.nonNull(quiz.getMaxMarks())){
                local.setMaxMarks(quiz.getMaxMarks());
            }
            if(Objects.nonNull(quiz.getNumberOfQuestions())){
                local.setNumberOfQuestions(quiz.getNumberOfQuestions());
            }
            if(Objects.nonNull(quiz.getDescription()) && !"".equalsIgnoreCase(quiz.getDescription())){
                local.setDescription(quiz.getDescription());
            }
            local.setActive(quiz.isActive());
            local.setCategory(quiz.getCategory());
            return this.quizRepository.save(local);
        }else {
            throw new Exception("Quiz already exist");
        }
    }

    @Override
    public Quiz getQuiz(Long id) throws Exception {
        Quiz local = this.quizRepository.findById(id).get();
        if(local != null){
            return local;
        }else {
            throw new Exception("No Quiz");
        }
    }

    @Override
    public void deleteQuiz(Long id) throws Exception {
        this.quizRepository.deleteById(id);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz, Long id) throws Exception {
        Quiz local = this.quizRepository.findById(id).get();
        if(local != null){
            if(Objects.nonNull(quiz.getCode()) && !"".equalsIgnoreCase(quiz.getCode())){
                local.setCode(quiz.getCode());
            }
            if(Objects.nonNull(quiz.getTitle()) && !"".equalsIgnoreCase(quiz.getTitle())){
                local.setTitle(quiz.getTitle());
            }
            if(Objects.nonNull(quiz.getDescription()) && !"".equalsIgnoreCase(quiz.getDescription())){
                local.setDescription(quiz.getDescription());
            }
            if(Objects.nonNull(quiz.getMaxMarks())){
                local.setMaxMarks(quiz.getMaxMarks());
            }
            if(Objects.nonNull(quiz.getNumberOfQuestions())){
                local.setNumberOfQuestions(quiz.getNumberOfQuestions());
            }
            if(Objects.nonNull(quiz.getDescription()) && !"".equalsIgnoreCase(quiz.getDescription())){
                local.setDescription(quiz.getDescription());
            }
            local.setCategory(quiz.getCategory());
            return this.quizRepository.save(local);
        }else{
            throw new Exception("Quiz not found");
        }
    }

    @Override
    public List<Quiz> getQuizes() throws Exception {
        return this.quizRepository.findAll();
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepository.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesByCategory(Category category) {
        return this.quizRepository.findByCategoryAndActive(category, true);
    }
}
