package net.datenwerke.rs.birt.service.reportengine;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.IReportEngine;

import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile;

public interface BirtReportService {

	public void remove(BirtReportFile file);

	public boolean isBirtEnabled();

	IReportEngine getReportEngine() throws BirtException;

	public void shutdown();

	public void clearFontCache();

}
