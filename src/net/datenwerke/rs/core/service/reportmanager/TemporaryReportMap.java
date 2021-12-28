package net.datenwerke.rs.core.service.reportmanager;

import java.util.HashMap;

import com.google.inject.servlet.SessionScoped;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

@SessionScoped
public class TemporaryReportMap extends HashMap<String, Report> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7185670158624072363L;

}
