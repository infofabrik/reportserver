package net.datenwerke.gf.client.homepage;

import java.util.Collection;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.homepage.modules.ClientMainModule;
import net.datenwerke.gf.client.homepage.modules.ClientTempModule;
import net.datenwerke.gf.client.homepage.ui.DwMainViewport;

/**
 * This implementation of {@link DwMainViewportUiService} configure the
 * ReportServer default screens setup and handles events raised by components on
 * these screen parts.
 * 
 *
 */
@Singleton
public class DwMainViewportUiServiceImpl implements DwMainViewportUiService {

   private final Provider<DwMainViewport> viewportProvider;
   private DwMainViewport viewport;

   @Inject
   public DwMainViewportUiServiceImpl(Provider<DwMainViewport> DwMainViewport) {

      this.viewportProvider = DwMainViewport;
   }

   /**
    * Returns the main widget and sets some server calls in motion.
    */
   @Override
   public Widget getWidget() {
      if (null == viewport)
         viewport = viewportProvider.get();
      return viewport;
   }

   @Override
   public void addTempModule(ClientTempModule module) {
      viewport.addTempModule(module);
   }

   @Override
   public void removeTempModule(ClientTempModule module) {
      viewport.removeTempModule(module);
   }

   @Override
   public Collection<ClientTempModule> getTempModules() {
      if (null == viewport)
         viewport = viewportProvider.get();
      return viewport.getTempModules();
   }

   @Override
   public void showModule(ClientMainModule module) {
      viewport.showModule(module);
   }

   @Override
   public void setLoadingMask() {
      viewport.setLoadingMask();
   }

   @Override
   public void unmask() {
      viewport.unmask();
   }

}
