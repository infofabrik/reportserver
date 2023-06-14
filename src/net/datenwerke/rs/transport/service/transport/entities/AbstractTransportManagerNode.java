package net.datenwerke.rs.transport.service.transport.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportFolderDto;
import net.datenwerke.rs.transport.service.transport.TransportTreeService;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

/**
 * Provides the base class for all transport nodes.
 * 
 * 
 */
@Entity
@Table(name = "TRANSPORTMANAGER_NODE")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@TreeDBTree(rootTypes = TransportFolder.class, manager = TransportTreeService.class)
@GenerateDto(dtoPackage = "net.datenwerke.rs.transport.client.transport.dto", abstractDto = true, whitelist = {
      TransportDto.class, TransportFolderDto.class })
abstract public class AbstractTransportManagerNode extends SecuredAbstractNode<AbstractTransportManagerNode> {

   private static final long serialVersionUID = -4729776009606610343L;

   public abstract String getName();

   @Override
   public String getNodeName() {
      return isRoot() ? getRootNodeName() : null == getName() ? "undefined" : getName();
   }

   @Override
   public String getRootNodeName() {
      return "transportmanager";
   }
}
