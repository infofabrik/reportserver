package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCReadOnly;

public class SFFCReadOnlyImpl implements SFFCReadOnly {

   public static SFFCReadOnly TRUE = new SFFCReadOnlyImpl(true);
   public static SFFCReadOnly FALSE = new SFFCReadOnlyImpl(false);

   private boolean readOnly;

   public SFFCReadOnlyImpl(boolean readOnly) {
      this.readOnly = readOnly;
   }

   @Override
   public boolean isReadOnly() {
      return this.readOnly;
   }

}
