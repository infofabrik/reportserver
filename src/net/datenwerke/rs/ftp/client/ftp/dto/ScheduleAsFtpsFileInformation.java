package net.datenwerke.rs.ftp.client.ftp.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsFtpsFileInformation implements AdditionalScheduleInformation {

    /**
     * 
     */
    private static final long serialVersionUID = 6178278605851440500L;

    private FtpsDatasinkDto ftpsDatasinkDto;
    private String name;
    private String folder;
    private boolean compressed;

    public FtpsDatasinkDto getFtpsDatasinkDto() {
        return ftpsDatasinkDto;
    }

    public void setFtpsDatasinkDto(FtpsDatasinkDto ftpsDatasinkDto) {
        this.ftpsDatasinkDto = ftpsDatasinkDto;
    }

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
    
    public boolean isCompressed() {
        return compressed;
    }
   
    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

}