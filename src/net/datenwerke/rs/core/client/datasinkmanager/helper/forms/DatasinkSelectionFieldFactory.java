package net.datenwerke.rs.core.client.datasinkmanager.helper.forms;

import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

public interface DatasinkSelectionFieldFactory {

	public DatasinkSelectionField create(
		Container container, 
		UITree datasourceTree,
		Class<? extends DatasinkDefinitionDto>[] types);
}
