package net.datenwerke.rs.tabledatasink.client.tabledatasink.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsTableDatasinkFileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = 6382892081570316797L;
   /**
    * 
    */

   private TableDatasinkDto tableDatasinkDto;
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public TableDatasinkDto getTableDatasinkDto() {
      return tableDatasinkDto;
   }

   public void setTableDatasinkDto(TableDatasinkDto tableDatasinkDto) {
      this.tableDatasinkDto = tableDatasinkDto;
   }

}
