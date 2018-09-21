package com.coding.BeltExamJava.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;



@Entity
@Table(name="users")
public class User {
	   @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	   
	    @Pattern(regexp="^\\w+@[a-zA-Z0-9-_]+?.[a-zA-Z0-9]{2,15}$", message="Invalid Email")
	    @Email(message="Email must be valid")
	    private String email;

	    @Size(min=8, message="Password must be greater than 5 characters")
	    private String password;
	    @Transient
	    private String passwordConfirmation;

	    @Size(min=1, message ="Please enter a name")
	    private String name;
	
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;	    
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }

	    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
	    private List<Task> tasks= new ArrayList<Task>();

	    @Size(max=3)	    
	    @OneToMany(mappedBy="assignee", fetch = FetchType.LAZY)
	    private List<Task> assignedtasks= new ArrayList<Task>();
	    	    
	    public User() {
	    }
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}
		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		public List<Task> getTasks() {
			return tasks;
		}
		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
		}
		public List<Task> getAssignedtasks() {
			return assignedtasks;
		}
		public void setAssignedtasks(List<Task> assignedtasks) {
			this.assignedtasks = assignedtasks;
		}
	    
}
