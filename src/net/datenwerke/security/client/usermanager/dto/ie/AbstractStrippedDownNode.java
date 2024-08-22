package net.datenwerke.security.client.usermanager.dto.ie;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;

public abstract class AbstractStrippedDownNode extends RsDto {

   private static final long serialVersionUID = -2095982606521264464L;

   private Long id;
   private String parentOu;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getParentOu() {
      return parentOu;
   }

   public void setParentOu(String parentOu) {
      this.parentOu = parentOu;
   }
}
