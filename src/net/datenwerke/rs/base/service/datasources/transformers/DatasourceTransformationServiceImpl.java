package net.datenwerke.rs.base.service.datasources.transformers;

import javax.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public class DatasourceTransformationServiceImpl implements DatasourceTransformationService {

   private HookHandlerService hookHandler;

   @Inject
   public DatasourceTransformationServiceImpl(HookHandlerService hookHandler) {

      this.hookHandler = hookHandler;
   }

   @Override
   public <T> T transform(Class<T> resultType, DatasourceContainerProvider containerProvider, ParameterSet parameters) {
      /* return null if no database is defined */
      if (null == containerProvider || null == containerProvider.getDatasourceContainer()
            || null == containerProvider.getDatasourceContainer().getDatasource())
         return null;

      for (DataSourceDefinitionTransformer tfmr : hookHandler.getHookers(DataSourceDefinitionTransformer.class)) {
         if (tfmr.consumes(containerProvider, resultType)) {
            return (T) tfmr.transform(containerProvider, resultType, parameters);
         }
      }

      String from = "null";

      if (null != containerProvider && null != containerProvider.getDatasourceContainer()
            && null != containerProvider.getDatasourceContainer().getDatasource()) {
         from = containerProvider.getDatasourceContainer().getDatasource().getClass().getName();
      }
      String to = resultType instanceof Class ? resultType.getName() : resultType.getClass().getName();
      throw new RuntimeException("No DataSourceDefinitionTransformer for " + from + " -> " + to);

   }

}
