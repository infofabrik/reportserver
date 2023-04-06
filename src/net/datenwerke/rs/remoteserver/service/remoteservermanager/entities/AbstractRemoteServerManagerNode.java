package net.datenwerke.rs.remoteserver.service.remoteservermanager.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

/**
 * Provides the base class for all remoteserver nodes
 * 
 *
 */
@Entity
@Table(name = "REMOTE_SERVER_MNGR_NODE")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@TreeDBTree(rootTypes = RemoteServerFolder.class, manager = RemoteServerTreeService.class)
@GenerateDto(dtoPackage = "net.datenwerke.rs.remoteserver.client.remoteservermanager.dto", abstractDto = true, whitelist = {
      RemoteServerFolderDto.class, AbstractReportManagerNodeDto.class })
abstract public class AbstractRemoteServerManagerNode extends SecuredAbstractNode<AbstractRemoteServerManagerNode> {

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
      return "remoteservers";
   }
}
