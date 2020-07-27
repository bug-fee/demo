package com.repairs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.repairs.serviceImpl.CoreService;
import com.repairs.weixinutil.SignUtil;

@Controller
public class CoreServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@RequestMapping(value="/CoreServlet.do",method=RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//微信加密签名
		String signature = request.getParameter("signature");
		//时间截
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		//校验是否接入成功
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
			//System.out.println(echostr);
		}
		out.close();
		out = null;
	}
	@RequestMapping(value="/CoreServlet.do",method=RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//接收参数：微信加密签名，时间截，随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce  = request.getParameter("nonce");
		PrintWriter out = response.getWriter();
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			//调用核心服务类接收处理请求
			String respXml;
			try {
				respXml = CoreService.processRequest(request);
				out.print(respXml);
				System.out.println(respXml);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		out.close();
		out = null;
	}



}
