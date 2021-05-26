package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkDao;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public interface SFFCDatasinkDao extends SimpleFormFieldConfiguration {
   Provider<? extends DatasinkDao> getDatasinkDaoProvider();
   BaseIcon getIcon();
}
