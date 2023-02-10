package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport;

import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class DatasinkManagerExporter extends TreeNodeExporter {

   private static final String EXPORTER_ID = "DatasinkManagerExporter";
   
   public static final String EXPORTER_NAME = "Datasink-Export";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<? extends AbstractNode<?>> getTreeType() {
      return AbstractDatasinkManagerNode.class;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { DatasinkFolder.class, DatasinkDefinition.class };
   }

}