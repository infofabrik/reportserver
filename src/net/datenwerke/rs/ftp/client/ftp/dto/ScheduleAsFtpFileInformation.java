package net.datenwerke.rs.ftp.client.ftp.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsFtpFileInformation implements AdditionalScheduleInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2881771133985741640L;

	private FtpDatasinkDto ftpDatasinkDto;
	private String name;
	private String folder;
	private boolean compressed;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public FtpDatasinkDto getFtpDatasinkDto() {
		return ftpDatasinkDto;
	}
	public void setFtpDatasinkDto(FtpDatasinkDto ftpDatasinkDto) {
		this.ftpDatasinkDto = ftpDatasinkDto;
	}
	
	public boolean isCompressed() {
	    return compressed;
	}
	  
	public void setCompressed(boolean compressed) {
	    this.compressed = compressed;
	}
	
}
