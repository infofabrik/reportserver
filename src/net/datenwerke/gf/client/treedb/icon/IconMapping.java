package net.datenwerke.gf.client.treedb.icon;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class IconMapping {

   final private Class<? extends AbstractNodeDto> type;
   private ImageResource icon;

   public IconMapping(Class<? extends AbstractNodeDto> type) {
      this.type = type;
   }

   public IconMapping(Class<? extends AbstractNodeDto> type, ImageResource icon) {
      this(type);
      this.icon = icon;
   }

   public IconMapping(Class<? extends AbstractNodeDto> type, BaseIcon icon) {
      this(type, icon.toImageResource());
   }

   public Class<? extends AbstractNodeDto> getType() {
      return type;
   }

   public ImageResource getIcon(AbstractNodeDto node) {
      return icon;
   }

}
