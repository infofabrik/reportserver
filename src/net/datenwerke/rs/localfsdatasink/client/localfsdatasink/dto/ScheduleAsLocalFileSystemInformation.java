package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsLocalFileSystemInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = -9023296728584910685L;

   private LocalFileSystemDatasinkDto localFileSystemDatasinkDto;
   private String name;
   private String folder;
   private boolean compressed;

   public LocalFileSystemDatasinkDto getLocalFileSystemDatasinkDto() {
      return localFileSystemDatasinkDto;
   }

   public void setLocalFileSystemDatasinkDto(LocalFileSystemDatasinkDto localFileSystemDatasinkDto) {
      this.localFileSystemDatasinkDto = localFileSystemDatasinkDto;
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