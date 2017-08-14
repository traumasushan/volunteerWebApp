package com.gemasu.volunteerWebApp.conroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gemasu.volunteerWebApp.model.User;
import com.gemasu.volunteerWebApp.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/register")
	public String registerForm(){
		return "registration";
	}
	
	@RequestMapping(value= "/createUser", method = RequestMethod.POST)
	public String register(User user, Principal principal, Model model){
		System.out.println("saveeee");
		userService.save(user);
		if(principal!=null){
			model.addAttribute("user", principal.getName());
			return "redirect:/";
		}
		return "redirect:/login";
	} 
	@GetMapping("/viewUser")
	public String profile(Model model, Principal principal){
			model.addAttribute("profile", userService.findByUserName(principal.getName()));
			return "profile";
	}
	@PostMapping("/updateUser")
	public String updateProfile(User user){
		userService.save(user);
		return "redirect:/";
	}
	@GetMapping("/deleteUser/{username}")
	public String deleteUser( @PathVariable String username, Model model){
		userService.removeuser(userService.findByUserName(username));
		//model.addAttribute("user", null);
		return "forward:/logout";
	}
	
//	@GetMapping("/user/{id}")
//	public String getUser(@PathVariable String id, Model model){
//		model.addAttribute("user", userService.findById(id));
//		return "userDetail";
//	}
}
