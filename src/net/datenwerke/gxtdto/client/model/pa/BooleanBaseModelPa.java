package net.datenwerke.gxtdto.client.model.pa;

import net.datenwerke.gxtdto.client.model.BooleanBaseModel;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface BooleanBaseModelPa extends PropertyAccess<BooleanBaseModel> {

	public ValueProvider<BooleanBaseModel,Boolean> value();
	
}
