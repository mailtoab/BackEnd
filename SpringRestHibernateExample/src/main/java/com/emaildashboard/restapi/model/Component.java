package com.emaildashboard.restapi.model;

import java.io.Serializable;
import java.sql.Clob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="SSP_FLOW.COMPONENT")
public class Component implements Serializable{
	
	private static final long serialVersionUID = 1L;	

	@Id @GeneratedValue
	@Column(name="COMPONENT_ID")
	private int componentID;
	
	@Column(name="EMAIL_STATUS", columnDefinition="varchar2(1)")
	private String emailStatus="N";
	
	@Column(name="RULE_TEST", columnDefinition="varchar2(1)")
	private String ruleTest="N";
	
	@Column(name="SEARCH", columnDefinition="varchar2(1)")
	private String search="N";	

	@Column(name="SERVER_STATUS", columnDefinition="varchar2(1)")
	private String serverStatus="N";
	
	@Column(name="DYNAMIC_SETTING", columnDefinition="varchar2(1)")
	private String dynamicSetting="N";	

	@Column(name="USER_ACCESS", columnDefinition="varchar2(1)")
	private String access="N";

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="VZ_ID")
	private UserDetail userDetail;

	public int getComponentID() {
		return componentID;
	}

	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getRuleTest() {
		return ruleTest;
	}

	public void setRuleTest(String ruleTest) {
		this.ruleTest = ruleTest;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}

	public String getDynamicSetting() {
		return dynamicSetting;
	}

	public void setDynamicSetting(String dynamicSetting) {
		this.dynamicSetting = dynamicSetting;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}