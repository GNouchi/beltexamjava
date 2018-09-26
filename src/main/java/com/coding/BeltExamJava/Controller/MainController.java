package com.coding.BeltExamJava.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.BeltExamJava.Models.Task;
import com.coding.BeltExamJava.Models.User;
import com.coding.BeltExamJava.Services.MainService;
import com.coding.BeltExamJava.Services.UserService;
import com.coding.BeltExamJava.Validator.UserValidator;


@Controller
public class MainController {
	private final UserService userService; 
	private final UserValidator userValidator;
	private final MainService mainService;
    String star = String.join("", Collections.nCopies(25, "*"));
	List<Integer> priorities = new ArrayList<Integer>();
	
	public MainController(UserService userService, UserValidator userValidator, MainService mainService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.mainService = mainService;
		priorities.add(1);
		priorities.add(2);
		priorities.add(3);
	} 
	
	
	@RequestMapping({"/","/home","/login", "/tasks"} )
	public String master(HttpSession session, @ModelAttribute("user") User user, Model model) {
		if(session.getAttribute("userid")==null) {
			return "login";
		}
		if(session.getAttribute("userid")!=null) {
			Long userid =  (Long) session.getAttribute("userid");
			model.addAttribute("user", userService.findUserById(userid));
			if ((Integer) session.getAttribute("order") == null || (Integer) session.getAttribute("order") ==0) {
				session.setAttribute("order", 0);
				model.addAttribute("tasks", mainService.findAllOrderByHighest());				
			}
			else {
				model.addAttribute("tasks", mainService.findAllOrderByLowest());								
			}
			return "index";
		}
		return "error";
		
	}	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	@RequestMapping("/tasks/{eventid}")
	public String showTask(@PathVariable("eventid")Long eventid, Model model, HttpSession session) {
		Task task = mainService.findById(eventid);
		if(task==null) {
			return "redirect:/";
		}
		model.addAttribute("task", task);
		long userid = (long) session.getAttribute("userid");
		if(task.getCreator().getId()==userid) {
			model.addAttribute("isCreator", 1);			
		}
		if(task.getAssignee().getId()==userid) {
			model.addAttribute("isAssignee", 1);			
		}
		return "show";
	}

	@RequestMapping("/tasks/new")
	public String createTask(@ModelAttribute("task")Task task, Model model) {		
		List<User> assignees = mainService.findAllPossibleAssignees();
		model.addAttribute("assignees", assignees);
		model.addAttribute("priorities", priorities);
		return "newtask";
	}

	@RequestMapping("/tasks/{eventid}/edit")
	public String editTask(@PathVariable("eventid") Long eventid, Model model) {		
		List<User> assignees = mainService.findAllPossibleAssignees();
		Task currenttask= mainService.findById(eventid);
		if (currenttask==null) {return "redirect:/";}
		if(assignees.contains(currenttask.getAssignee())) {
			assignees.remove(currenttask.getAssignee());
		}
		model.addAttribute("task", currenttask);
		model.addAttribute("assignees", assignees);
		model.addAttribute("priorities", priorities);
		return "edit";
	}
	
//~~~~~~~~~~~	Operations ~~~~~~~~~~~~//
// 	registration
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "login";
        }
        User newuser = userService.registerUser(user);
        session.setAttribute("userid", newuser.getId());
        return "redirect:/";
    }
//	login
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password")String password, Model model, HttpSession session) {
    	boolean passwordsMatch = userService.authenticateUser(email, password);
    	if(passwordsMatch) {
    		session.setAttribute("userid", userService.findUserByEmail(email).getId());
    		return "redirect:/";
    	}
			model.addAttribute("error","Invalid credentials .... (╯°□°）╯︵ ┻━┻)");
			model.addAttribute("user", new User());
			return "login";    	
    }
//	new task
	@RequestMapping(value= "/tasks/new", method=RequestMethod.POST)
	public String newTask(@Valid @ModelAttribute("task") Task task, BindingResult result, HttpSession session, RedirectAttributes ra) {
		if(session.getAttribute("userid")==null) {
			ra.addFlashAttribute("error", "Please enter a name ");
			return "redirect:/tasks/new";
		}
        if(result.hasErrors()) {
			ra.addFlashAttribute("error", "Please enter a name ");
			return "redirect:/tasks/new";
        }
        Long userid = (Long) session.getAttribute("userid");
        User creator = userService.findUserById(userid);
        mainService.newTask(creator, task);
        System.out.println("create successful redirect..");
        return "redirect:/";

	}
// delete task
	@RequestMapping(value="tasks/{eventid}/delete")
	public String deleteTask(@PathVariable("eventid") Long eventid, HttpSession session) {
		Long userid = (Long) session.getAttribute("userid");
		if(userid==null) {
			System.out.println("redirect userid null");
			return "redirect:/login"; 
		}
		Task task = mainService.findById(eventid);
		if(task == null) {
			System.out.println("redirect eventid null");
			return "redirect:/"; 
		}
		if(task.getCreator().getId()== userid) {
			mainService.deleteTask(task);
		}
		System.out.println("redirect nothing hit");
		return "redirect:/"; 
	}
// complete task
	@RequestMapping(value="tasks/{eventid}/complete")
	public String completeTask(@PathVariable("eventid") Long eventid, HttpSession session) {
		Long userid = (Long) session.getAttribute("userid");
		if(userid==null) {
			System.out.println("redirect userid null");
			return "redirect:/login"; 
		}
		Task task = mainService.findById(eventid);
		if(task == null) {
			System.out.println("redirect eventid null");
			return "redirect:/"; 
		}
		if(task.getAssignee().getId()== userid) {
			mainService.deleteTask(task);
		}
		System.out.println("bool "+(task.getAssignee().getId()== userid));
		System.out.println("redirect nothing hit");
		return "redirect:/"; 
	}
// order
	@RequestMapping("/orderhigh")
	public String orderhigh(HttpSession session) {
		session.setAttribute("order", 0);
		return "redirect:/";
	}
	@RequestMapping("/orderlow")
	public String orderlow(HttpSession session) {
		System.out.println("hit order low");
		session.setAttribute("order", 1);
		return "redirect:/";
	}
	@RequestMapping(value="/tasks/{eventid}/edit", method=RequestMethod.PUT)
	public String editTask(@PathVariable("eventid") Long eventid, Model model, HttpSession session, @ModelAttribute("task")Task task, BindingResult result) {		
		System.out.println("hit edit!");
		List<User> assignees = mainService.findAllPossibleAssignees();
		if (result.hasErrors()|| task.getTaskname().isEmpty()) {
			System.out.println("errors found, escaping..");
//			if(assignees.contains(task.getAssignee())) {
//				assignees.remove(task.getAssignee());
//			}
//			model.addAttribute("assignees", assignees);
//			model.addAttribute("priorities", priorities);
//			model.addAttribute("task", task);
//			model.addAttribute("error", "name cannot be empty!");
//			return "edit";
			return "redirect:/tasks/"+eventid+"/edit";
		}
		long userid  =  (long)  session.getAttribute("userid");
		long creatorid = task.getCreator().getId();
		System.out.println("userid : "+ userid);
		System.out.println("creatorid : "+ creatorid);
		System.out.println(creatorid==userid);

		if(userid== 0 || creatorid==0) {
			System.out.println("redirected cause nul");
			return "redirect:/";
		}
		System.out.println("taskname edit: "+  task.getTaskname());
		if(creatorid==userid) {
			System.out.println("sending to save!");
			mainService.editTask(task);
		}
		System.out.println("how even...");
		return "redirect:/tasks/"+eventid+"/edit";
	}
}
