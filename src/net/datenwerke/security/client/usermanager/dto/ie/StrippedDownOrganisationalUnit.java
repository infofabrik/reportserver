package net.datenwerke.security.client.usermanager.dto.ie;

import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;

public class StrippedDownOrganisationalUnit extends AbstractStrippedDownNode {

   /**
    * 
    */
   private static final long serialVersionUID = 91919518656343884L;

   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public static StrippedDownOrganisationalUnit fromOu(OrganisationalUnitDto ou) {
      StrippedDownOrganisationalUnit sOu = new StrippedDownOrganisationalUnit();

      sOu.setId(ou.getId());
      sOu.setName(ou.getName());

      return sOu;
   }

   @Override
   public int hashCode() {
      if (null != getId())
         return getId().hashCode();
      return super.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof StrippedDownOrganisationalUnit))
         return false;

      if (null == getId())
         return super.equals(obj);

      return getId().equals(((StrippedDownOrganisationalUnit) obj).getId());
   }
}
