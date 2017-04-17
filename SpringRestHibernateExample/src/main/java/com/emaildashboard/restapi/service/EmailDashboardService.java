package com.emaildashboard.restapi.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.emaildashboard.restapi.model.EmailHealthCheckup;
import com.emaildashboard.restapi.model.Component;

@Service
public interface EmailDashboardService {
	List<Object> getEmailList(String input);
	void saveOrUpdateUserDetail(Component component);
	Component getUserDetail(String vzID);
	EmailHealthCheckup getEmailHC();	
}
