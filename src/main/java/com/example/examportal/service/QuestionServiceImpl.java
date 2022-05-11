package com.example.examportal.service;

import com.example.examportal.model.exam.Question;
import com.example.examportal.model.exam.Quiz;
import com.example.examportal.repo.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

    final QuestionRepository questionRepository;


    @Override
    public Question addQuestion(Question question) throws Exception {
        Question local = new Question();
            if(Objects.nonNull(question.getContent()) && !"".equalsIgnoreCase(question.getContent())){
                local.setContent(question.getContent());
            }
            if(Objects.nonNull(question.getOption1()) && !"".equalsIgnoreCase(question.getOption1())){
                local.setOption1(question.getOption1());
            }
            if(Objects.nonNull(question.getOption2()) && !"".equalsIgnoreCase(question.getOption2())){
                local.setOption2(question.getOption2());
            }
            if(Objects.nonNull(question.getOption3()) && !"".equalsIgnoreCase(question.getOption3())){
                local.setOption3(question.getOption3());
            }
            if(Objects.nonNull(question.getOption4()) && !"".equalsIgnoreCase(question.getOption4())){
                local.setOption4(question.getOption4());
            }
            local.setImage(question.getImage());
            local.setQuiz(question.getQuiz());
            local.setAnswer(question.getAnswer());
            return this.questionRepository.save(local);
    }

    @Override
    public Question getQuestion(Long id) throws Exception {
        Question local = this.questionRepository.findById(id).get();
        if(local != null){
            return local;
        }else{
            throw new Exception("No Questions");
        }

    }

    @Override
    public List<Question> getQuestios() throws Exception {
        return this.questionRepository.findAll();
    }

    @Override
    public Question updateQuestion(Question question, Long id) throws Exception {
        Question local = this.questionRepository.findById(id).get();
        if(local != null) {
            if (Objects.nonNull(question.getContent()) && !"".equalsIgnoreCase(question.getContent())) {
                local.setContent(question.getContent());
            }
            if (Objects.nonNull(question.getOption1()) && !"".equalsIgnoreCase(question.getOption1())) {
                local.setOption1(question.getOption1());
            }
            if (Objects.nonNull(question.getOption2()) && !"".equalsIgnoreCase(question.getOption2())) {
                local.setOption2(question.getOption2());
            }
            if (Objects.nonNull(question.getOption3()) && !"".equalsIgnoreCase(question.getOption3())) {
                local.setOption3(question.getOption3());
            }
            if (Objects.nonNull(question.getOption4()) && !"".equalsIgnoreCase(question.getOption4())) {
                local.setOption4(question.getOption4());
            }
            local.setImage(question.getImage());
            local.setQuiz(question.getQuiz());
            local.setAnswer(question.getAnswer());
            return this.questionRepository.save(local);
        }else {
            throw new Exception("Question not found");
        }
    }

    @Override
    public void deleteQuestion(Long id) throws Exception {
        Question local = this.questionRepository.findById(id).get();
        if(local != null){
            this.questionRepository.delete(local);
        }else {
            throw new Exception("Question not found");
        }
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Quiz quiz) throws Exception {
        return this.questionRepository.findByQuiz(quiz);
    }


}
