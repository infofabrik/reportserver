package net.datenwerke.security.client.usermanager.dto.ie;

import net.datenwerke.security.client.usermanager.dto.UserDto;

public class StrippedDownUser extends AbstractStrippedDownNode {

   private static final long serialVersionUID = -7289428683601291812L;

   private String firstname;
   private String lastname;

   public String getFirstname() {
      return firstname;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public String getLastname() {
      return lastname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   @Override
   public String toString() {
      return getLastname() + ", " + getFirstname();
   }

   public static StrippedDownUser fromUser(UserDto user) {
      StrippedDownUser sUser = new StrippedDownUser();

      sUser.setId(user.getId());
      sUser.setFirstname(user.getFirstname());
      sUser.setLastname(user.getLastname());

      return sUser;
   }

   @Override
   public int hashCode() {
      if (null != getId())
         return getId().hashCode();
      return super.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof StrippedDownUser))
         return false;

      if (null == getId())
         return super.equals(obj);

      return getId().equals(((StrippedDownUser) obj).getId());
   }

}
