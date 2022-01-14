package net.datenwerke.security.client.security.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.security.client.security.GenericTargetIdentifier;

public class GenericTargetProviderHook extends ObjectHook<GenericTargetIdentifier> {

   public GenericTargetProviderHook(GenericTargetIdentifier object) {
      super(object);
   }

}
