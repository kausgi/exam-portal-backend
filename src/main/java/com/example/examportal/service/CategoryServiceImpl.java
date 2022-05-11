package com.example.examportal.service;

import com.example.examportal.model.exam.Category;
import com.example.examportal.repo.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService{

    final CategoryRepository categoryRepository;


    @Override
    public Category addCategory(Category category) throws Exception {
        Category local = this.categoryRepository.findByCode(category.getCode());
        if(local == null){
            local = new Category();
            if(Objects.nonNull(category.getCode()) && !"".equalsIgnoreCase(category.getCode())){
                local.setCode(category.getCode());
            }
            if(Objects.nonNull(category.getTitle()) && !"".equalsIgnoreCase(category.getTitle())){
                local.setTitle(category.getTitle());
            }
            if(Objects.nonNull(category.getDescription()) && !"".equalsIgnoreCase(category.getDescription())){
                local.setDescription(category.getDescription());
            }
            return this.categoryRepository.save(local);
        }else {
            throw new Exception("Category already exist");
        }
    }

    @Override
    public Category updateCategory(Category category, int id) throws Exception {
        Category local = this.categoryRepository.findById(id).get();
        if(local != null){
            if(Objects.nonNull(category.getCode()) && !"".equalsIgnoreCase(category.getCode())){
                local.setCode(category.getCode());
            }
            if(Objects.nonNull(category.getTitle()) && !"".equalsIgnoreCase(category.getTitle())){
                local.setTitle(category.getTitle());
            }
            if(Objects.nonNull(category.getDescription()) && !"".equalsIgnoreCase(category.getDescription())){
                local.setDescription(category.getDescription());
            }
            return this.categoryRepository.save(local);
        }else{
            throw new Exception("Category not found");
        }

    }

    @Override
    public void deleteCategory(int id) throws Exception {
        Category local = this.categoryRepository.findById(id).get();
        if(local != null){
            this.categoryRepository.deleteById(id);
        }else {
            throw new Exception("Category doesn't exist");
        }
    }

    @Override
    public Category getCategory(int id) throws Exception {
        Category local = this.categoryRepository.findById(id).get();
        if(local != null){
            return local;
        }else {
            throw new Exception("No Category");
        }
    }

    @Override
    public List<Category> getCategories() throws Exception {
        return categoryRepository.findAll();
    }
}
