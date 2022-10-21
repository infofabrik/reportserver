package net.datenwerke.rs.tabletemplate.service.tabletemplate.config;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;

@GenerateDto(dtoPackage = "net.datenwerke.rs.tabletemplate.client.tabletemplate.dto")
public class RECTableTemplate implements ReportExecutionConfig {

   /**
    * 
    */
   private static final long serialVersionUID = 2846255412147622569L;

   @ExposeToClient
   private Long templateId;

   @ExposeToClient
   private Long temporaryId;

   @ExposeToClient
   private String templateKey;

   public Long getTemplateId() {
      return templateId;
   }

   public void setTemplateId(Long templateId) {
      this.templateId = templateId;
   }

   public Long getTemporaryId() {
      return temporaryId;
   }

   public void setTemporaryId(Long temporaryId) {
      this.temporaryId = temporaryId;
   }

   public void setTemplateKey(String templateKey) {
      this.templateKey = templateKey;
   }

   public String getTemplateKey() {
      return templateKey;
   }

}
