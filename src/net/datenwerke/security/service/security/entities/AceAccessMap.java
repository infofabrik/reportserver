package net.datenwerke.security.service.security.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.security.service.security.rights.Right;

/**
 * 
 *
 */
@Entity
@Table(name = "ACE_ACCESS_MAP")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.security.client.security.dto", proxyableDto = false, createDecorator = true)
public class AceAccessMap implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -1905865857100779466L;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue
   private Long id;

   @Version
   private Integer version;

   @ExposeToClient
   private Long access = 0L;

   @ExposeToClient
   @Column(length = 32)
   private String securee;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Integer getVersion() {
      return version;
   }

   @SuppressWarnings("unused")
   private void setVersion(Integer version) {
      this.version = version;
   }

   public Long getAccess() {
      return access;
   }

   public void setAccess(long access) {
      this.access = access;
   }

   @Transient
   public void addAccessRight(Right right) {
      this.access |= right.getBitField();
   }

   @Transient
   public void addAccessRight(long right) {
      this.access |= right;
   }

   @Transient
   public void setUniversalAccess() {
      for (int i = 0; i < 64; i++)
         this.access |= 1 << i;
   }

   @Transient
   public boolean hasAccessRight(long right) {
      return (this.access & right) == right;
   }

   @Transient
   public boolean hasAccessRight(Right right) {
      return hasAccessRight(right.getBitField());
   }

   @Transient
   public void clearAccess() {
      setAccess(0);
   }

   public String getSecuree() {
      return securee;
   }

   public void setSecuree(String securee) {
      this.securee = securee;
   }

}
