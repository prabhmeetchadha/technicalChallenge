package com.test.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.test.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findBystatus(String status);
	Optional<Task> findById(Long Id);
	

}
