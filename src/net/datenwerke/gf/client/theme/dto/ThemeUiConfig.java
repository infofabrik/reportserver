package net.datenwerke.gf.client.theme.dto;

import java.io.Serializable;

public class ThemeUiConfig implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String logoLoginHtml = "<i class=\"icon-rs-logo rs-login-logo\"></i><span class=\"rs-login-bg\"><i class=\"icon-rs-logo-square\"></i></span>";
   private String logoLoginWidth = "200px";

   private String logoHeaderHtml = "<span class=\"rs-header-logo\"><i class=\"icon-rs-Report\"></i><i class=\"icon-rs-Server\"></i></span>";
   private String logoHeaderWidth = "180px";

   private int headerHeight = 40;

   public ThemeUiConfig() {
      // default constructor
   }

   public String getLogoLoginHtml() {
      return logoLoginHtml;
   }

   public void setLogoLoginHtml(String logoLoginHtml) {
      this.logoLoginHtml = logoLoginHtml;
   }

   public String getLogoLoginWidth() {
      return logoLoginWidth;
   }

   public void setLogoLoginWidth(String logoLoginWidth) {
      this.logoLoginWidth = logoLoginWidth;
   }

   public String getLogoHeaderHtml() {
      return logoHeaderHtml;
   }

   public void setLogoHeaderHtml(String logoHeaderHtml) {
      this.logoHeaderHtml = logoHeaderHtml;
   }

   public String getLogoHeaderWidth() {
      return logoHeaderWidth;
   }

   public void setLogoHeaderWidth(String logoHeaderWidth) {
      this.logoHeaderWidth = logoHeaderWidth;
   }

   public int getHeaderHeight() {
      return headerHeight;
   }

   public void setHeaderHeight(int headerHeight) {
      this.headerHeight = headerHeight;
   }

}
