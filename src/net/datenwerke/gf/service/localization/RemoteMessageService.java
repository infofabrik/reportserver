package net.datenwerke.gf.service.localization;

import java.util.Collection;
import java.util.HashMap;

public interface RemoteMessageService {

   public Collection<String> getAvailableLanguages();

   public HashMap<String, HashMap<String, String>> getMessages(String language);

   void addTranslation(String lang, String msgClass, String key, String value, boolean override);

}
