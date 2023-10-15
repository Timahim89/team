package com.timahim.javaexam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.timahim.javaexam.models.Team;
import com.timahim.javaexam.models.User;
import com.timahim.javaexam.services.TeamService;
import com.timahim.javaexam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class TeamController {
	
	@Autowired HttpSession session;
	
	//Dont forget fairService
	@Autowired
	public TeamService teamServ;
	
	@Autowired
	public UserService userServ;
	
	//route to display add team form
	@GetMapping("/teams/new")
	public String addNewTeam(Model viewModel,
			@ModelAttribute("newTeam") Team newTeam) {
	 	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	Long userId = (Long) session.getAttribute("userId");
    	//talked to service to grab user by the id in session and then save in our model so JSP can use it. 
    	viewModel.addAttribute("loggedUser", userServ.findById(userId));
    	// talk to Service to grab all classes then send to JSP
    	return "createTeam.jsp";
	}
	
	//add to DB
	@PostMapping("/teams/new")
	public String addTeamToDB(@Valid @ModelAttribute("newTeam")Team newTeam, 
			BindingResult result,Model viewModel) {
		//if no one in session, send user back
	 	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
	 	//link loggedUser to user ID
    	Long userId = (Long) session.getAttribute("userId");
    	User loggedUser = userServ.findById(userId);
	 	if (result.hasErrors()) {
	    	viewModel.addAttribute("loggedUser", loggedUser);
	 		return "createTeam.jsp";
	 	}
    	// talk to Service to grab all classes then send to JSP
	 	newTeam.setCreator(loggedUser);
    	Team savedTeam = teamServ.createTeam(newTeam);//way to link user id
		return "redirect:/teams/" + savedTeam.getId();
	}
	
	//view page
	@GetMapping("/teams/{id}")
	public String viewTeam(@PathVariable Long id, Model viewModel) {
	 	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	Long userId = (Long) session.getAttribute("userId");
    	//talked to service to grab user by the id in session and then save in our model so JSP can use it. 
    	viewModel.addAttribute("loggedUser", userServ.findById(userId));
    	//Get one Team and send to JSP 
    	viewModel.addAttribute("thisTeam", teamServ.readOneTeam(id));
		return "teamDetail.jsp";
	}
	
	//edit in DB
	@PutMapping("/teams/{id}/edit")
	public String editTeamToDB(@PathVariable Long id, Model viewModel,
			@Valid @ModelAttribute("thisTeam") Team thisTeam, BindingResult result) {
	 	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
	 	if(result.hasErrors()) {
	    viewModel.addAttribute("originalName", teamServ.readOneTeam(id).getTeamName());

	 		return"editTeam.jsp";
	 	}
	 	teamServ.updateTeam(thisTeam);
		return "redirect:/home";
	}
	
	//edit Page
	@GetMapping("/teams/{id}/edit")
	public String editTeam(@PathVariable Long id, Model viewModel) {
	 	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}

    	Long userId = (Long) session.getAttribute("userId");
    	//talked to service to grab user by the id in session and then save in our model so JSP can use it. 
    	Team thisTeam = teamServ.readOneTeam(id);
    	
    	//check to see if logged in user is the one added this edit page. 
//	 	if(userId != thisTeam.getCreator().getId()) {
//	 		return "redirect:/home";
//	 	}
	 		
    	viewModel.addAttribute("thisTeam", thisTeam);
    	viewModel.addAttribute("originalName", thisTeam.getTeamName());
		return "editTeam.jsp";
	}
	
	//Delete from DB
	@DeleteMapping("/teams/{id}")
	public String deleteTeamFromDB(@PathVariable Long id, Model viewModel) {
	 	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
	 	teamServ.deleteTeam(id);
		return "redirect:/home";
	}
}
