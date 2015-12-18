package com.mkyong.web.controller;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController {

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}
	
	//Spring Security see this :
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "time", required = false) String time,
			@RequestParam(value = "logout", required = false) String logout) {

			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
			}
			
			if (time != null) {
				model.addObject("time", "Invalid session!");
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("login");

			return model;

		}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	        System.out.println("1Logout");
	    }
	    System.out.println("2Logout");
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping(value="/checkauth", method = RequestMethod.GET)
	public ModelAndView checkauthPage () {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    ModelAndView model = new ModelAndView();
	    if (auth != null && auth.isAuthenticated() ){   
	    	
	    	String roles[] = {};
	    	String resroles = "";
	    	Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
	    	for (GrantedAuthority authority : authorities) {
	    		//GrantedAuthority authority;
				roles = (String[]) ArrayUtils.add(roles, authority.getAuthority());
				resroles = Arrays.toString(roles);
	    	}
	    	//roles = roles + "]";
			model.addObject("title", "checkauth");
			model.addObject("message", "You are logged in!");
			model.addObject("roles", resroles);
			model.setViewName("checkauth");
			
	    }
	    else {
	    	model.addObject("title", "checkauth");
			model.addObject("message", "You are not logged in!");
			model.setViewName("checkauth");
	    }
	    return model;
	}

}