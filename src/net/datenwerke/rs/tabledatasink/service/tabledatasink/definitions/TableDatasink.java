package net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.locale.TableDatasinkMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;

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
   protected static Provider<PbeService> pbeServiceProvider;

   @ExposeToClient
   @EnclosedEntity
   @OneToOne(
         cascade = CascadeType.ALL, 
         orphanRemoval = true
         )
   private DatasourceContainer datasourceContainer = new DatasourceContainer();
   
   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String tableName;
   
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

}
