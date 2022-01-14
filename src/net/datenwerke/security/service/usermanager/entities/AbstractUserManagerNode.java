package net.datenwerke.security.service.usermanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserManagerServiceImpl;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

/**
 * Provides the base class for all user nodes.
 * 
 * 
 * @see UserManagerServiceImpl
 *
 */
@Entity
@Table(name = "USERMANAGER_NODE")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@TreeDBTree(rootTypes = OrganisationalUnit.class, manager = UserManagerService.class)
@GenerateDto(dtoPackage = "net.datenwerke.security.client.usermanager.dto", abstractDto = true, whitelist = {
      UserDto.class, OrganisationalUnitDto.class, GroupDto.class })
abstract public class AbstractUserManagerNode extends SecuredAbstractNode<AbstractUserManagerNode> {

   private static final long serialVersionUID = -4729776009606610343L;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Column(length = 128)
   @Field
   private String guid;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Column(length = 1024)
   @Field
   private String origin;

   public abstract String getName();

   @Override
   public String getNodeName() {
      return isRoot() ? getRootNodeName() : null == getName() ? "undefined" : getName();
   }

   @Override
   public String getRootNodeName() {
      return "usermanager";
   }

   public String getOrigin() {
      return origin;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public String getGuid() {
      return guid;
   }

   public void setGuid(String guid) {
      this.guid = guid;
   }
}
