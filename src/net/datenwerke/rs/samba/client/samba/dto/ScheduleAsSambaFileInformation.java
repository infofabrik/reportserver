package net.datenwerke.rs.samba.client.samba.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsSambaFileInformation implements AdditionalScheduleInformation {

    /**
     * 
     */
    private static final long serialVersionUID = 2881771133985741640L;

    private SambaDatasinkDto sambaDatasinkDto;
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
    public SambaDatasinkDto getSambaDatasinkDto() {
        return sambaDatasinkDto;
    }
    public void setSambaDatasinkDto(SambaDatasinkDto sambaDatasinkDto) {
        this.sambaDatasinkDto = sambaDatasinkDto;
    }
    
    public boolean isCompressed() {
       return compressed;
    }

    public void setCompressed(boolean compressed) {
       this.compressed = compressed;
    }
    
}