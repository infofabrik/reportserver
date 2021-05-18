package net.datenwerke.rs.emaildatasink.client.emaildatasink.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsEmailDatasinkFileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = -7134123427566869923L;

   private EmailDatasinkDto emailDatasinkDto;
   private String name;

   public EmailDatasinkDto getEmailDatasinkDto() {
      return emailDatasinkDto;
   }

   public void setEmailDatasinkDto(EmailDatasinkDto emailDatasinkDto) {
      this.emailDatasinkDto = emailDatasinkDto;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

}
