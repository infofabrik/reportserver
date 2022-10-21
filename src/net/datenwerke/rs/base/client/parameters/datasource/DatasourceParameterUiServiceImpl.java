package net.datenwerke.rs.base.client.parameters.datasource;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.rs.enterprise.client.EnterpriseUiService;

@Singleton
public class DatasourceParameterUiServiceImpl implements DatasourceParameterUiService {

   private final EnterpriseUiService enterpriseService;

   private boolean allowPostProcessing;

   @Inject
   public DatasourceParameterUiServiceImpl(EnterpriseUiService enterpriseService) {
      this.enterpriseService = enterpriseService;
   }

   @Override
   public void setAllowPostProcessing(boolean b) {
      this.allowPostProcessing = b;
   }

   @Override
   public boolean isAllowPostProcessing() {
      return allowPostProcessing && enterpriseService.isEnterprise();
   }

}
