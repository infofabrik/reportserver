package net.datenwerke.gxtdto.client.model.pa;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import net.datenwerke.gxtdto.client.model.BooleanBaseModel;

public interface BooleanBaseModelPa extends PropertyAccess<BooleanBaseModel> {

   public ValueProvider<BooleanBaseModel, Boolean> value();

}
