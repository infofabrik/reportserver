package net.datenwerke.rs.core.client.datasinkmanager;

import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.config.DatasinkDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;

/**
 * 
 *
 */
public interface DatasinkUIService {
	
	public DatasinkDefinitionConfigConfigurator getConfigurator(Class<? extends DatasinkDefinitionDto> configClazz);
	
	public DatasinkSelectionField getSelectionField(
			Container container, 
			Provider<UITree> datasinkTreeProvider,
			Class<? extends DatasinkDefinitionDto>... types
	);
	
	
	public DatasinkSelectionField getSelectionField(
			Container container,
			Provider<UITree> datasinkTreeProvider
	);
	
	public DatasinkSelectionField getSelectionField(
			Container container,
			UITree datasinkTreeProvider
	);
	
}
