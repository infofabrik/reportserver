package net.datenwerke.rs.ldapserver.service.ldapserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.ldapserver.service.ldapserver.entities.dtogen.LdapServer2DtoPostProcessor;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.locale.RemoteServerManagerMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;


/**
 * Used to define a connection to the external RS server
 */

@Entity
@Table(name = "RS_LDAP_SERVER")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.ldapserver.client.ldapserver.dto", 
      poso2DtoPostProcessors = LdapServer2DtoPostProcessor.class, 
      additionalFields = {
                  @AdditionalField(
                  name = "hasSecurityCredentials", 
                  type = Boolean.class
                  ) 
            }, 
            icon = "upload"
      )
@InstanceDescription(
      msgLocation = RemoteServerManagerMessages.class, 
      objNameKey = "ldapServer",
      icon = "upload"
      )
@Indexed
public class LdapServer extends RemoteServerDefinition {

   /**
    * 
    */
   private static final long serialVersionUID = 5532424176260294397L;
   
   @Inject
   protected static Provider<PbeService> pbeServiceProvider;
 
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String providerHost;
   
   @ExposeToClient
   @Field
   private boolean ldapDisabled = false;
   
   @ExposeToClient
   @Field
   private int providerPort;

   @ExposeToClient
   @Field
   @Column(length = 20)
   private String securityEncryption;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String securityPrincipal;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String securityCredentials;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String ldapBase;
   
   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String ldapFilter;
   
   @ExposeToClient
   @ManyToOne
   private OrganisationalUnit externalDir;
   
   @ExposeToClient
   @Field
   private boolean writeProtection = false;
   
   @ExposeToClient
   @Field
   private boolean logResultingTree = false;
   
   @ExposeToClient
   @Field
   private boolean flattenTree = false;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrObjClass;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrGuid;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrOrgUnitObjClass;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrOrgUnitObjName;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrGroupObjClass;
      
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrGroupName;
      
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrGroupMember;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrUserObjClass;
   
   public String getProviderHost() {
      return providerHost;
   }

   public void setProviderHost(String providerHost) {
      this.providerHost = providerHost;
   }

   public boolean isLdapDisabled() {
      return ldapDisabled;
   }

   public void setLdapDisabled(boolean ldapDisabled) {
      this.ldapDisabled = ldapDisabled;
   }

   public int getProviderPort() {
      return providerPort;
   }

   public void setProviderPort(int providerPort) {
      this.providerPort = providerPort;
   }

   public String getSecurityEncryption() {
      return securityEncryption;
   }

   public void setSecurityEncryption(String securityEncryption) {
      this.securityEncryption = securityEncryption;
   }

   public String getSecurityPrincipal() {
      return securityPrincipal;
   }

   public void setSecurityPrincipal(String securityPrincipal) {
      this.securityPrincipal = securityPrincipal;
   }

   public String getSecurityCredentials() {
      if (null == securityCredentials)
         return null;
      if ("".equals(securityCredentials))
         return "";
      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      return new String(encryptionService.decryptFromHex(securityCredentials));
   }

   /**
    * Encrypts and sets the given password.
    * 
    * @param password the password to encrypt and set
    */
   public void setSecurityCredentials(String securityCredentials) {
      if (null == securityCredentials)
         securityCredentials = "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      byte[] encrypted = encryptionService.encrypt(securityCredentials);

      this.securityCredentials = new String(Hex.encodeHex(encrypted));
   }   

   public String getLdapBase() {
      return ldapBase;
   }

   public void setLdapBase(String ldapBase) {
      this.ldapBase = ldapBase;
   }

   public String getLdapFilter() {
      return ldapFilter;
   }

   public void setLdapFilter(String ldapFilter) {
      this.ldapFilter = ldapFilter;
   }

   public OrganisationalUnit getExternalDir() {
      return externalDir;
   }

   public void setExternalDir(OrganisationalUnit externalDir) {
      this.externalDir = externalDir;
   }

   public boolean isWriteProtection() {
      return writeProtection;
   }

   public void setWriteProtection(boolean writeProtection) {
      this.writeProtection = writeProtection;
   }

   public boolean isLogResultingTree() {
      return logResultingTree;
   }

   public void setLogResultingTree(boolean logResultingTree) {
      this.logResultingTree = logResultingTree;
   }

   public boolean isFlattenTree() {
      return flattenTree;
   }

   public void setFlattenTree(boolean flattenTree) {
      this.flattenTree = flattenTree;
   }

   public String getAttrObjClass() {
      return attrObjClass;
   }

   public void setAttrObjClass(String attrObjClass) {
      this.attrObjClass = attrObjClass;
   }

   public String getAttrGuid() {
      return attrGuid;
   }

   public void setAttrGuid(String attrGuid) {
      this.attrGuid = attrGuid;
   }

   public String getAttrOrgUnitObjClass() {
      return attrOrgUnitObjClass;
   }

   public void setAttrOrgUnitObjClass(String attrOrgUnitObjClass) {
      this.attrOrgUnitObjClass = attrOrgUnitObjClass;
   }

   public String getAttrOrgUnitObjName() {
      return attrOrgUnitObjName;
   }

   public void setAttrOrgUnitObjName(String attrOrgUnitObjName) {
      this.attrOrgUnitObjName = attrOrgUnitObjName;
   }

   public String getAttrGroupObjClass() {
      return attrGroupObjClass;
   }

   public void setAttrGroupObjClass(String attrGroupObjClass) {
      this.attrGroupObjClass = attrGroupObjClass;
   }

   public String getAttrGroupName() {
      return attrGroupName;
   }

   public void setAttrGroupName(String attrGroupName) {
      this.attrGroupName = attrGroupName;
   }

   public String getAttrGroupMember() {
      return attrGroupMember;
   }

   public void setAttrGroupMember(String attrGroupMember) {
      this.attrGroupMember = attrGroupMember;
   }

   public String getAttrUserObjClass() {
      return attrUserObjClass;
   }

   public void setAttrUserObjClass(String attrUserObjClass) {
      this.attrUserObjClass = attrUserObjClass;
   }

   public String getAttrUserFirstname() {
      return attrUserFirstname;
   }

   public void setAttrUserFirstname(String attrUserFirstname) {
      this.attrUserFirstname = attrUserFirstname;
   }

   public String getAttrUserLastname() {
      return attrUserLastname;
   }

   public void setAttrUserLastname(String attrUserLastname) {
      this.attrUserLastname = attrUserLastname;
   }

   public String getAttrUserUsername() {
      return attrUserUsername;
   }

   public void setAttrUserUsername(String attrUserUsername) {
      this.attrUserUsername = attrUserUsername;
   }

   public String getAttrUserMail() {
      return attrUserMail;
   }

   public void setAttrUserMail(String attrUserMail) {
      this.attrUserMail = attrUserMail;
   }

   public String getAttrAdd() {
      return attrAdd;
   }

   public void setAttrAdd(String attrAdd) {
      this.attrAdd = attrAdd;
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrUserFirstname;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrUserLastname;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrUserUsername;
   
   @ExposeToClient
   @Field
   @Column(length = 255)
   private String attrUserMail;
   
   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String attrAdd;

}