package net.datenwerke.rs.dsbundle.client.dsbundle;

import java.util.List;

public interface DatasourceBundleUiService {

	public void setAvailableBundleKeys(List<String> result);
	
	public List<String> getAvailableBundleKeys();
}
