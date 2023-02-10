package net.datenwerke.treedb.ext.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.base.ext.service.RsBaseExtStartup;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperServiceImpl;
import net.datenwerke.treedb.ext.service.eximport.http.HttpTreeImportModule;

public class TreeDbExtModule extends AbstractModule {

   @Override
   protected void configure() {
      install(new HttpTreeImportModule());
      
      bind(TreeNodeExportHelperService.class).to(TreeNodeExportHelperServiceImpl.class);
      
      bind(RsBaseExtStartup.class).asEagerSingleton();
   }

}
