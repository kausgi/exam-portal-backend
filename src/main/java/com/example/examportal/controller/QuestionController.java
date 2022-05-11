package com.example.examportal.controller;

import com.example.examportal.model.exam.Question;
import com.example.examportal.model.exam.Quiz;
import com.example.examportal.repo.QuestionRepository;
import com.example.examportal.service.QuestionService;
import com.example.examportal.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
@CrossOrigin("*")
@Transactional
public class QuestionController {
    final QuestionService questionService;
    final QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) throws Exception {
        Question local = this.questionService.addQuestion(question);
        return ResponseEntity.ok(local);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable("id") long id) throws Exception {
        Question local = this.questionService.getQuestion(id);
        return ResponseEntity.ok(local);
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuestions() throws Exception {
        List<Question> questions = this.questionService.getQuestios();
        return ResponseEntity.ok(questions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@RequestBody Question question, @PathVariable("id") long id) throws Exception {
        Question local = this.questionService.updateQuestion(question, id);
        return ResponseEntity.ok(local);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") long id) throws Exception {
        this.questionService.deleteQuestion(id);
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("id") Long id) throws Exception {
//        Quiz quiz = new Quiz();
//        quiz.setId(id);
//        List<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questions);

        Quiz quiz = this.quizService.getQuiz(id);
        List<Question> questions = this.questionService.getQuestios();
        List __questions = new ArrayList(questions);
        if(__questions.size() > quiz.getNumberOfQuestions()){
            __questions = __questions.subList(0,quiz.getNumberOfQuestions());
        }
        Collections.shuffle(__questions);
        return ResponseEntity.ok(__questions);
    }

    @GetMapping("/quiz/all/{id}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("id") Long id) throws Exception {
        Quiz quiz = new Quiz();
        quiz.setId(id);
        List<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questions);

    }
}
