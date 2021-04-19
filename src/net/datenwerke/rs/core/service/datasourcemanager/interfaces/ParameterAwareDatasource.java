package net.datenwerke.rs.core.service.datasourcemanager.interfaces;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;

public interface ParameterAwareDatasource {

	public boolean usesParameter(DatasourceDefinitionConfig config, String key);
}
