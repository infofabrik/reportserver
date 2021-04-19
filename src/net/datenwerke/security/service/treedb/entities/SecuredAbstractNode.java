package net.datenwerke.security.service.treedb.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import net.datenwerke.dtoservices.dtogenerator.Visibility;
import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.security.client.security.dto.DeleteDto;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.dto.GrantAccessDto;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;
import net.datenwerke.security.service.security.HierarchicalSecurityTarget;
import net.datenwerke.security.service.security.entities.HierarchicalAcl;
import net.datenwerke.security.service.security.interfaces.Owneable;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.service.treedb.AbstractNode;

import org.hibernate.envers.Audited;

/**
 * Extends the abstract node and adds ACL security.
 * 
 */
@MappedSuperclass
@GenerateDto(
	dtoPackage="net.datenwerke.security.client.treedb.dto",
	createDecorator=true,
	poso2DtoPostProcessors=SecuredAbstractNode2DtoPostProcessor.class,
	abstractDto=true,
	additionalFields ={
		@AdditionalField(name="availableInheritedAccessRights", type=Set.class, generics=RightDto.class, visibility=Visibility.PROTECTED),
		@AdditionalField(name="availableAccessRights", type=Set.class, generics=RightDto.class, visibility=Visibility.PROTECTED),
		@AdditionalField(name="availableAccessRightsSet", type=Boolean.class, visibility=Visibility.PROTECTED)
	},
	whitelist={
		ReadDto.class,WriteDto.class,ExecuteDto.class,GrantAccessDto.class,DeleteDto.class,
		UserDto.class, UserDtoDec.class, OrganisationalUnitDto.class, GroupDto.class
	}
)
abstract public class SecuredAbstractNode<N extends AbstractNode<N>> extends AbstractNode<N> implements HierarchicalSecurityTarget, Owneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7146543743586621606L;
	
	@EntityClonerIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@Audited
	private User owner;
	
	/**
	 * Stores a reference to the node's ACL
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@EnclosedEntity
	@Audited
	private HierarchicalAcl acl;

	/**
	 * Retrieves the node's ACL
	 */
    public HierarchicalAcl getAcl() {
    	return acl;
	}

    /**
     * Sets the node's acl and notifies the ACL.
     * @param acl
     */
	public void setAcl(HierarchicalAcl acl) {
		this.acl = acl;
	}
	
	@Transient
	public HierarchicalSecurityTarget getParentTarget() {
		return (HierarchicalSecurityTarget) getParent();
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}
}
