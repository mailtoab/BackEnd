package com.emaildashboard.restapi.model;

import java.util.List;

public class EmailHealthCheckup {
	
	private List<Object> deliveryErrorCount;
	private List<Object> processedBySystem;
	private int totalPendingCount;
	private List<Object> pendingCountByStatus;
	private List<Object> pendingCountByServer;
	public List<Object> getDeliveryErrorCount() {
		return deliveryErrorCount;
	}
	public void setDeliveryErrorCount(List<Object> deliveryErrorCount) {
		this.deliveryErrorCount = deliveryErrorCount;
	}
	public List<Object> getProcessedBySystem() {
		return processedBySystem;
	}
	public void setProcessedBySystem(List<Object> processedBySystem) {
		this.processedBySystem = processedBySystem;
	}
	public int getTotalPendingCount() {
		return totalPendingCount;
	}
	public void setTotalPendingCount(int totalPendingCount) {
		this.totalPendingCount = totalPendingCount;
	}
	public List<Object> getPendingCountByStatus() {
		return pendingCountByStatus;
	}
	public void setPendingCountByStatus(List<Object> pendingCountByStatus) {
		this.pendingCountByStatus = pendingCountByStatus;
	}
	public List<Object> getPendingCountByServer() {
		return pendingCountByServer;
	}
	public void setPendingCountByServer(List<Object> pendingCountByServer) {
		this.pendingCountByServer = pendingCountByServer;
	}
}
