package net.datenwerke.gxtdto.client.model.pa;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import net.datenwerke.gxtdto.client.model.ListStringBaseModel;

public interface ListStringBaseModelPa extends PropertyAccess<ListStringBaseModel> {

   public ValueProvider<ListStringBaseModel, List<String>> value();

}
