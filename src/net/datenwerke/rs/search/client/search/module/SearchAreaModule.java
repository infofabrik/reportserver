package net.datenwerke.rs.search.client.search.module;

import javax.inject.Provider;

import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.gf.client.homepage.modules.ClientTempModuleImpl;
import net.datenwerke.rs.search.client.search.locale.SearchMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SearchAreaModule extends ClientTempModuleImpl {

   private final Provider<SearchAreaMainWidget> mainWidgetProvider;
   private SearchAreaMainWidget mainWidget;

   @Inject
   public SearchAreaModule(Provider<SearchAreaMainWidget> mainWidgetProvider) {
      this.mainWidgetProvider = mainWidgetProvider;
   }

   @Override
   public String getModuleName() {
      return SearchMessages.INSTANCE.searchAreaModule();
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.SEARCH.toImageResource();
   }

   @Override
   public SearchAreaMainWidget getMainWidget() {
      if (null == mainWidget) {
         mainWidget = mainWidgetProvider.get();
         mainWidget.setModule(this);
      }
      return mainWidget;
   }

   public void addSearchComponent(String search, Component displayComponent) {
      getMainWidget().addSearchComponent(search, displayComponent);
   }

   public void closeCurrent() {
      getMainWidget().close();
   }

   public void removeModule() {
      viewport.removeTempModule(this);
   }

   @Override
   public void onMouseOver(MouseEvent be) {

   }

   @Override
   public boolean isRecyclable() {
      return false;
   }
}
