package com.coding.BeltExamJava.Services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.coding.BeltExamJava.Models.User;
import com.coding.BeltExamJava.Repositories.UserRepository;


@Service
public class UserService {
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
//	register
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
        return userRepository.save(user);
	}
//	find by email
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
// find by id 
	public User findUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return (user.isPresent())? user.get():null;			 
	}
// authenticate user
	public boolean authenticateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
				return false;}
		if(BCrypt.checkpw(password, user.getPassword())) {
			return true;
				}
		return false;
	}
//	find all users
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
}
