/**
 * 
 */
package com.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.beans.Task;
import com.test.service.TaskService;

/**
 * @author Prabhmeet
 *
 */

@RestController
public class TaskController {
	
	private final TaskService taskService;
	
	@Autowired
	public  TaskController(TaskService taskService) {
		super();
		this.taskService= taskService;
		
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task ) {
		
		Task tsk = taskService.saveTask(task);
		return new ResponseEntity<Task>(tsk,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/tasks")
	public ResponseEntity<Object> getAllTasks(){
		return ResponseEntity.ok().body(taskService.getAllTasks());
	}
	
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable (value="id") Long id, @RequestBody Task task){
		task.setId(id);
		Task tsk = taskService.updateTask(task);
		if(tsk==null) {
			return ResponseEntity.ok().body(new String("Task not found"));
		}
		return ResponseEntity.ok().body(tsk);
	}
	
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Object> deleteTask (@PathVariable (value="id") Long id){
		int returnCode = taskService.deleteTask(id);
		if(returnCode==1) {
			return ResponseEntity.ok().body(new String("Task Deleted Successfully"));
			
		} else if (returnCode==-1){
			return ResponseEntity.ok().body(new String("Task Not Found"));
			
		} 
			
		return ResponseEntity.ok().body(new String ("Deletion operation failed due to unknown reasons"));
		
	}


}
