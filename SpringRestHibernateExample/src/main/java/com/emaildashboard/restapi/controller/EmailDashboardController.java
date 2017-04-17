package com.emaildashboard.restapi.controller;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import com.emaildashboard.restapi.service.EmailDashboardService;
import com.emaildashboard.restapi.model.Component;
import com.emaildashboard.restapi.model.EmailHealthCheckup;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailDashboardController {
	
	@Autowired
	EmailDashboardService emailService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getEmailList/{input}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Object> getEmailList(@PathVariable String input) {
		return emailService.getEmailList(input);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/saveOrUpdateUserDetail", method = RequestMethod.POST, headers = "Accept=application/json")
	public void saveOrUpdateUserDetail(@RequestBody Component component) {	
		emailService.saveOrUpdateUserDetail(component);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getUserDetail/{vzID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Component getUserDetail(@PathVariable String vzID) {			
		Component component=emailService.getUserDetail(vzID);
		System.out.println(component.getDynamicSetting()+" "+component.getUserDetail().getVzID());
		return component;
	}	

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getEmailHC", method = RequestMethod.GET, headers = "Accept=application/json")
	public EmailHealthCheckup getEmailHC() {
		System.out.println("getEmailHC");
		return emailService.getEmailHC();
	}
}