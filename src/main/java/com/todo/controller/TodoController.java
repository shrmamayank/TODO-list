package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.entity.Todo;
import com.todo.service.ITodoService;
import com.todo.util.TodoResponseBody;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
	@Autowired
	ITodoService iTodoService;

	@PostMapping()
	public ResponseEntity<? extends Object> saveTodo(@RequestBody Todo Todo) {
		Todo = iTodoService.saveTodo(Todo);
		return ResponseEntity.ok().body(new TodoResponseBody<Todo>(Todo));
	}

	@GetMapping()
	public ResponseEntity<? extends Object> getAllTodo() {
		List<Todo> Todo = iTodoService.getAllTodo();
		return ResponseEntity.ok().body(new TodoResponseBody<List<Todo>>(Todo));
	}

	@DeleteMapping()
	public ResponseEntity<? extends Object> deleteAllTodo() {
		Boolean response = iTodoService.deleteAllTodo();
		return ResponseEntity.ok().body(new TodoResponseBody<Boolean>(response, "All Todo deleted successfully"));

	}

	@PostMapping("/delete")
	public ResponseEntity<? extends Object> deleteTodoWithIds(@RequestBody List<Long> idsList) {
		Boolean response = iTodoService.deleteTodoWithIds(idsList);
		return ResponseEntity.ok().body(new TodoResponseBody<Boolean>(response, "Selected todo  deleted successfully"));

	}
}