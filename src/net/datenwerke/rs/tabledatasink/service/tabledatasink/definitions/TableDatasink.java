package net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.TableDatasinkService;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.locale.TableDatasinkMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitymerge.service.annotations.EntityMergeField;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;

/**
 * Used to define Table datasinks that can be used in ReportServer to send
 * reports to a given table datasink
 */
@Entity
@Table(name = "TABLE_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.tabledatasink.client.tabledatasink.dto",
      dtoImplementInterfaces = DatasourceContainerProviderDto.class, 
      icon = "table"
)
@InstanceDescription(
      msgLocation = TableDatasinkMessages.class, 
      objNameKey = "tableDatasinkTypeName", 
      icon = "table"
      )
@Indexed
public class TableDatasink extends DatasinkDefinition implements DatasourceContainerProvider {

   /**
    * 
    */
   private static final long serialVersionUID = 3831194855989908242L;
   
   @Inject
   protected static Provider<TableDatasinkService> basicDatasinkService;

   @ExposeToClient
   @EnclosedEntity
   @OneToOne(
         cascade = CascadeType.ALL, 
         orphanRemoval = true
         )
   @EntityMergeField(toClone = true)
   private DatasourceContainer datasourceContainer = new DatasourceContainer();
   
   @ExposeToClient
   @Field
   @Column(length = 1024)
   @EntityMergeField
   private String tableName;
   
   @ExposeToClient
   @Field
   @Column(length = 2048)
   @EntityMergeField
   private String primaryKeys;
   
   @ExposeToClient
   @Field
   @EntityMergeField
   private boolean copyPrimaryKeys;
   
   @ExposeToClient
   @Field
   @EntityMergeField
   private boolean truncateTable;
   
   @ExposeToClient
   @Field
   @EntityMergeField
   private int batchSize = 100;
   
   public int getBatchSize() {
      return batchSize;
   }

   public void setBatchSize(int batchSize) {
      this.batchSize = batchSize;
   }

   public String getPrimaryKeys() {
      return primaryKeys;
   }

   public void setPrimaryKeys(String primaryKeys) {
      this.primaryKeys = primaryKeys;
   }

   public boolean isCopyPrimaryKeys() {
      return copyPrimaryKeys;
   }

   public void setCopyPrimaryKeys(boolean copyPrimaryKeys) {
      this.copyPrimaryKeys = copyPrimaryKeys;
   }
   
   public boolean isTruncateTable() {
      return truncateTable;
   }
   public void setTruncateTable(boolean truncateTable) {
      this.truncateTable = truncateTable;
   }

   public DatasourceContainer getDatasourceContainer() {
      return datasourceContainer;
   }

   public void setDatasourceContainer(DatasourceContainer datasourceContainer) {
      this.datasourceContainer = datasourceContainer;
   }
   
   public String getTableName() {
      return tableName;
   }
   
   public void setTableName(String tableName) {
      this.tableName = tableName;
   }

   @Override
   public BasicDatasinkService getDatasinkService() {
      return basicDatasinkService.get();
   }

   @Override
   public DatasinkConfiguration getDefaultConfiguration(String fileEnding) {
      return new DatasinkConfiguration() {
      };
   }

}
