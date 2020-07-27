package com.repairs.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.repairs.serviceImpl.UserDispatcherService;

@Controller
public class UserDispatcherController {
	@Autowired
	private UserDispatcherService userDispatcherService;
	@RequestMapping("/userdispatcher.do")
	public void userDispatcher(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url = userDispatcherService.getDispatcherUrl(request);
		response.sendRedirect(url);
	}
	
	@RequestMapping("/repariDispatcher.do")
	public void repariDispatcher(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url = userDispatcherService.getRepairDispatcherUrl(request);
		response.sendRedirect(url);
	}
}
