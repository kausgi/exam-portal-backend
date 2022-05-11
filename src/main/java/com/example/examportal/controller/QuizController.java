package com.example.examportal.controller;

import com.example.examportal.model.exam.Category;
import com.example.examportal.model.exam.Quiz;
import com.example.examportal.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
@CrossOrigin("*")
@Transactional
public class QuizController {
    final QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) throws Exception {
        Quiz local = this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(local);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable("id") Long id) throws Exception {
        Quiz local = this.quizService.getQuiz(id);
        return ResponseEntity.ok(local);
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes() throws Exception {
        List<Quiz> quizzes = this.quizService.getQuizes();
        return ResponseEntity.ok(quizzes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz, @PathVariable("id") Long id) throws Exception {
        Quiz local = this.quizService.updateQuiz(quiz, id);
        return ResponseEntity.ok(local);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) throws Exception {
        this.quizService.deleteQuiz(id);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getQuizzesOfCategory(@PathVariable("id") int id){
        Category category = new Category();
        category.setId(id);
        return ResponseEntity.ok (this.quizService.getQuizzesOfCategory(category));
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveQuizzes(){
        return ResponseEntity.ok(this.quizService.getActiveQuizzes());
    }

    @GetMapping("/category/active/{id}")
    public ResponseEntity<?> getActiveQuizzesByCategory(@PathVariable("id") int id){
        Category category = new Category();
        category.setId(id);
        return ResponseEntity.ok (this.quizService.getActiveQuizzesByCategory(category));
    }
}
