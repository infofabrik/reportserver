package net.datenwerke.gxtdto.client.utils.valueprovider;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

import com.sencha.gxt.core.client.ValueProvider;

public class DisplayTitleValueProvider<T extends Dto> implements ValueProvider<T, String> {

	@Override
	public String getValue(T object) {
		return null != object ? object.toDisplayTitle() : "";
	}

	@Override
	public void setValue(T object, String value) {
	}

	@Override
	public String getPath() {
		return "__displayTitle";
	}

}
