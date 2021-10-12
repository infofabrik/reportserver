package net.datenwerke.rs.googledrive.client.googledrive.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsGoogleDriveFileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = 6322306376657899165L;

   private GoogleDriveDatasinkDto googleDriveDatasinkDto;
   private String name;
   private String folder;

   public GoogleDriveDatasinkDto getGoogleDriveDatasinkDto() {
      return googleDriveDatasinkDto;
   }

   public void setGoogleDriveDatasinkDto(GoogleDriveDatasinkDto googleDriveDatasinkDto) {
      this.googleDriveDatasinkDto = googleDriveDatasinkDto;
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

}
