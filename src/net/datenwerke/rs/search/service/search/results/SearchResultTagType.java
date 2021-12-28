package net.datenwerke.rs.search.service.search.results;

import java.io.Serializable;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.search.client.search.dto"

)
public class SearchResultTagType implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -3790852725942130333L;

   @ExposeToClient
   private String type;

   @ExposeToClient
   private String display;

   public SearchResultTagType() {
   }

   public SearchResultTagType(String type, String display) {
      this.type = type;
      this.display = display;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getDisplay() {
      return display;
   }

   public void setDisplay(String display) {
      this.display = display;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((type == null) ? 0 : type.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (!(obj instanceof SearchResultTagType)) {
         return false;
      }
      SearchResultTagType other = (SearchResultTagType) obj;
      if (type == null) {
         if (other.type != null) {
            return false;
         }
      } else if (!type.equals(other.type)) {
         return false;
      }
      return true;
   }

}
