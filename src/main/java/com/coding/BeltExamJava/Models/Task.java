package com.coding.BeltExamJava.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="tasks")
public class Task {
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
		
		@Size(min=1, max=50)
		private String taskname;
		
		@Min(1)
		@Max(3)
		private Integer priority;
		
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="creator_id")
	    private User creator;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="assignee_id")
	    private User assignee;

	    public Task() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTaskname() {
			return taskname;
		}

		public void setTaskname(String taskname) {
			this.taskname = taskname;
		}

		public Integer getPriority() {
			return priority;
		}

		public void setPriority(Integer priority) {
			this.priority = priority;
		}

		public User getCreator() {
			return creator;
		}

		public void setCreator(User creator) {
			this.creator = creator;
		}

		public User getAssignee() {
			return assignee;
		}

		public void setAssignee(User assignee) {
			this.assignee = assignee;
		}

}
