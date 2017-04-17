package com.emaildashboard.restapi.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emaildashboard.restapi.model.Component;
import com.emaildashboard.restapi.model.EmailHealthCheckup;
import com.emaildashboard.restapi.utility.MyResultTransformer;

@Repository
public class EmailDashboardDAOImpl implements EmailDashboardDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Object> getEmailList(String input) {
		System.out.println("Inside getEmailList DAO");
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = null;

		if (input.substring(0, 1).matches("\\d")) {
			query = session.createSQLQuery(
					"SELECT evd.EMAIL_SEQ_ID, ed.EVENT_NAME, evd.ORDER_ID, to_char(evd.INSERT_DATE, 'DD-MM-YYYY HH24:MI:SS') as INSERT_DATE, to_char(evd.STATUS_DATE, 'DD-MM-YYYY HH24:MI:SS') as STATUS_DATE, ec.EMAIL_CONTENT "
							+ "FROM SSP_FLOW.EMAIL_EVENT_DETAILS evd, SSP_FLOW.EVENT_TEMPLATE ed, SSP_FLOW.EMAIL_CONTENT ec WHERE evd.event_id=ed.event_id "
							+ "AND evd.EMAIL_SEQ_ID=ec.EMAIL_SEQ_ID AND evd.EMAIL_SEQ_ID=:input order by evd.INSERT_DATE");
		} else {
			query = session.createSQLQuery(
					"SELECT evd.EMAIL_SEQ_ID, ed.EVENT_NAME, evd.ORDER_ID, to_char(evd.INSERT_DATE, 'DD-MM-YYYY HH24:MI:SS') as INSERT_DATE, to_char(evd.STATUS_DATE, 'DD-MM-YYYY HH24:MI:SS') as STATUS_DATE, ec.EMAIL_CONTENT "
							+ "FROM SSP_FLOW.EMAIL_EVENT_DETAILS evd, SSP_FLOW.EVENT_TEMPLATE ed, SSP_FLOW.EMAIL_CONTENT ec WHERE evd.event_id=ed.event_id "
							+ "AND evd.EMAIL_SEQ_ID=ec.EMAIL_SEQ_ID AND evd.order_id=:input order by evd.INSERT_DATE");
		}
		query.setParameter("input", input);
		query.setResultTransformer(MyResultTransformer.INSTANCE);
		System.out.println(query.getQueryString());
		return query.list();
	}

	@Override
	public void saveOrUpdateUserDetail(Component component) {
		System.out.println("Inside insertAccess DAO");
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(component.getUserDetail());
		session.saveOrUpdate(component);
	}

	public Component getUserDetail(String vzID) {
		System.out.println("Inside getUserDetail DAO: " + vzID);
		Session session = this.sessionFactory.getCurrentSession();

		Query q = session.createQuery("from Component c where c.userDetail.vzID=:vzID");
		q.setParameter("vzID", vzID);
		Component component = (Component) q.uniqueResult();

		return component;
	}

	public EmailHealthCheckup getEmailHC() {
		System.out.println("Inside getEmailList DAO");
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = null;
		EmailHealthCheckup emailHealthCheckup=new EmailHealthCheckup();
		
		//Delivery and Error Count on Yesterday
		query = session.createSQLQuery("select status, count(1) as count from EMAIL_EVENT_DETAILS "
				+ "where insert_date BETWEEN TRUNC(SYSDATE - 1) AND TRUNC(SYSDATE) - 1/86400 "
				+ "and status in ('DE','E') group by status");
		List<Object> deliveryErrorCount=query.list();	
		
		//Processed By Server on Yesterday
		query = session.createSQLQuery("select upper(processed_by_system) as server, count(1) as count from EMAIL_EVENT_DETAILS "
				+ "where insert_date BETWEEN TRUNC(SYSDATE - 1) AND TRUNC(SYSDATE) - 1/86400 "
				+ "group by upper(PROCESSED_BY_SYSTEM)");
		List<Object> processedBySystem=query.list();
		
		//Total Pending Count
		query = session.createSQLQuery("select count(1) as count from EMAIL_EVENT_DETAILS "
				+ "where insert_date >= sysdate-1 and status in ('Y','T','X')").addScalar("count", new IntegerType());
		int totalPendingCount = (Integer)query.uniqueResult();
		
		//Pending Count By Status
		query = session.createSQLQuery("select status, count(1) as count from EMAIL_EVENT_DETAILS "
				+ "where insert_date >= sysdate-1 and status in ('Y','T') group by status");
		List<Object> pendingCountByStatus=query.list();
		
		//Pending Count By Server
		query = session.createSQLQuery("select upper(processed_by_system) as server, count(1) as count from EMAIL_EVENT_DETAILS "
				+ "where insert_date >= sysdate-1 and status in ('Y','T') group by upper(processed_by_system)");
		List<Object> pendingCountByServer=query.list();
		
		emailHealthCheckup.setDeliveryErrorCount(deliveryErrorCount);
		emailHealthCheckup.setProcessedBySystem(processedBySystem);
		emailHealthCheckup.setTotalPendingCount(totalPendingCount);
		emailHealthCheckup.setPendingCountByStatus(pendingCountByStatus);
		emailHealthCheckup.setPendingCountByServer(pendingCountByServer);;
		return emailHealthCheckup;
	}

}