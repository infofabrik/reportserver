package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

public interface SFFCSeparator extends SimpleFormFieldConfiguration {

   public enum TYPE {
      LINE, TEXT, H_LARGE, H_MEDIUM, H_SMALL
   }

   public TYPE getType();

   public MarginData getMargins();

   public String getText();

}
