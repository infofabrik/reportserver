package net.datenwerke.gxtdto.client.dtomanager;

import com.sencha.gxt.core.client.ValueProvider;

public interface HasValueProviderByPath<M> {

	public ValueProvider<M, ?> getValueProviderByPath(String path);
}
