package net.datenwerke.gxtdto.client.utilityservices.form.combo;

import com.sencha.gxt.data.shared.Converter;

public class ConverterWithEmpty<V> implements Converter<V, Object> {
	
	@Override
	public V convertFieldValue(Object object) {
		if(object instanceof EmptyOption)
			return null;
		return (V) object;
	}

	@Override
	public Object convertModelValue(V object) {
		return object;
	}

}
