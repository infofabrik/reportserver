package net.datenwerke.rs.emaildatasink.client.emaildatasink.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsEmailDatasinkFileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = -7134123427566869923L;

   private EmailDatasinkDto emailDatasinkDto;
   private String name;
   private String subject;
   private String message;
   private boolean compressed;

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

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

   public boolean isCompressed() {
      return compressed;
   }

   public void setCompressed(boolean compressed) {
      this.compressed = compressed;
   }

}
