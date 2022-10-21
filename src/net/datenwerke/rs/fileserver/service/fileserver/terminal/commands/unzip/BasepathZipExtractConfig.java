package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip;

import java.util.zip.ZipEntry;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.utils.zip.ZipExtractionConfig;

public class BasepathZipExtractConfig extends ZipExtractionConfig {

   private final FileServerFolder loc;
   private UnzipCommandHelper unzipHelper;
   private FileServerService fileServerService;

   @Inject
   public BasepathZipExtractConfig(@Assisted FileServerFolder parent, UnzipCommandHelper unzipHelper,
         FileServerService fileServerService) {
      this.loc = parent;
      this.unzipHelper = unzipHelper;
      this.fileServerService = fileServerService;
   }

   @Override
   public boolean isAllowedFile(ZipEntry entry) {
      return true;
   }

   @Override
   public void processContent(ZipEntry entry, byte[] content) {
      if (null == content || content.length == 0 && entry.isDirectory() && "/".equals(entry.getName()))
         return;

      AbstractFileServerNode f = unzipHelper.createFileAndFolders(loc, entry.getName(), entry.isDirectory());
      if (f instanceof FileServerFile) {
         ((FileServerFile) f).setData(content);
         fileServerService.merge(f);
      }
   }

}
