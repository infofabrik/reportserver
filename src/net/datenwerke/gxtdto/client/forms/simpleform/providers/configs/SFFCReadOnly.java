package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCReadOnlyImpl;

public interface SFFCReadOnly extends SimpleFormFieldConfiguration {

   public static SFFCReadOnly TRUE = new SFFCReadOnlyImpl(true);
   public static SFFCReadOnly FALSE = new SFFCReadOnlyImpl(false);

   public boolean isReadOnly();
}
