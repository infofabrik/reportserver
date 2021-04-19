package net.datenwerke.rs.ftp.client.ftp.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsSftpFileInformation implements AdditionalScheduleInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2881771133985741640L;

	private SftpDatasinkDto sftpDatasinkDto;
	private String name;
	private String folder;
	
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
	
	public SftpDatasinkDto getSftpDatasinkDto() {
		return sftpDatasinkDto;
	}
	public void setSftpDatasinkDto(SftpDatasinkDto sftpDatasinkDto) {
		this.sftpDatasinkDto = sftpDatasinkDto;
	}
	
}
