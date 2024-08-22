package net.datenwerke.rs.saiku.client.saiku;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SaikuUiService {

   void getSettings(AsyncCallback<HashMap<String, String>> callback);

}
