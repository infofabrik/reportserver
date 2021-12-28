package net.datenwerke.rs.core.client.datasinkmanager.helper.forms;

import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public interface DatasinkSelectionFieldFactory {

   public DatasinkSelectionField create(Provider<? extends HasDefaultDatasink> datasinkDaoProvider, BaseIcon icon,
         Container container, UITree datasourceTree, Class<? extends DatasinkDefinitionDto>[] types);
}
