package net.datenwerke.rs.core.service.reportmanager.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

import org.hibernate.envers.Audited;


/**
 * Provides the base class for all user nodes
 * 
 *
 */
@Entity
@Table(name="REPORT_MNGR_NODE")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@TreeDBTree(
	rootTypes=ReportFolder.class,
	manager=ReportService.class
)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.reportmanager.dto.reports",
	abstractDto=true,
	whitelist={
		ReportVariantDto.class,
		ReportDto.class,ReportVariantDto.class,ReportFolderDto.class,
		AbstractDatasourceManagerNodeDto.class
	}
)
abstract public class AbstractReportManagerNode extends SecuredAbstractNode<AbstractReportManagerNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3336712332134719828L;

	abstract public String getName();
	abstract public String getDescription();

	@Override
	public String getNodeName() {
		return isRoot() ? getRootNodeName() : null == getName() ? "undefined" : getName();
	}

	@Override
	public String getRootNodeName() {
		return "reportmanager";
	}
}
