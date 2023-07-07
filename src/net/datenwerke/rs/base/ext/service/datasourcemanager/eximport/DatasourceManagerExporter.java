package net.datenwerke.rs.base.ext.service.datasourcemanager.eximport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class DatasourceManagerExporter extends TreeNodeExporter {

   private static final String EXPORTER_ID = "DatasourceManagerExporter";
   
   public static final String EXPORTER_NAME = "Datasource-Export";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<? extends AbstractNode<?>> getTreeType() {
      return AbstractDatasourceManagerNode.class;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { DatasourceFolder.class, DatasourceDefinition.class };
   }

   @Override
   public Set<Class<?>> getAllowedReferenceTypes() {
      return new HashSet<>(Arrays.asList(AbstractDatasourceManagerNode.class, AbstractFileServerNode.class));
   }

}
