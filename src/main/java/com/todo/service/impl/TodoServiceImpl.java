package com.todo.service.impl;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.entity.Todo;
import com.todo.repository.TodoRepository;
import com.todo.service.ITodoService;

@Service
public class TodoServiceImpl implements ITodoService {

	@Autowired
	TodoRepository todoRepository;

	@Override
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public List<Todo> getAllTodo() {
		List<Todo> todoList = (List<Todo>) todoRepository.findAll();
		if (todoList != null && todoList.size() > 0) {
			return todoList;
		} else {
			throw new EntityNotFoundException(format("Data not available", Todo.class));
		}
	}

	@Override
	public Boolean deleteAllTodo() {
		try {
			todoRepository.deleteAll();
		} catch (Exception e) {
			throw new EntityNotFoundException(format("Some thing wrong", Todo.class));
		}
		return true;
	}

	@Override
	public Boolean deleteTodoWithIds(List<Long> idsList) {
		if (idsList != null && idsList.size() > 0) {
			List<Todo> todoList = new ArrayList<Todo>();
			idsList.forEach(id -> {
				Todo todo = new Todo();
				todo.setId(id);
				todoList.add(todo);
			});
			try {
				todoRepository.deleteAll(todoList);
			} catch (Exception e) {
				throw new EntityNotFoundException(format("Some thing wrong  ", Todo.class));
			}
		} else {
			throw new EntityNotFoundException(format("Id's can not be  Null ", Todo.class));
		}
		return true;
	}

}
