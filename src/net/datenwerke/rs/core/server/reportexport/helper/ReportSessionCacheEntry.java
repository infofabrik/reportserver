package net.datenwerke.rs.core.server.reportexport.helper;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public class ReportSessionCacheEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String outputFormat;
	private Report adjustedReport;
	private ReportExecutionConfig[] executorConfigs;
	private Throwable error;
	private BufferedImage image;
	private boolean cached;
	
	public ReportSessionCacheEntry() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public Report getAdjustedReport() {
		return adjustedReport;
	}

	public void setAdjustedReport(Report adjustedReport) {
		this.adjustedReport = adjustedReport;
	}

	public ReportExecutionConfig[] getExecutorConfigs() {
		return executorConfigs;
	}
	
	public void setExecutorConfigs(ReportExecutionConfig[] executorConfigs) {
		this.executorConfigs = executorConfigs;
	}

	public void setError(Throwable error) {
		this.error = error;
	}
	
	public Throwable getError() {
		return error;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public boolean isCached() {
		return cached;
	}
	
	public void setCached(boolean cached) {
		this.cached = cached;
	}
}
