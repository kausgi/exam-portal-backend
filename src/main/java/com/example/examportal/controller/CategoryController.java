package com.example.examportal.controller;

import com.example.examportal.model.exam.Category;
import com.example.examportal.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/category")
@Transactional
public class CategoryController {

    final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category) throws Exception {
        Category local = this.categoryService.addCategory(category);
        return ResponseEntity.ok(local);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") int id) throws Exception {
        Category local = this.categoryService.getCategory(id);
        return ResponseEntity.ok(local);
    }

    @GetMapping("/")
    public ResponseEntity<?> getCategories() throws Exception {
        List<Category> categories = this.categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable("id") int id) throws Exception {
        Category local = this.categoryService.updateCategory(category, id);
        return ResponseEntity.ok(local);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") int id) throws Exception {
        this.categoryService.deleteCategory(id);
    }
}
