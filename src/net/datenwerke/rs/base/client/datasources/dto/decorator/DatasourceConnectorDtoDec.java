package net.datenwerke.rs.base.client.datasources.dto.decorator;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.base.client.datasources.config.CsvDatasourceConfigConfigurator;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;

/**
 * Dto Decorator for {@link DatasourceConnectorDto}
 *
 */
public class DatasourceConnectorDtoDec extends DatasourceConnectorDto implements IdedDto {


	private static final long serialVersionUID = 1L;

	public DatasourceConnectorDtoDec() {
		super();
	}

	public DatasourceConnectorConfigDto createConfigObject(){
		return null;
	}
	
	public void addConnectorSpecificFormFields(List<Widget> widgets, DatasourceDefinitionConfigDto config, DatasourceDefinitionDto datasourceDefinitionDto, DatasourceSelectionField datasourceSelectionField, CsvDatasourceConfigConfigurator configConfigurator){
		
	}

	public void inheritConnectorSpecificChanges(
			DatasourceDefinitionConfigDto config,
			DatasourceDefinitionDto datasourceDefinitionDto,
			CsvDatasourceConfigConfigurator csvDatasourceConfigConfigurator) {
		// TODO Auto-generated method stub
		
	}

}
