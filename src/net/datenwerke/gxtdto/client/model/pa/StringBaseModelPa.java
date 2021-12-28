package net.datenwerke.gxtdto.client.model.pa;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import net.datenwerke.gxtdto.client.model.StringBaseModel;

public interface StringBaseModelPa extends PropertyAccess<StringBaseModel> {

   public static final StringBaseModelPa INSTANCE = GWT.create(StringBaseModelPa.class);

   public ValueProvider<StringBaseModel, String> value();

}
