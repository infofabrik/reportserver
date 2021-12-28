package net.datenwerke.security.ext.client.usermanager;

import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;

import net.datenwerke.gxtdto.client.stores.LoadableListStore;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public interface UserManagerUIService {

	public ListLoader<ListLoadConfig, ListLoadResult<StrippedDownGroup>> getStrippedGroupLoader();
	public ListLoader<ListLoadConfig, ListLoadResult<StrippedDownUser>> getStrippedUserLoader();
	
	BaseIcon getIcon(AbstractUserManagerNodeDto node);

	public LoadableListStore<ListLoadConfig, StrippedDownUser, ListLoadResult<StrippedDownUser>> getStrippedUserStore();
	public LoadableListStore<ListLoadConfig, StrippedDownGroup, ListLoadResult<StrippedDownGroup>> getStrippedGroupStore();
	
}
