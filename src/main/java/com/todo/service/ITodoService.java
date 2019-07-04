package com.todo.service;

import java.util.List;

import com.todo.entity.Todo;

public interface ITodoService {

	Todo saveTodo(Todo todo);

	List<Todo> getAllTodo();

	Boolean deleteAllTodo();

	Boolean deleteTodoWithIds(List<Long> idsList);

}
