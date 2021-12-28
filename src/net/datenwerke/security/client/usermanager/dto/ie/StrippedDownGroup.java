package net.datenwerke.security.client.usermanager.dto.ie;

import net.datenwerke.security.client.usermanager.dto.GroupDto;

public class StrippedDownGroup extends AbstractStrippedDownNode {

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

   public static StrippedDownGroup fromGroup(GroupDto group) {
      StrippedDownGroup sGroup = new StrippedDownGroup();

      sGroup.setId(group.getId());
      sGroup.setName(group.getName());

      return sGroup;
   }

   @Override
   public int hashCode() {
      if (null != getId())
         return getId().hashCode();
      return super.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof StrippedDownGroup))
         return false;

      if (null == getId())
         return super.equals(obj);

      return getId().equals(((StrippedDownGroup) obj).getId());
   }
}
