package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl;

import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCSeparator;

public class SFFCSeparatorImpl implements SFFCSeparator {

   private final TYPE type;
   private final String text;
   private final MarginData layoutData;

   public SFFCSeparatorImpl(TYPE type) {
      this(type, null, null);
   }

   public SFFCSeparatorImpl(TYPE type, String text) {
      this(type, text, null);
   }

   public SFFCSeparatorImpl(TYPE type, String text, MarginData layoutData) {
      this.type = type;
      this.text = text;
      this.layoutData = layoutData;
   }

   @Override
   public MarginData getMargins() {
      return layoutData;
   }

   @Override
   public String getText() {
      return text;
   }

   @Override
   public TYPE getType() {
      return type;
   }

}
