/**
 * 
 */
package com.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.beans.Task;
import com.test.repositories.TaskRepository;

/**
 * @author Prabhmeet
 *
 */
@Service
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository taskRepository;
	
	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public Task saveTask(Task task) {
		
		com.test.entity.Task dbTask = new com.test.entity.Task();
		BeanUtils.copyProperties(task, dbTask);
		dbTask = taskRepository.save(dbTask);
		
		BeanUtils.copyProperties(dbTask, task);
		
		return task;
	}

	@Override
	public List<Task> getAllTasks() {
		
		List<com.test.entity.Task> allSavedTasks = taskRepository.findAll();
		List<Task> allTasks = new ArrayList<Task>();
		
		for(com.test.entity.Task dbTask : allSavedTasks) {
			Task task = new Task();
			BeanUtils.copyProperties(dbTask, task);
			allTasks.add(task);
		}
		
		return allTasks;
		
	}
	
	@Override
	public Task updateTask(Task task) {
		
		Optional<com.test.entity.Task> dbTask = taskRepository.findById(task.getId());
		
		if(dbTask.isPresent()) {
			dbTask.get().setDescription(task.getDescription());
			dbTask.get().setPriority(task.getPriority());
			dbTask.get().setRequester(task.getRequester());
			dbTask.get().setTitle(task.getTitle());
			
			taskRepository.save(dbTask.get());
			BeanUtils.copyProperties(dbTask.get(), task);
			
			return task;
		} else {
			return null;
		}
		
	}

	@Override
	public int deleteTask(Long id) {
		
		Optional<com.test.entity.Task> dbTask = taskRepository.findById(id);
		if(dbTask.isPresent()) {
			taskRepository.delete(dbTask.get());
			return 1;
		}
		else {
			return 0;
		}
	}


}
