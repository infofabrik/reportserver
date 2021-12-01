package net.datenwerke.rs.core.service.reportmanager;

import java.util.HashMap;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class TemporaryReportMap extends HashMap<String, Report> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7185670158624072363L;

}
