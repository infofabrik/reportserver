package net.datenwerke.rs.utils.entitymerge.service;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class EntityMergeModule extends AbstractReportServerModule{
   
   @Override
   protected void configure() {
      bind(EntityMergeService.class).to(EntityMergeServiceImpl.class);
      
      bind(EntityMergeStartup.class).asEagerSingleton();
   }
}
