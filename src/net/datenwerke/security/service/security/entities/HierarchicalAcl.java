package net.datenwerke.security.service.security.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Table(name = "HIERARCHICAL_ACL")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Audited
public class HierarchicalAcl extends Acl {

   /**
    * 
    */
   private static final long serialVersionUID = -2545028130153819825L;

   @Override
   public void addAce(Ace ace) {
      if (!(ace instanceof HierarchicalAce))
         throw new IllegalArgumentException("Expected hierarchical ACE");
      super.addAce(ace);
   }

   @Override
   public void addAce(Ace ace, int position) {
      if (!(ace instanceof HierarchicalAce))
         throw new IllegalArgumentException("Expected hierarchical ACE");
      super.addAce(ace, position);
   }

}
