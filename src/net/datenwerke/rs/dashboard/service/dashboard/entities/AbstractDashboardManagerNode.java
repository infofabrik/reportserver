package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.dashboard.service.dashboard.DashboardManagerService;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

import org.hibernate.envers.Audited;


/**
 * Provides the base class for all user nodes
 * 
 *
 */
@Entity
@Table(name="DASHBOARD_MNGR_NODE")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@TreeDBTree(
	rootTypes=DashboardFolder.class,
	manager=DashboardManagerService.class
)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	abstractDto=true
)
abstract public class AbstractDashboardManagerNode extends SecuredAbstractNode<AbstractDashboardManagerNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8042613328051548683L;

	abstract public String getName();
	abstract public String getDescription();

	@Override
	public String getNodeName() {
		return isRoot() ? getRootNodeName() : null == getName() ? "undefined" : getName();
	}

	@Override
	public String getRootNodeName() {
		return "dashboardlib";
	}
}
