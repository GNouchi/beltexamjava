package com.coding.BeltExamJava.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coding.BeltExamJava.Models.Task;


public interface TaskRepository extends CrudRepository<Task, Long>{
	List<Task> findAll();
	
	@Query(value="select * from tasks order by priority desc", nativeQuery=true)
	List<Task> findAllOrderByHighest();
	@Query(value="select * from tasks order by priority asc",nativeQuery=true)
	List<Task> findAllOrderByLowest();

}
