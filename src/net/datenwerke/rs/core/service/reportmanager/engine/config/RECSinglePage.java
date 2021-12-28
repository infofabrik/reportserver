package net.datenwerke.rs.core.service.reportmanager.engine.config;

import java.util.Objects;

public class RECSinglePage implements RECPaged {

   /**
    * 
    */
   private static final long serialVersionUID = 29621752085956289L;

   private int page;
   private int pageSize = 100;

   public RECSinglePage() {
      // dummy
   }

   public RECSinglePage(int page) {
      this.page = page;
   }

   public RECSinglePage(int page, int pagesize) {
      this.page = page;
      this.pageSize = pagesize;
   }

   @Override
   public int getFirstPage() {
      return page;
   }

   @Override
   public int getLastPage() {
      return page;
   }

   @Override
   public int getPageSize() {
      return this.pageSize;
   }

   public void setPage(int page) {
      this.page = page;
   }

   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }

   @Override
   public int hashCode() {
      return Objects.hash(page, pageSize);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;

      if (obj instanceof RECSinglePage) {
         final RECSinglePage other = (RECSinglePage) obj;
         return page == other.page
               && pageSize == other.pageSize;
      } else {
         return false;
      }
   }
}
