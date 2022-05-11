package com.example.examportal.repo;

import com.example.examportal.model.exam.Category;
import com.example.examportal.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz findByCode(String code);

    List<Quiz> findByCategory(Category category);

    List<Quiz> findByActive(boolean b);
    List<Quiz> findByCategoryAndActive(Category category, boolean b);

    @Modifying
    @Query("delete from Quiz q where q.id=:id ")
    void deleteById(@RequestParam("id") Long id);
}
