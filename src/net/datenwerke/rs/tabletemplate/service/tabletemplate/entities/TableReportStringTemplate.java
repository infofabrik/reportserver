package net.datenwerke.rs.tabletemplate.service.tabletemplate.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@Entity
@Table(name = "TABLE_REPORT_STR_TEMPLATE")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.tabletemplate.client.tabletemplate.dto")
public class TableReportStringTemplate extends TableReportTemplate {

   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String template;

   public void setTemplate(String template) {
      this.template = template;
   }

   public String getTemplate() {
      return template;
   }

   @Override
   public void updateData(TableReportTemplate template) {
      if (!(template instanceof TableReportStringTemplate))
         throw new IllegalArgumentException("Expected " + TableReportStringTemplate.class);

      super.updateData(template);

      setTemplate(((TableReportStringTemplate) template).getTemplate());
   }

   @Override
   protected TableReportTemplate doCreateTemporary() {
      TableReportStringTemplate template = new TableReportStringTemplate();
      template.setTemplate(getTemplate());
      return template;
   }

}
