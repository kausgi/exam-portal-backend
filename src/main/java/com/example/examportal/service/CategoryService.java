package com.example.examportal.service;

import com.example.examportal.model.exam.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    public Category addCategory(Category category) throws Exception;
    public Category updateCategory(Category category, int id) throws Exception;
    public void deleteCategory(int id) throws Exception;
    public Category getCategory(int id) throws Exception;
    public List<Category> getCategories() throws Exception;
}
