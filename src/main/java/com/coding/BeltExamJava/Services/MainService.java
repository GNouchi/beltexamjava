package com.coding.BeltExamJava.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding.BeltExamJava.Models.Task;
import com.coding.BeltExamJava.Models.User;
import com.coding.BeltExamJava.Repositories.TaskRepository;
import com.coding.BeltExamJava.Repositories.UserRepository;


@Service
public class MainService {
	private final UserRepository userRepository;
	private final TaskRepository taskRepository;
	public MainService(UserRepository userRepository, TaskRepository taskRepository) {
		this.userRepository= userRepository;
		this.taskRepository = taskRepository;
	}

//	create
	public Task newTask(User creator, Task task) {
		Task newtask = task;
		System.out.println("assignee is : " +  task.getAssignee());
		System.out.println(creator.getId());
		newtask.setCreator(creator);
		taskRepository.save(newtask);
		System.out.println(newtask.getCreator());
		return task;
	}
	
// find all possible assignees
	
	public List<User> findAllPossibleAssignees(){
		List<User> allUsers= userRepository.findAll();
		List<User> result = new ArrayList<User>();
			for(User user:allUsers) {
				if(user.getAssignedtasks().size() <3) {
					result.add(user);
				}
			}
		return result;
	}

// find all tasks
	public List<Task> findAllTasks(){
		return taskRepository.findAll();
	}
//	find one task
	public Task findById(Long id) {
		Optional<Task> result= taskRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}
// delete task
	public void deleteTask(Task task) {
		System.out.println("deleting :" +task.getId());
		taskRepository.delete(task);
	}
//	find all tasks order by highest	
	public List<Task> findAllOrderByHighest(){
		return taskRepository.findAllOrderByHighest();	
	}
//	find all tasks order by lowest	
	public List<Task> findAllOrderByLowest(){
		return taskRepository.findAllOrderByLowest();	
	}	
// edit task 
	public void editTask(Task task) {
		System.out.println(task.getTaskname());;
		taskRepository.save(task);
		
		System.out.println("saved task!");
	}
	
}
