package com.emaildashboard.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="SSP_FLOW.USER_DETAIL")
public class UserDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VZ_ID", columnDefinition="varchar2(20)")
	private String vzID;
	
	@Column(name="ACTIVATE", columnDefinition="varchar2(1)")
	private String activate="N";	
	
	@Column(name="ADMIN", columnDefinition="varchar2(1)")
	private String admin="N";

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getVzID() {
		return vzID;
	}

	public void setVzID(String vzID) {
		this.vzID = vzID;
	}

	public String getActivate() {
		return activate;
	}

	public void setActivate(String activate) {
		this.activate = activate;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
		
}