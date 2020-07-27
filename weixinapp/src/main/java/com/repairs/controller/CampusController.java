package com.repairs.controller;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repairs.bo.Campusbo;
import com.repairs.service.CampusService;

@Controller
public class CampusController {
	@Autowired
	private CampusService campusService;
	@RequestMapping(value="/selectAllCampus.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String selectAllCampus(){
		List<Campusbo> list=campusService.selectAllCampus();
		String json=JSONArray.fromObject(list).toString();
		return json;
	}
	
}
