package net.datenwerke.rs.printer.service.printer.definitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.printer.service.printer.PrinterService;
import net.datenwerke.rs.printer.service.printer.locale.PrinterDatasinkMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;

@Entity
@Table(name = "PRINTER_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.printer.client.printer.dto", 
      icon = "print"
      )
@InstanceDescription(
      msgLocation = PrinterDatasinkMessages.class, 
      objNameKey = "printerDatasinkTypeName", 
      icon = "print"
      )
@Indexed
public class PrinterDatasink extends DatasinkDefinition {
   /**
    * 
    */
   private static final long serialVersionUID = 8316420701145091123L;

   @Inject
   protected static Provider<PrinterService> basicDatasinkService;

   @ExposeToClient
   @Field
   @Column(length = 2048)
   private String printerName;

   public String getPrinterName() {
      return printerName;
   }

   public void setPrinterName(String printerName) {
      this.printerName = printerName;
   }

   @Override
   public BasicDatasinkService getDatasinkService() {
      return basicDatasinkService.get();
   }

   @Override
   public DatasinkConfiguration getDefaultConfiguration(String fileEnding) {
      return new DatasinkConfiguration() {};
   }

}
