package net.datenwerke.rs.core.client.datasourcemanager.config;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;

/**
 * 
 *
 */
public interface DatasourceDefinitionConfigConfigurator {

   Iterable<Widget> getDefaultAdditionalFormfields(DatasourceDefinitionConfigDto config,
         DatasourceDefinitionDto datasourceDefinitionDto, DatasourceSelectionField datasourceSelectionField,
         DatasourceContainerProviderDto datasourceContainerProvider);

   Iterable<Widget> getOptionalAdditionalFormfields(DatasourceDefinitionConfigDto config,
         DatasourceDefinitionDto datasourceDefinitionDto, DatasourceSelectionField datasourceSelectionField,
         DatasourceContainerProviderDto datasourceContainerProvider);

   void inheritChanges(DatasourceDefinitionConfigDto config, DatasourceDefinitionDto datasourceDefinitionDto);

   DatasourceDefinitionConfigDto createConfigObject(DatasourceDefinitionDto datasourceDefinitionDto,
         DatasourceContainerProviderDto datasourceContainerProvider);

   boolean consumes(DatasourceDefinitionDto datasourceDefinitionDto, DatasourceDefinitionConfigDto datasourceConfig);

   boolean isValid(DatasourceContainerDto datasourceContainer);

   boolean isReloadOnDatasourceChange();
}
