package net.datenwerke.rs.core.client.reportexecutor.ui;


import java.util.ArrayList;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public abstract class ReportExecutorMainPanelView {

	private String executeReportToken;

	public abstract String getViewId();
	
	public boolean wantsToBeDefault(){
		return false;
	}
	
	public abstract String getComponentHeader();
	
	public abstract Widget getViewComponent();
	
	public ImageResource getIcon(){
		return null;
	}

	public void setExecuteReportToken(String executeReportToken) {
		this.executeReportToken = executeReportToken;
	}
	
	public String getExecuteReportToken(){
		return executeReportToken;
	}

	public boolean allowsDropOf(Object m) {
		return false;
	}

	public void objectDropped(Object m) {
	
	}

	public List<String> validateView() {
		return new ArrayList<String>();
	}

	public void cleanup() {
		
	}

}
