package net.datenwerke.security.service.security.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

/**
 * 
 *
 */
@Entity
@Table(name = "HIERARCHICAL_ACE")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.security.client.security.dto")
public class HierarchicalAce extends Ace {

   /**
    * 
    */
   private static final long serialVersionUID = -1640918825185870573L;

   /**
    * Describes whether this Ace is applies to the current node, to all children,
    * or to both
    */
   @ExposeToClient
   @Column(nullable = false)
   private InheritanceType inheritancetype = InheritanceType.BOTH;

   public InheritanceType getInheritancetype() {
      return inheritancetype;
   }

   public void setInheritancetype(InheritanceType inheritancetype) {
      this.inheritancetype = inheritancetype;
   }

   @Transient
   public boolean isHereACE() {
      return InheritanceType.HERE.equals(inheritancetype) || InheritanceType.BOTH.equals(inheritancetype);
   }

   @Transient
   public boolean isInheritedACE() {
      return InheritanceType.INHERITED.equals(inheritancetype) || InheritanceType.BOTH.equals(inheritancetype);
   }

}
