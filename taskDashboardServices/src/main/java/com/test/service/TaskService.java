package com.test.service;

import java.util.List;

import com.test.beans.Task;

public interface TaskService {
	
	
	public Task saveTask (Task task);
	public List<Task> getAllTasks();
	public Task updateTask(Task task);
	public int deleteTask(Long id);

}
