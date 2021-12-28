package net.datenwerke.rs.scriptreport.service.scriptreport.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.scriptreport.service.scriptreport.locale.ScriptReportMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

@Entity
@Table(name = "SCRIPT_REPORT")
@Audited
@Indexed
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(dtoPackage = "net.datenwerke.rs.scriptreport.client.scriptreport.dto", createDecorator = true, typeDescriptionMsg = net.datenwerke.rs.scriptreport.client.scriptreport.locale.ScriptReportMessages.class, typeDescriptionKey = "reportTypeName")
@TreeDBAllowedChildren({ ScriptReportVariant.class })
@InstanceDescription(msgLocation = ScriptReportMessages.class, objNameKey = "scriptReportTypeName", icon = "script")
public class ScriptReport extends Report {

   /**
    * 
    */
   private static final long serialVersionUID = 1888604254381316817L;

   @ExposeToClient
   @ManyToOne
   private FileServerFile script;

   @ExposeToClient
   private String arguments;

   @JoinTable(name = "SCRIPT_REPORT_2_EX_FORMAT")
   @ExposeToClient(view = DtoView.LIST)
   @ElementCollection
   @OrderColumn(name = "val_n")
   private List<String> exportFormats = new ArrayList<String>();

   public void setScript(FileServerFile script) {
      this.script = script;
   }

   public FileServerFile getScript() {
      return script;
   }

   public List<String> getExportFormats() {
      return exportFormats;
   }

   public void setExportFormats(List<String> exportFormats) {
      this.exportFormats = exportFormats;
   }

   public void setArguments(String arguments) {
      this.arguments = arguments;
   }

   public String getArguments() {
      return arguments;
   }

   @Override
   protected Report createVariant(Report report) {
      if (!(report instanceof ScriptReport))
         throw new IllegalArgumentException("Expected ScriptReport"); //$NON-NLS-1$

      ScriptReportVariant variant = new ScriptReportVariant();

      /* copy parameter instances */
      initVariant(variant, report);

      return variant;

   }

}
