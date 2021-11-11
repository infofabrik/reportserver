package net.datenwerke.rs.box.client.box.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsBoxFileInformation implements AdditionalScheduleInformation {
   /**
    * 
    */
   private static final long serialVersionUID = 6581874489830243966L;

   private BoxDatasinkDto boxDatasinkDto;
   private String name;
   private String folder;
   private boolean compressed;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public BoxDatasinkDto getBoxDatasinkDto() {
      return boxDatasinkDto;
   }

   public void setBoxDatasinkDto(BoxDatasinkDto boxDatasinkDto) {
      this.boxDatasinkDto = boxDatasinkDto;
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
