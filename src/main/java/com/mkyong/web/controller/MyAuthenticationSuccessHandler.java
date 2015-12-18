package com.mkyong.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    		Authentication authentication) throws IOException, ServletException {
    	// This is actually not an error, but an OK message. It is sent to avoid redirects.
    	//String targetUrl = "admin";
    	//response.setContentType("Content-Type:application/json");
    	HttpSession session = request.getSession(false);
    	//session.setMaxInactiveInterval(60*180);
    	session.setAttribute("authorities", authentication.getAuthorities()); 
    	// response.sendRedirect(targetUrl);
    	//response.setHeader("Content-Type:application/json", value);
    	//response.sendError(HttpServletResponse.SC_OK);
    	//
    	response.getWriter().println("{");
    	response.getWriter().println("\"message\": \"Login successful\"");
    	response.getWriter().println("}");
    	//HelloController.
    	// response.sendError(HttpServletResponse.SC_OK);
    }
}
