package net.datenwerke.security.service.treedb.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.datenwerke.security.service.security.action.SecurityActionImpl;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.Right;

public class RemoveNodeAction extends SecurityActionImpl {

	public Collection<Right> getRights() {
		List<Right> list = new ArrayList<Right>();
		list.add(new Delete());
		return list;
	}

}
