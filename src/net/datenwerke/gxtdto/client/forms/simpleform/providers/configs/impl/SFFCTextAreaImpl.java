package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTextArea;

public class SFFCTextAreaImpl implements SFFCTextArea {

   private int height;

   public SFFCTextAreaImpl() {
      this(150);
   }

   public SFFCTextAreaImpl(int height) {
      this.height = height;
   }

   @Override
   public int getWidth() {
      return -1;
   }

   @Override
   public int getHeight() {
      return height;
   }

}
