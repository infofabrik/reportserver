package net.datenwerke.rs.core.service.reportmanager.entities.reports;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "REPORT_BYTE_PROPERTY")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.core.client.reportmanager.dto.reports")
public class ReportByteProperty extends ReportProperty {

   @Lob
   private byte[] byteValue;

   public ReportByteProperty() {
      super();
   }

   public ReportByteProperty(String name) {
      super(name);
   }

   public byte[] getByteValue() {
      return byteValue;
   }

   public void setByteValue(byte[] byteValue) {
      this.byteValue = byteValue;
   }
}
