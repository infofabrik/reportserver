package net.datenwerke.rs.base.ext.service.reportmanager.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;

public class ReportManagerVFS extends TreeBasedVirtualFileSystem<AbstractReportManagerNode> {

   /**
    * 
    */
   private static final long serialVersionUID = -7186418223163754943L;

   public static final String FILESYSTEM_NAME = "reportmanager";

   @Inject
   public ReportManagerVFS(Provider<ReportService> reportServiceProvider) {
      super(reportServiceProvider);
   }

   @Override
   public String getFileSystemName() {
      return FILESYSTEM_NAME;
   }

   @Override
   protected boolean doIsFolder(AbstractReportManagerNode node) {
      return node instanceof ReportFolder;
   }

   @Override
   protected String doGetNodeName(AbstractReportManagerNode node) {
      if (node instanceof ReportFolder)
         return ((ReportFolder) node).getName();
      else
         return ((Report) node).getName();
   }

   @Override
   protected void doRename(AbstractReportManagerNode node, String name) {
      if (node instanceof ReportFolder)
         ((ReportFolder) node).setName(name);
      else
         ((Report) node).setName(name);
   }

   @Override
   protected AbstractReportManagerNode instantiateFolder(String folder) {
      return new ReportFolder(folder);
   }

   @Override
   protected boolean isFolder(AbstractReportManagerNode node) {
      return node instanceof ReportFolder;
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      if (!vfsLocation.exists()) {
         VFSLocation parentLoc = vfsLocation.getParentLocation();
         if (!parentLoc.exists())
            return false;

         AbstractReportManagerNode parent = getNodeByLocation(parentLoc);
         if (!(parent instanceof ReportFolder))
            return false;

         return canWrite(parent);
      } else {
         return false;
      }
   }

}
