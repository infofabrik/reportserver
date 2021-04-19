package net.datenwerke.security.service.treedb.actions;

import java.util.Arrays;
import java.util.Collection;

import net.datenwerke.security.service.security.action.SecurityActionImpl;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.security.rights.Write;

public class MoveNodeActionForNode extends SecurityActionImpl {

	public Collection<Right> getRights() {
		return Arrays.asList(new Read(), new Write(), new Delete());
	}

}
