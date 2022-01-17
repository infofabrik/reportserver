package net.datenwerke.rs.crystal.service.crystal.entities;

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
import net.datenwerke.rs.crystal.service.crystal.locale.CrystalEngineMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.ClonePostProcessor;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;

@Entity
@Table(name = "CRYSTAL_REPORT_VARIANT")
@Audited
@Indexed
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.crystal.client.crystal.dto", 
      createDecorator = true
      )
@InstanceDescription(
      msgLocation = CrystalEngineMessages.class, 
      objNameKey = "reportVariantTypeName", 
      icon = "diamond"
      )
public class CrystalReportVariant extends CrystalReport implements ReportVariant {

   /**
    * 
    */
   private static final long serialVersionUID = -319938511179945406L;

   public CrystalReport getBaseReport() {
      AbstractReportManagerNode parent = getParent();
      if (parent instanceof HibernateProxy)
         parent = (AbstractReportManagerNode) ((HibernateProxy) parent).getHibernateLazyInitializer()
               .getImplementation();
      return (CrystalReport) parent;
   }

   public void setBaseReport(Report baseReport) {
      throw new IllegalStateException("should not be called on server");
   }

   @Override
   public CrystalReportFile getReportFile() {
      return getBaseReport().getReportFile();
   }

   @Override
   public DatasourceContainer getDatasourceContainer() {
      return getBaseReport().getDatasourceContainer();
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
   }

   @Override
   public boolean hasChildren() {
      return false;
   }
}
