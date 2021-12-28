package net.datenwerke.rs.core.service.datasinkmanager.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

/**
 * Provides the base class for all datasink nodes
 * 
 *
 */
@Entity
@Table(name = "DATASINK_MNGR_NODE")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@TreeDBTree(rootTypes = DatasinkFolder.class, manager = DatasinkTreeService.class)
@GenerateDto(dtoPackage = "net.datenwerke.rs.core.client.datasinkmanager.dto", abstractDto = true, whitelist = {
      DatasinkFolderDto.class, DatasinkDefinitionDto.class, AbstractReportManagerNodeDto.class })
abstract public class AbstractDatasinkManagerNode extends SecuredAbstractNode<AbstractDatasinkManagerNode> {

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
      return "datasinks";
   }
}
