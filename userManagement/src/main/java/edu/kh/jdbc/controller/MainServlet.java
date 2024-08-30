package edu.kh.jdbc.controller;

import java.io.IOException;
import java.net.InetAddress;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/main")
public class MainServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		String ipAddress=req.getRemoteAddr();
		if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
		    InetAddress inetAddress=InetAddress.getLocalHost();
		    ipAddress=inetAddress.getHostAddress();
		}
		
		HttpSession session = req.getSession();
		
		session.setAttribute("ip", ipAddress);
		
		
		System.out.println("클라이언트IP 주소: "+ipAddress);
		
		String path = "/WEB-INF/views/main.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
		
	}
	
}
