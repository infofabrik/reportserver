package net.datenwerke.gxtdto.client.utils;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class IconGuesserService {

   public ImageResource guessIcon(String name) {
      return BaseIcon.fromFileExtension(name).toImageResource();
   }

}
