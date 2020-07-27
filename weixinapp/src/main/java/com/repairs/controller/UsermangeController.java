package com.repairs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repairs.service.UsermanageService;

@Controller
public class UsermangeController {
	@Autowired
	private UsermanageService usermanageService;
    @RequestMapping(value="/selectServiceman.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
    @ResponseBody
	public String selectServiceman(int id) {
		return usermanageService.selectServiceman(id);
	}
    @RequestMapping(value="/updateServiceman.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
    @ResponseBody
    public String updateServiceman(int id,int state){
    	String json="{\"state\":\"success\"}";
    	usermanageService.updateServiceman(id, state);
    	return json;
    }
}
