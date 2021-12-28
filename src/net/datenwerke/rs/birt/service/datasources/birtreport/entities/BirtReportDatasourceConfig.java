package net.datenwerke.rs.birt.service.datasources.birtreport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;

@Entity
@Table(name = "BIRT_REPORT_DATASRC_CFG")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.birt.client.datasources.dto")
public class BirtReportDatasourceConfig extends DatasourceDefinitionConfig {

   private static final long serialVersionUID = 206876596722300331L;

   @ExposeToClient
   @ManyToOne
   private BirtReport report;

   /**
    * The name of the dataset / parameter the data is pulled from
    */
   @ExposeToClient
   private String target;

   @ExposeToClient
   private BirtReportDatasourceTargetType targetType = BirtReportDatasourceTargetType.DATASET;

   @ExposeToClient(disableHtmlEncode = true)
   @Column(length = 4096)
   private String queryWrapper;

   public String getQueryWrapper() {
      return queryWrapper;
   }

   public void setQueryWrapper(String queryWrapper) {
      this.queryWrapper = queryWrapper;
   }

   public BirtReport getReport() {
      return report;
   }

   public void setReport(BirtReport report) {
      this.report = report;
   }

   public String getTarget() {
      return target;
   }

   public void setTarget(String target) {
      this.target = target;
   }

   public BirtReportDatasourceTargetType getTargetType() {
      return targetType;
   }

   public void setTargetType(BirtReportDatasourceTargetType targetType) {
      this.targetType = targetType;
   }

   @Override
   public boolean contentEquals(DatasourceDefinition definition, DatasourceDefinitionConfig config) {
      if (!(config instanceof BirtReportDatasourceConfig))
         return false;
      if (!(definition instanceof BirtReportDatasourceDefinition))
         return false;

      BirtReportDatasourceConfig otherConfig = (BirtReportDatasourceConfig) config;

      if (null != target && !target.equals(otherConfig.target))
         return false;
      if (null == target && null != otherConfig.target)
         return false;

      if (null != targetType && !targetType.equals(otherConfig.targetType))
         return false;
      if (null == targetType && null != otherConfig.targetType)
         return false;

      if (null != queryWrapper && !queryWrapper.equals(otherConfig.queryWrapper))
         return false;
      if (null == queryWrapper && null != otherConfig.queryWrapper)
         return false;

      if (null != report && !report.idsMatch(otherConfig.report))
         return false;

      return super.contentEquals(definition, config);
   }
}
