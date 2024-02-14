package net.datenwerke.rs.incubator.service.misc.terminal.exportcontentprovider;

import com.google.inject.Inject;

import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualContentProviderImpl;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperServiceImpl;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TerminalExportContentProvider extends VirtualContentProviderImpl {

   public static final String VIRTUAL_NAME = "export";
   public static final String VIRTUAL_FILE_NAME = "export.xml";

   private final TreeNodeExportHelperServiceImpl exportService;

   @Inject
   public TerminalExportContentProvider(TreeNodeExportHelperServiceImpl exportService,
         SecurityService securityService) {
      super(securityService);

      this.exportService = exportService;
   }

   @Override
   public String getName() {
      return VIRTUAL_NAME;
   }

   @Override
   protected void addVirtualChildInfos(VFSLocationInfo info) {
      info.addChildInfo(new VFSObjectInfo(getClass(), VIRTUAL_FILE_NAME, VIRTUAL_FILE_NAME, false));
   }

   @Override
   protected boolean doHasContent(VFSLocation vfsLocation) {
      return true;
   }

   @Override
   protected byte[] doGetContent(VFSLocation vfsLocation) throws VFSException {
      VFSLocation parent = vfsLocation.getVirtualParentLocation();

      AbstractNode node = (AbstractNode) parent.getObject();

      return exportService.export(node, false, "export", false, false).getBytes();
   }

   @Override
   protected void doSetContent(VFSLocation vfsLocation, byte[] content) {
   }

   @Override
   protected String doGetContentType(VFSLocation vfsLocation) {
      return "application/xml";
   }

   @Override
   public boolean enhanceNonVirtual(VFSLocation location) {
      return null != location.getFilesystemManager()
            && location.getFilesystemManager() instanceof TreeBasedVirtualFileSystem;
   }

}
