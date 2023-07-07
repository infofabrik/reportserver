package net.datenwerke.rs.base.ext.service.dashboardmanager.eximport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardFolder;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class DashboardManagerExporter extends TreeNodeExporter {

   private static final String EXPORTER_ID = "DashboardManagerExporter";
   
   public static final String EXPORTER_NAME = "Dashboard-Export";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<? extends AbstractNode<?>> getTreeType() {
      return AbstractDashboardManagerNode.class;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { DashboardFolder.class, DashboardNode.class, DadgetNode.class };
   }

   @Override
   public Set<Class<?>> getAllowedReferenceTypes() {
      return new HashSet<>(Arrays.asList(AbstractDashboardManagerNode.class, AbstractReportManagerNode.class,
            AbstractFileServerNode.class, AbstractDatasourceManagerNode.class));
   }

}
