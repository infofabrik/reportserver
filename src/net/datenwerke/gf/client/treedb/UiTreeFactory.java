package net.datenwerke.gf.client.treedb;

import com.sencha.gxt.data.shared.TreeStore;

import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface UiTreeFactory {

	public UITree create(TreeStore<AbstractNodeDto> store);
}
