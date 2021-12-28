package net.datenwerke.gxtdto.client.overlay;

import com.google.inject.ImplementedBy;
import com.sencha.gxt.core.client.dom.XElement;

@ImplementedBy(OverlayServiceImpl.class)
public interface OverlayService {

   XElement overlay(String text);

   XElement overlay(String name, String text);

   XElement overlay(String name, String text, String top, String left);

   void remove(XElement overlay);

   void remove(String name);

}
