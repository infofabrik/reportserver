package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.AbstractTsDiskNode2DtoPost;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

/**
 * 
 *
 */

@Entity
@Table(name = "TS_DISK_NODE")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@TreeDBTree(rootTypes = TsDiskRoot.class, manager = TsDiskService.class, multipleRoots = true)
@GenerateDto(dtoPackage = "net.datenwerke.rs.tsreportarea.client.tsreportarea.dto", abstractDto = true, createDecorator = true, poso2DtoPostProcessors = AbstractTsDiskNode2DtoPost.class, whitelist = RsDto.class, additionalFields = {
      @AdditionalField(name = "teamSpaceId", type = Long.class) })
abstract public class AbstractTsDiskNode extends AbstractNode<AbstractTsDiskNode> {

   /**
    * 
    */
   private static final long serialVersionUID = 889807740883444464L;

   public abstract String getName();

   public abstract String getDescription();

   public abstract void setName(String name);

   public abstract void setDescription(String name);

   @Override
   public String getNodeName() {
      return isRoot() ? getRootNodeName() : null == getName() ? "undefined" : getName();
   }

   @Override
   public String getRootNodeName() {
      return "tsreport";
   }
}
