package com.mkyong.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

    	if (authentication != null && authentication.getDetails() != null) {
            try {
            	request.getSession().invalidate();
                System.out.println("User Successfully Logout");
                response.getWriter().println("{");
            	response.getWriter().println("\"message\": \"Logout successful\"");
            	response.getWriter().println("}");
                //you can add more codes here when the user successfully logs out,
                //such as updating the database for last active.
            } catch (Exception e) {
                e.printStackTrace();
                e = null;
            }
        }

    	response.setStatus(HttpServletResponse.SC_OK);
        //redirect to login
    	//response.sendRedirect("login"); 
    }
}