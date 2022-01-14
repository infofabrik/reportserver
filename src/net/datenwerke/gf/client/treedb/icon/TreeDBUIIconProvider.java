package net.datenwerke.gf.client.treedb.icon;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.data.shared.IconProvider;

import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class TreeDBUIIconProvider implements IconProvider<AbstractNodeDto> {

   private Map<Class<? extends AbstractNodeDto>, IconMapping> entries = new HashMap<Class<? extends AbstractNodeDto>, IconMapping>();

   public TreeDBUIIconProvider(IconMapping... icons) {
      addMappings(icons);
   }

   public void addMappings(IconMapping... icons) {
      for (IconMapping icon : icons)
         entries.put(icon.getType(), icon);
   }

   public void addMapping(IconMapping mapping) {
      entries.put(mapping.getType(), mapping);
   }

   public ImageResource getIcon(AbstractNodeDto model) {
      Class<?> currentClass = model.getClass();
      while (null != currentClass) {
         if (entries.containsKey(currentClass)) {
            IconMapping mapping = entries.get(currentClass);
            return mapping.getIcon(model);
         }
         currentClass = currentClass.getSuperclass();
      }
      return null;
   }

}
