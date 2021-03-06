package net.datenwerke.rs.scriptreport.service.scriptreport.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.envers.Audited;
import org.hibernate.proxy.HibernateProxy;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.IgnoreMergeBackDto;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.scriptreport.service.scriptreport.locale.ScriptReportMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.ClonePostProcessor;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;

@Entity
@Table(name = "SCRIPT_REPORT_VARIANT")
@Audited
@Indexed
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.scriptreport.client.scriptreport.dto", 
      createDecorator = true
      )
@InstanceDescription(
      msgLocation = ScriptReportMessages.class, 
      objNameKey = "reportVariantTypeName", 
      icon = "script"
      )
public class ScriptReportVariant extends ScriptReport implements ReportVariant {

   /**
    * 
    */
   private static final long serialVersionUID = 8689438037587625879L;

   public ScriptReport getBaseReport() {
      AbstractReportManagerNode parent = getParent();
      if (parent instanceof HibernateProxy)
         parent = (AbstractReportManagerNode) ((HibernateProxy) parent).getHibernateLazyInitializer()
               .getImplementation();
      return (ScriptReport) parent;
   }

   public void setBaseReport(Report baseReport) {
      throw new IllegalStateException("should not be called on server");
   }

   @Override
   public DatasourceContainer getDatasourceContainer() {
      return getBaseReport().getDatasourceContainer();
   }

   @IgnoreMergeBackDto
   @Override
   public void setScript(FileServerFile script) {
      throw new NotImplementedException("not implemented");
   }

   @IgnoreMergeBackDto
   @Override
   public void setArguments(String arguments) {
      throw new NotImplementedException("not implemented");
   }

   @IgnoreMergeBackDto
   @Override
   public void setExportFormats(List<String> exportFormats) {
      throw new NotImplementedException("not implemented");
   }

   @Override
   public FileServerFile getScript() {
      return getBaseReport().getScript();
   }

   @Override
   public String getArguments() {
      return getBaseReport().getArguments();
   }

   @Override
   public List<String> getExportFormats() {
      return getBaseReport().getExportFormats();
   }

   @IgnoreMergeBackDto
   @Override
   public void setDatasourceContainer(DatasourceContainer datasource) {
      throw new NotImplementedException("not implemented");
   }

   @Override
   public List<ParameterDefinition> getParameterDefinitions() {
      return getBaseReport().getParameterDefinitions();
   }

   @IgnoreMergeBackDto
   @Override
   public void setParameterDefinitions(List<ParameterDefinition> parameters) {
      throw new NotImplementedException("not implemented");
   }

   /**
    */
   @ClonePostProcessor
   public void guideCloningProcess(Object report) {
      super.setParameterDefinitions(null);
      super.setDatasourceContainer(null);
      super.setScript(null);
      super.setArguments(null);
      super.setExportFormats(null);
   }

   @Override
   public boolean hasChildren() {
      return false;
   }
}
