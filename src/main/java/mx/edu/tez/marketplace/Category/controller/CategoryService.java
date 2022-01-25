package mx.edu.tez.marketplace.Category.controller;

import mx.edu.tez.marketplace.Category.model.Category;
import mx.edu.tez.marketplace.Category.model.CategoryRepository;
import mx.edu.tez.marketplace.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Transactional
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("ok",false,categoryRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(rollbackOn = {SQLException.class})
    public ResponseEntity<Message> save(Category category){
        Optional<Category> existsCategory = categoryRepository.findByDescription(category.getDescription());
        if (existsCategory.isPresent()){
            return new ResponseEntity<>(new Message("La categoria ya existe",true,null), HttpStatus.OK);
        }
        Category savedCategory = categoryRepository.saveAndFlush(category);
        return new ResponseEntity<>(new Message("Categoria se a regristrada correcata,emte",false,savedCategory),HttpStatus.OK);
    }

    @Transactional(rollbackOn = {SQLException.class})
    public ResponseEntity<Message> save(Category category){
        Optional<Category> existsCategory = categoryRepository.findByDescription(category.getDescription());
        if (existsCategory.isPresent()){
        }
        Category savedCategory = categoryRepository.saveAndFlush(category);
    }
}
