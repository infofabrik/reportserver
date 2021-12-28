package net.datenwerke.gxtdto.client.utilityservices.ext;

import java.util.HashMap;

public interface HookableContainer {

   void setContainerName(String name);

   HashMap<String, String> getHookConfig();

   void setHookConfig(HashMap<String, String> hookConfig);

   String getContainerName();

}
