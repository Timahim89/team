package com.timahim.javaexam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.timahim.javaexam.models.LoginUser;
import com.timahim.javaexam.models.User;
import com.timahim.javaexam.services.TeamService;
import com.timahim.javaexam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
    
    // Add once service is implemented:
     @Autowired
     private UserService userServ;
     
     @Autowired TeamService teamServ;
//    NOTE: We can declare the @ModelAttribute or Model viewModel to create attributes but we cannot use both in this example.
//     @GetMapping("/")
//     public String index(@ModelAttribute("newUser") UserModel newUser, @ModelAttribute("newLogin") LoginUserModel newLogin) { 
//     
//         // Bind empty User and LoginUser objects to the JSP
//         // to capture the form input
//         return "loginReg.jsp";
//     }
     
    @GetMapping("/")
    public String index(Model viewModel) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
    	viewModel.addAttribute("registerUser", new User()); //this or the one within
    	viewModel.addAttribute("newLogin", new LoginUser());
        return "loginReg.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerUser") User registerUser, 
			BindingResult result, @ModelAttribute("newLogin") LoginUser newLogin, HttpSession session) { /* , HttpSession session, Model model */
        User theNewUser = userServ.register(registerUser, result); 
        if(result.hasErrors()) {

            return "loginReg.jsp";
        } else {
        	//setAttribute allows us to create a variable but with session,
        	//...this allows the browser to keep this variable in cookie form.
        	//...in other words, the web browser will remember this variable even if the browser closes.
        	session.setAttribute("userId", theNewUser.getId()); 
        	//prior to redirecting, the userId is set with the id of the new registered user
        	//... and because Attributes are global, the dashboard will be able to read this
            return "redirect:/home";
        }
        

    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        

    	User foundUser = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("registerUser", new User());
            return "loginReg.jsp";
        } else {
        	session.setAttribute("userId", foundUser.getId());
        	return "redirect:/home";
        }
        
    }
    
    @GetMapping("/home")
    public String landingPage(HttpSession session, Model viewModel) {
    	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	Long userId = (Long) session.getAttribute("userId");
    	viewModel.addAttribute("loggedUser", userServ.findById(userId));
    	viewModel.addAttribute("allTeams", teamServ.readAllTeams()); //Talk to service to grab all Teams, then to jsp
    	return "home.jsp";
    }
    
    @GetMapping("/logout")
	public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";

    }
}