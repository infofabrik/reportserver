package net.datenwerke.rs.core.client.datasinkmanager.config;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerProviderDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;

/**
 * 
 *
 */
public interface DatasinkDefinitionConfigConfigurator {

	Iterable<Widget> getDefaultAdditionalFormfields(DatasinkDefinitionDto datasinkDefinitionDto, DatasinkSelectionField datasinkSelectionField, DatasinkContainerProviderDto datasinkContainerProvider); 
	
	Iterable<Widget> getOptionalAdditionalFormfields(DatasinkDefinitionDto datasinkDefinitionDto, DatasinkSelectionField datasinkSelectionField, DatasinkContainerProviderDto datasinkContainerProvider);
	
	void inheritChanges(DatasinkDefinitionDto datasinkDefinitionDto);
	
	boolean consumes(DatasinkDefinitionDto datasinkDefinitionDto);

	boolean isValid(DatasinkContainerDto datasinkContainer);

	boolean isReloadOnDatasinkChange();
}
