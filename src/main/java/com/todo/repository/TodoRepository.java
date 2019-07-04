package com.todo.repository;

 
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.entity.Todo;
@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

 }