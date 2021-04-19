package net.datenwerke.rs.dsbundle.service.dsbundle;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;

public class DummyDatasourceBundleServiceImpl implements SimpleDatasourceBundleService {

	@Override
	public List<String> getAvailableBundleKeys() {
		return null;
	}

	@Override
	public Set<DatasourceDefinition> getAllDatasources(DatasourceContainerProvider container, List<String> keys) {
		return Stream.of(container.getDatasourceContainer().getDatasource()).collect(Collectors.toSet());
	}
	
}
