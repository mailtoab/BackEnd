package com.emaildashboard.restapi.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.emaildashboard.restapi.model.Component;
import com.emaildashboard.restapi.model.EmailHealthCheckup;

@Repository
public interface EmailDashboardDAO {
	List<Object> getEmailList(String input);
	void saveOrUpdateUserDetail(Component component);
	Component getUserDetail(String vzID);
	EmailHealthCheckup getEmailHC();
}
