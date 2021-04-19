package net.datenwerke.security.service.treedb.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class MoveNodeEvent extends DwLoggedEvent {

	public MoveNodeEvent(AbstractNode node, AbstractNode newParent) {
		super(
			"node_id", null != node ? node.getId() : "NULL",
			"node_type", null != node ? node.getClass() : "NULL",
			"new_parent_id", null != node ? newParent.getId() : "NULL",
			"new_parent_type", null != node ? newParent.getClass() : "NULL"
		);
	}
	
	public MoveNodeEvent(AbstractNode node, AbstractNode newParent, int index) {
		super(
			"node_id", null != node ? node.getId() : "NULL",
			"node_type", null != node ? node.getClass() : "NULL",
			"new_parent_id", null != node ? newParent.getId() : "NULL",
			"new_parent_type", null != node ? newParent.getClass() : "NULL",
			"index", index
		);
	}

	@Override
	public String getLoggedAction() {
		return "NODE_MOVED";
	}

}
