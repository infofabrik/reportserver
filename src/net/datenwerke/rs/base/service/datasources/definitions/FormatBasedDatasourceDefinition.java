package net.datenwerke.rs.base.service.datasources.definitions;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnector;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitymerge.service.annotations.EntityMergeField;

@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.datasources.dto", abstractDto = true)
@MappedSuperclass
public abstract class FormatBasedDatasourceDefinition extends DatasourceDefinition {

   /**
    * 
    */
   private static final long serialVersionUID = -1595308818017167937L;

   @EnclosedEntity
   @OneToOne(cascade = CascadeType.ALL)
   @ExposeToClient(view = DtoView.MINIMAL)
   @Audited
   @EntityMergeField
   private DatasourceConnector connector;

   public void setConnector(DatasourceConnector connector) {
      this.connector = connector;
   }

   public DatasourceConnector getConnector() {
      return connector;
   }

   public InputStream getDataStream(FormatBasedDatasourceConfig dsConfig) throws IOException {
      return getConnector().getDataStream(dsConfig);
   }
}
