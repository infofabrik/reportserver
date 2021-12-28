package net.datenwerke.rs.fileserver.client.fileserver.provider.helper;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gf.client.treedb.icon.IconMapping;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 * <p>
 * Needs static injection
 * </p>
 *
 */
public class FileIconMapping extends IconMapping {

   public FileIconMapping() {
      super(FileServerFileDto.class);
   }

   @Override
   public ImageResource getIcon(AbstractNodeDto node) {
      if (!(node instanceof FileServerFileDto))
         throw new IllegalArgumentException("Expected FileServerFileDto"); //$NON-NLS-1$
      FileServerFileDto file = (FileServerFileDto) node;
      String n = file.getName();
      if (null == n || n.indexOf(".") < 0)
         return BaseIcon.FILE.toImageResource();

      String ext = n.substring(n.lastIndexOf("."));
      if (ext.length() > 1)
         ext = ext.substring(1);

      if ("groovy".equals(ext) || "rs".equals(ext))
         return BaseIcon.SCRIPT.toImageResource();
      if ("cf".equals(ext))
         return BaseIcon.COG.toImageResource();

      return BaseIcon.fromFileExtension(ext).toImageResource();
   }
}
