package net.datenwerke.rs.base.service.reportengines.table.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.envers.Audited;
import org.hibernate.proxy.HibernateProxy;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.IgnoreMergeBackDto;
import net.datenwerke.eximport.ex.annotations.ExImportConfig;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.utils.entitycloner.annotation.ClonePostProcessor;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;

/**
 *
 *
 */
@Entity
@Table(name = "TABLE_REPORT_VARIANT")
@Audited
@Indexed
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.reportengines.table.dto", createDecorator = true)
@ExImportConfig(excludeFields = { "datasourceContainer", "metadataDatasourceContainer", "parameterDefinitions" })
@InstanceDescription(msgLocation = ReportEnginesMessages.class, objNameKey = "tableReportVariantTypeName", icon = "table")
public class TableReportVariant extends TableReport implements ReportVariant {

   /**
    * 
    */
   private static final long serialVersionUID = -1314238195723702665L;

   public TableReport getBaseReport() {
      AbstractReportManagerNode parent = getParent();
      if (parent instanceof HibernateProxy)
         parent = (AbstractReportManagerNode) ((HibernateProxy) parent).getHibernateLazyInitializer()
               .getImplementation();
      return (TableReport) parent;
   }

   public void setBaseReport(Report baseReport) {
      throw new IllegalStateException("Should not be called on Server");
   }

   @IgnoreMergeBackDto
   @Override
   public void setDatasourceContainer(DatasourceContainer datasource) {
      throw new NotImplementedException("not implemented");
   }

   @Override
   public DatasourceContainer getDatasourceContainer() {
      return getBaseReport().getDatasourceContainer();
   }

   @Override
   public DatasourceContainer getMetadataDatasourceContainer() {
      return getBaseReport().getMetadataDatasourceContainer();
   }

   @Override
   public boolean isAllowCubification() {
      return getBaseReport().isAllowCubification();
   }

   @IgnoreMergeBackDto
   @Override
   public void setMetadataDatasourceContainer(DatasourceContainer datasource) {
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
      super.setMetadataDatasourceContainer(null);
      super.setDatasourceContainer(null);
   }

   @Override
   public boolean hasChildren() {
      return false;
   }
}
