package net.datenwerke.rs.base.service.datasources.transformers;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public interface DatasourceTransformationService {

	public <T> T transform(Class<T> resultType, DatasourceContainerProvider containerProvider, ParameterSet parameters);
}
