package net.datenwerke.rs.fileserver.service.fileserver.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

/**
 * 
 *
 */

@Entity
@Table(name="FILE_SERVER_NODE")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@TreeDBTree(
	rootTypes={FileServerFolder.class,FileServerFile.class},
	manager=FileServerService.class
)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.fileserver.client.fileserver.dto",
	abstractDto=true,
	whitelist={FileServerFileDto.class, FileServerFolderDto.class}
)
abstract public class AbstractFileServerNode extends SecuredAbstractNode<AbstractFileServerNode>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -949128386736839307L;

	public abstract String getName();
	
	@Override
	public String getNodeName() {
		return isRoot() ? getRootNodeName() : null == getName() ? "undefined" : getName();
	}

	@Override
	public String getRootNodeName() {
		return "fileserver";
	}


}
