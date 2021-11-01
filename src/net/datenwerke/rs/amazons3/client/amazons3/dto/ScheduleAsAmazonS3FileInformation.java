package net.datenwerke.rs.amazons3.client.amazons3.dto;

import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsAmazonS3FileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = 1976599147497923869L;

   private AmazonS3DatasinkDto amazonS3DatasinkDto;
   private String name;
   private String folder;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public AmazonS3DatasinkDto getAmazonS3DatasinkDto() {
      return amazonS3DatasinkDto;
   }

   public void setAmazonS3DatasinkDto(AmazonS3DatasinkDto amazonS3DatasinkDto) {
      this.amazonS3DatasinkDto = amazonS3DatasinkDto;
   }

   public String getFolder() {
      return folder;
   }

   public void setFolder(String folder) {
      this.folder = folder;
   }

}
