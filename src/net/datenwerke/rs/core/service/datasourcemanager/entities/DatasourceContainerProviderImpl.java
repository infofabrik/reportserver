package net.datenwerke.rs.core.service.datasourcemanager.entities;

public class DatasourceContainerProviderImpl implements
		DatasourceContainerProvider {

	private DatasourceContainer datasourceContainer;
	
	public DatasourceContainerProviderImpl(
			DatasourceContainer datasourceContainer) {
		super();
		this.datasourceContainer = datasourceContainer;
	}

	@Override
	public DatasourceContainer getDatasourceContainer() {
		return datasourceContainer;
	}

	@Override
	public void setDatasourceContainer(DatasourceContainer datasourceContainer) {
		this.datasourceContainer = datasourceContainer;
	}

}
