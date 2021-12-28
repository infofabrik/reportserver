package net.datenwerke.rs.core.client.datasourcemanager;

import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;

/**
 * 
 *
 */
public interface DatasourceUIService {

   public DatasourceDefinitionConfigConfigurator getConfigurator(Class<? extends DatasourceDefinitionDto> configClazz);

   public DatasourceSelectionField getSelectionField(Container container, boolean displayOptionalAdditionalConfigFields,
         Provider<UITree> datasourceTreeProvider, Class<? extends DatasourceDefinitionDto>... types);

   public DatasourceSelectionField getSelectionField(Container container, Provider<UITree> datasourceTreeProvider);

}
