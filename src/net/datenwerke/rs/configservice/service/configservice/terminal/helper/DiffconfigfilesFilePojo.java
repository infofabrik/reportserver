package net.datenwerke.rs.configservice.service.configservice.terminal.helper;

import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;

public class DiffconfigfilesFilePojo {

   private final VFSLocationInfo vfsLocationInfo;
   private final String fullPathName;
   private final String fileName;

   public DiffconfigfilesFilePojo(VFSLocationInfo vfsLocationInfo, String fullPathName, String fileName) {
      this.vfsLocationInfo = vfsLocationInfo;
      this.fullPathName = fullPathName;
      this.fileName = fileName;
   }

   public VFSLocationInfo getVfsLocationInfo() {
      return vfsLocationInfo;
   }

   public String getFullPathName() {
      return fullPathName;
   }

   public String getFileName() {
      return fileName;
   }
}
