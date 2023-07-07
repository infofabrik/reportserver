package net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TsDiskExporter extends TreeNodeExporter {

   public static final String EXPORTER_ID = "TsDiskExporter";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<? extends AbstractNode<?>> getTreeType() {
      return AbstractTsDiskNode.class;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { AbstractTsDiskNode.class };
   }

   @Override
   public Set<Class<?>> getAllowedReferenceTypes() {
      return new HashSet<>(Arrays.asList(TsDiskGeneralReference.class, AbstractReportManagerNode.class,
            AbstractFileServerNode.class, AbstractDatasourceManagerNode.class));
   }

}
