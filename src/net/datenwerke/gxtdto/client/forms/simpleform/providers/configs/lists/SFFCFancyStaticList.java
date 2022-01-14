package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists;

import java.util.Map;

import com.google.gwt.resources.client.ImageResource;

public abstract class SFFCFancyStaticList<M> extends SFFCStaticList<M> {

   abstract public int getHeight();

   abstract public int getWidth();

   abstract public Map<String, ImageResource> getIconMap();

   abstract public Map<String, String> getDescriptionMap();

   @Override
   public net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList.TYPE getType() {
      return TYPE.Dropdown;
   }

   @Override
   public boolean allowTextSelection() {
      return false;
   }
}
