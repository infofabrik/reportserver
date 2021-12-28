package net.datenwerke.rs.onedrive.client.onedrive.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsOneDriveFileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = -9131044045371849092L;

   private OneDriveDatasinkDto oneDriveDatasinkDto;
   private String name;
   private String folder;
   private boolean compressed;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public OneDriveDatasinkDto getOneDriveDatasinkDto() {
      return oneDriveDatasinkDto;
   }

   public void setOneDriveDatasinkDto(OneDriveDatasinkDto oneDriveDatasinkDto) {
      this.oneDriveDatasinkDto = oneDriveDatasinkDto;
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