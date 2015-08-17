package com.tenchael.dubbo.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenchael.dubbo.api.DemoService;
import com.tenchael.dubbo.bean.User;

@Controller
public class HomeController {

	@Autowired
	private DemoService demoService;

	@RequestMapping(value = { "/", "index", "home" }, method = RequestMethod.GET)
	@ResponseBody
	public String index(
			@RequestParam(value = "name", defaultValue = "Dubbo") String name) {
		return demoService.sayHello(name);
	}

	@RequestMapping(value = { "info" }, method = RequestMethod.GET)
	@ResponseBody
	public Object getInfo(
			@RequestParam(value = "name", required = true) String name) {
		User user = demoService.getUserByName(name);
		return user;
	}

	@RequestMapping(value = { "set" }, method = RequestMethod.GET)
	@ResponseBody
	public Object setInfo(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "age", required = true) int age) {
		demoService.setUser(new User(name, age));
		return "OK";
	}

}