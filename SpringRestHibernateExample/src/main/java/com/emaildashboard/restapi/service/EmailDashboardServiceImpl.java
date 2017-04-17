package com.emaildashboard.restapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.emaildashboard.restapi.dao.EmailDashboardDAO;
import com.emaildashboard.restapi.model.Component;
import com.emaildashboard.restapi.model.EmailHealthCheckup;;

@Service
public class EmailDashboardServiceImpl implements EmailDashboardService {

	@Autowired
	EmailDashboardDAO emailDao;

	@Transactional
	public List<Object> getEmailList(String input) {
		return emailDao.getEmailList(input);
	}

	@Transactional
	public void saveOrUpdateUserDetail(Component component) {
		emailDao.saveOrUpdateUserDetail(component);
	}

	@Transactional
	public Component getUserDetail(String vzID) {
		return emailDao.getUserDetail(vzID);
	}
	
	@Transactional
	public EmailHealthCheckup getEmailHC() {
		return emailDao.getEmailHC();
	}
}