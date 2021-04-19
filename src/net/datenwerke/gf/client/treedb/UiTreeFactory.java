package net.datenwerke.gf.client.treedb;

import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.sencha.gxt.data.shared.TreeStore;

public interface UiTreeFactory {

	public UITree create(TreeStore<AbstractNodeDto> store);
}
