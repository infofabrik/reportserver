package net.datenwerke.rs.base.ext.service.reportmanager.eximport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class ReportManagerExporter extends TreeNodeExporter {

   public static final String EXPORTER_ID = "ReportManagerExporter";
   
   public static final String EXPORTER_NAME = "Report-Export";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<? extends AbstractNode<?>> getTreeType() {
      return AbstractReportManagerNode.class;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { Report.class, ReportFolder.class };
   }

   @Override
   public Set<Class<?>> getAllowedReferenceTypes() {
      return new HashSet<>(Arrays.asList(AbstractReportManagerNode.class, AbstractFileServerNode.class,
            AbstractDatasourceManagerNode.class));
   }

}
