package net.datenwerke.gxtdto.client.utilityservices;

import com.google.gwt.user.client.ui.Widget;

public interface UtilsUIService {

   public void redirect(String url);

   public void redirectWithoutAsking(String url);

   public void redirectInPopup(String url);

   public void triggerDownload(String url);

   public void reloadPage();

   public void reloadPageWithoutAsking();

   Widget asIframe(String html);

   String guessUserTimezone();

}
