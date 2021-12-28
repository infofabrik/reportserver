package net.datenwerke.gf.client.homepage.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface HomepageResources extends ClientBundle {

   public static HomepageResources INSTANCE = GWT.create(HomepageResources.class);

   @Source("img/ReportServerLogo2.png")
   ImageResource logo();
}
