package net.datenwerke.rs.scp.client.scp.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsScpFileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = -8159187693834655263L;

   private ScpDatasinkDto scpDatasinkDto;
   private String name;
   private String folder;
   private boolean compressed;

   public ScpDatasinkDto getScpDatasinkDto() {
      return scpDatasinkDto;
   }

   public void setScpDatasinkDto(ScpDatasinkDto scpDatasinkDto) {
      this.scpDatasinkDto = scpDatasinkDto;
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
