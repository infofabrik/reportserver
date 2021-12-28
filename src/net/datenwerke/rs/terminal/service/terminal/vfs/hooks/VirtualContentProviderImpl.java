package net.datenwerke.rs.terminal.service.terminal.vfs.hooks;

import java.util.Date;

import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

public abstract class VirtualContentProviderImpl implements VirtualContentProviderHook {

   protected final SecurityService securityService;

   public VirtualContentProviderImpl(SecurityService securityService) {
      this.securityService = securityService;
   }

   @Override
   public boolean consumes(VFSLocation momentaryLocation) throws VFSException {
      return momentaryLocation.getFilesystemManager() instanceof TreeBasedVirtualFileSystem
            && momentaryLocation.isVirtualLocation()
            && getName().equals(momentaryLocation.getPathHelper().getVirtualLocationName());
   }

   @Override
   public boolean consumes(VFSLocation momentaryLocation, PathHelper path) throws VFSException {
      return momentaryLocation.getFilesystemManager() instanceof TreeBasedVirtualFileSystem
            && !momentaryLocation.isVirtualLocation() && path.isVirtualLocation()
            && getName().equals(path.getVirtualLocationName());
   }

   @Override
   public VFSLocation getLocation(VFSLocation momentaryLocation, PathHelper path) {
      if (!momentaryLocation.isVirtualLocation())
         return momentaryLocation.newVirtualSubLocation(this);
      return momentaryLocation.newSubLocation(path.getLastPathway(), false);
   }

   @Override
   public String prettyPrint(String pathInVirtualSystem) {
      return pathInVirtualSystem;
   }

   @Override
   public VFSLocationInfo getLocationInfo(VFSLocation vfsLocation) {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation)) {
         VFSObjectInfo objectInfo = new VFSObjectInfo(getClass(), "#v-" + getName(), "#v-" + getName(), true);
         VFSLocationInfo info = new VFSLocationInfo(vfsLocation, objectInfo);
         addVirtualChildInfos(info);
         return info;
      } else {
         VFSLocationInfo info = getLocationInfo(baseLocation);
         for (VFSObjectInfo cinfo : info.getChildInfos()) {
            if (vfsLocation.getPathHelper().getLastPathway().equals(cinfo.getId()))
               return new VFSLocationInfo(vfsLocation, cinfo);
         }
      }

      throw new IllegalArgumentException("Could not find info");
   }

   @Override
   public boolean isFolder(VFSLocation vfsLocation) {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      return vfsLocation.equals(baseLocation);
   }

   @Override
   public String translatePathWay(VFSLocation location) {
      return location.getPathHelper().getLastPathway();
   }

   abstract protected void addVirtualChildInfos(VFSLocationInfo info);

   public boolean hasContent(VFSLocation vfsLocation) {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return false;
      return doHasContent(vfsLocation);
   }

   abstract protected boolean doHasContent(VFSLocation vfsLocation);

   public byte[] getContent(VFSLocation vfsLocation) throws VFSException {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return null;

      Object obj = baseLocation.getParentLocation().getObject();
      if (null == obj)
         return null;
      securityService.assertRights(obj, Read.class);

      return doGetContent(vfsLocation);
   }

   abstract protected byte[] doGetContent(VFSLocation vfsLocation) throws VFSException;

   public void setContent(VFSLocation vfsLocation, byte[] content) throws VFSException {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return;

      Object obj = baseLocation.getObject();
      if (null == obj)
         return;
      securityService.assertRights(obj, Read.class, Write.class);

      doSetContent(vfsLocation, content);
   }

   abstract protected void doSetContent(VFSLocation vfsLocation, byte[] content);

   public String getContentType(VFSLocation vfsLocation) {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return null;
      return doGetContentType(vfsLocation);
   }

   abstract protected String doGetContentType(VFSLocation vfsLocation);

   @Override
   public Date getLastModified(VFSLocation vfsLocation) {
      return null;
   }

   @Override
   public boolean exists(VFSLocation vfsLocation) {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return true;
      return doesExist(vfsLocation);
   }

   protected boolean doesExist(VFSLocation vfsLocation) {
      return false;
   }

   @Override
   public long getSize(VFSLocation vfsLocation) {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return 0;
      return doGetSize(vfsLocation);
   }

   protected long doGetSize(VFSLocation vfsLocation) {
      try {
         byte[] content = doGetContent(vfsLocation);
         return content.length;
      } catch (VFSException e) {
         return 0;
      }
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return canWriteIntoBaseLocation();
      return doCanWrite(vfsLocation);
   }

   protected boolean canWriteIntoBaseLocation() {
      return false;
   }

   protected boolean doCanWrite(VFSLocation location) {
      return false;
   }

   @Override
   public void writeIntoLocation(VFSLocation vfsLocation, byte[] uploadData) throws VFSException {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         throw new VFSException("Cannot write to virtual root");
      doWriteIntoLocation(vfsLocation, uploadData);
   }

   protected void doWriteIntoLocation(VFSLocation vfsLocation, byte[] uploadData) {

   }

   @Override
   public boolean isLocationDeletable(VFSLocation vfsLocation) {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return false;
      return doIsLocationDeletable(vfsLocation);
   }

   protected boolean doIsLocationDeletable(VFSLocation vfsLocation) {
      return false;
   }

   @Override
   public void delete(VFSLocation vfsLocation) throws VFSException {

   }

   @Override
   public VFSLocation create(VFSLocation vfsLocation) throws VFSException {
      VFSLocation baseLocation = vfsLocation.getVirtualBaseLocation();
      if (vfsLocation.equals(baseLocation))
         return null;
      return doCreate(vfsLocation);
   }

   @Override
   public Object getObjectFor(VFSLocation vfsLocation) throws VFSException {
      return null;
   }

   protected VFSLocation doCreate(VFSLocation vfsLocation) throws VFSException {
      return null;
   }
}
