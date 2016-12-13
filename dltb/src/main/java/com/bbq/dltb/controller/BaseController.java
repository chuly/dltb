package com.bbq.dltb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import cn.jpush.api.report.UsersResult.User;

@RestController
public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	@Value("#{configProperties['base_addr']}")
	private String base_addr;//http://127.0.0.1:8080
	
	@Autowired
	protected HttpServletRequest httpRequest;
	@Autowired
	protected HttpSession httpSession;
	@Autowired
	protected HttpServletResponse httpResponse;

	public User getCurrentUser() {
		User u = (User) httpRequest.getSession().getAttribute("user");
		return u;
	}

	
}
