package net.datenwerke.rs.core.client.datasourcemanager.dto;

import net.datenwerke.gxtdto.client.model.DwModel;

public class DatasourceContainerProviderDtoImpl implements DatasourceContainerProviderDto, DwModel {

   /**
    * 
    */
   private static final long serialVersionUID = 5022160534241648466L;

   private DatasourceContainerDto datasourceContainer;

   public DatasourceContainerDto getDatasourceContainer() {
      return datasourceContainer;
   }

   public void setDatasourceContainer(DatasourceContainerDto datasourceContainer) {
      this.datasourceContainer = datasourceContainer;
   }

}
