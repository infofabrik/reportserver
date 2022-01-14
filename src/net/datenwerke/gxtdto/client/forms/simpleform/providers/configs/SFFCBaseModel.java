package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import java.util.Map;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

public interface SFFCBaseModel<M extends Dto> extends SimpleFormFieldConfiguration {

   public ListStore<M> getAllItemsStore();

   public Map<ValueProvider<M, String>, String> getDisplayProperties();

   public boolean isMultiSelect();
}
