package net.datenwerke.gxtdto.client.utilityservices;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

public interface UtilsUIService {

   public void redirect(String url);

   public void redirectWithoutAsking(String url);

   public void redirectInPopup(String url);

   public void triggerDownload(String url);

   public void reloadPage();

   public void reloadPageWithoutAsking();

   Widget asIframe(String html);

   String guessUserTimezone();
   
   void showHtmlPopupWindows(final String title, final String html, final boolean maximize, BaseIcon icon,
         boolean collapsible, boolean titleCollapse, boolean maximizable, boolean resizable);

}
