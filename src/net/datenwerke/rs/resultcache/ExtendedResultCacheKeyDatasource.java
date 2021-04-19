package net.datenwerke.rs.resultcache;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public class ExtendedResultCacheKeyDatasource extends ResultCacheKeyDatasource {

	protected ParameterSet parameters;
	protected DatasourceContainerProvider containerProvider;
	private Class<?> transformationDestination;

	public ExtendedResultCacheKeyDatasource(DatasourceDefinition datasource, DatasourceDefinitionConfig config) {
		super(datasource, config);
	}
	public ExtendedResultCacheKeyDatasource(DatasourceDefinition ds) {
		super(ds);
	}
	
	public ExtendedResultCacheKeyDatasource(DatasourceDefinition datasource, DatasourceDefinitionConfig config,
			ParameterSet parameters, DatasourceContainerProvider containerProvider, Class<?> dst) {
		super(datasource, config);
		this.parameters = parameters;
		this.containerProvider = containerProvider;
		this.transformationDestination = dst;
	}
	
	public ParameterSet getParameters() {
		return parameters;
	}
	
	public DatasourceContainerProvider getContainerProvider() {
		return containerProvider;
	}
	
	public DatasourceContainer getContainer(){
		return null != containerProvider ? containerProvider.getDatasourceContainer() : null;
	}
	
	public Class<?> getTransformationDestination() {
		return transformationDestination;
	}
	
}
