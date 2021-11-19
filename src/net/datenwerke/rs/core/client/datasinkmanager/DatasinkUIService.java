package net.datenwerke.rs.core.client.datasinkmanager;

import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.config.DatasinkDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public interface DatasinkUIService {

   public DatasinkDefinitionConfigConfigurator getConfigurator(Class<? extends DatasinkDefinitionDto> configClazz);

   public DatasinkSelectionField getSelectionField(Provider<? extends HasDefaultDatasink> datasinkDaoProvider, BaseIcon defaultDatasinkIcon,
         Container container, Provider<UITree> datasinkTreeProvider, Class<? extends DatasinkDefinitionDto>... types);

   public DatasinkSelectionField getSelectionField(Provider<? extends HasDefaultDatasink> datasinkDaoProvider, BaseIcon defaultDatasinkIcon,
         Container container, Provider<UITree> datasinkTreeProvider);

   public DatasinkSelectionField getSelectionField(Provider<? extends HasDefaultDatasink> datasinkDaoProvider, BaseIcon defaultDatasinkIcon,
         Container container, UITree datasinkTreeProvider);

}
