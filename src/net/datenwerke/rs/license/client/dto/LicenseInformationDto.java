package net.datenwerke.rs.license.client.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class LicenseInformationDto implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String rsVersion;
   private String serverId;
   private Date installationDate;
   private String licenseType;
   private Date expirationDate;
   private String name;
   private HashMap<String, String> additionalLicenseProperties;
   private Date licenseIssueDate;
   private Date upgradesUntil;

   public LicenseInformationDto() {
   }

   public String getServerId() {
      return serverId;
   }

   public void setServerId(String serverId) {
      this.serverId = serverId;
   }

   public Date getInstallationDate() {
      return installationDate;
   }

   public void setInstallationDate(Date installationDate) {
      this.installationDate = installationDate;
   }

   public String getLicenseType() {
      return licenseType;
   }

   public void setLicenseType(String licenseType) {
      this.licenseType = licenseType;
   }

   public Date getExpirationDate() {
      return expirationDate;
   }

   public void setExpirationDate(Date expirationDate) {
      this.expirationDate = expirationDate;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public HashMap<String, String> getAdditionalLicenseProperties() {
      return additionalLicenseProperties;
   }

   public void setAdditionalLicenseProperties(HashMap<String, String> additionalLicenseProperties) {
      this.additionalLicenseProperties = additionalLicenseProperties;
   }

   public void setLicenseIssueDate(Date licenseIssueDate) {
      this.licenseIssueDate = licenseIssueDate;
   }

   public Date getLicenseIssueDate() {
      return licenseIssueDate;
   }

   public Date getUpgradesUntil() {
      return upgradesUntil;
   }

   public void setUpgradesUntil(Date upgradesUntil) {
      this.upgradesUntil = upgradesUntil;
   }

   public String getRsVersion() {
      return rsVersion;
   }

   public void setRsVersion(String rsVersion) {
      this.rsVersion = rsVersion;
   }

}
