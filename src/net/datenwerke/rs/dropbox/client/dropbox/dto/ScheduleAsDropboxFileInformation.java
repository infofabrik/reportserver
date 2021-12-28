package net.datenwerke.rs.dropbox.client.dropbox.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsDropboxFileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = 6581874489830243966L;

   private DropboxDatasinkDto dropboxDatasinkDto;
   private String name;
   private String folder;
   private boolean compressed;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public DropboxDatasinkDto getDropboxDatasinkDto() {
      return dropboxDatasinkDto;
   }

   public void setDropboxDatasinkDto(DropboxDatasinkDto dropboxDatasinkDto) {
      this.dropboxDatasinkDto = dropboxDatasinkDto;
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
