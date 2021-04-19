package net.datenwerke.rs.core.service.datasourcemanager.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

import org.hibernate.envers.Audited;

/**
 * Provides the base class for all datasource nodes
 * 
 *
 */
@Entity
@Table(name="DATASOURCE_MNGR_NODE")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@TreeDBTree(
	rootTypes=DatasourceFolder.class,
	manager=DatasourceService.class
)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.datasourcemanager.dto",
	abstractDto=true,
	whitelist={
		DatasourceFolderDto.class,DatasourceDefinitionDto.class,DatasourceDefinitionConfigDto.class,
		AbstractReportManagerNodeDto.class
	}
)
abstract public class AbstractDatasourceManagerNode extends SecuredAbstractNode<AbstractDatasourceManagerNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1307633830087406644L;

	public abstract String getName();
	
	@Override
	public String getNodeName() {
		return isRoot() ? getRootNodeName() : null == getName() ? "undefined" : getName();
	}

	@Override
	public String getRootNodeName() {
		return "datasources";
	}
}
