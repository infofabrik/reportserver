package net.datenwerke.rs.base.service.parameterreplacements.provider;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;

public class ReportForJuel {
	private String name = "";
	private String description ="";
	private String key = "";
	private Long id = -1l;
	private boolean isVariant = false;
	
	public ReportForJuel(Report report) {
		if(null != report){
			if(null != report.getName())
				this.name = report.getName();
			if(null != report.getDescription())
				this.description = report.getDescription();
			if(null != report.getKey())
				this.key = report.getKey();
			this.id = null == report.getId() ? report.getOldTransientId() : report.getId();
			this.isVariant = report instanceof ReportVariant;
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getKey() {
		return key;
	}
	
	public Long getId(){
		return id;
	}
	
	public Boolean isVariant(){
		return isVariant;
	}
}
