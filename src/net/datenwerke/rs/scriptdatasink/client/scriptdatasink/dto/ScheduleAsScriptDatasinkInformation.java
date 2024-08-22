package net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsScriptDatasinkInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = -9023296728584910685L;

   private ScriptDatasinkDto scriptDatasinkDto;
   private String name;
   private boolean compressed;

   public ScriptDatasinkDto getScriptDatasinkDto() {
      return scriptDatasinkDto;
   }

   public void setScriptDatasinkDto(ScriptDatasinkDto scriptDatasinkDto) {
      this.scriptDatasinkDto = scriptDatasinkDto;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean isCompressed() {
      return compressed;
   }

   public void setCompressed(boolean compressed) {
      this.compressed = compressed;
   }

}