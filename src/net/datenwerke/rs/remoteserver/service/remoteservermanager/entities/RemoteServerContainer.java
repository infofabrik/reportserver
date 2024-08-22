package net.datenwerke.rs.remoteserver.service.remoteservermanager.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@Entity
@Table(name = "REMOTE_SERVER_CONTAINER")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(dtoPackage = "net.datenwerke.rs.remoteserver.client.remoteservermanager.dto")
public class RemoteServerContainer implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 6605057669794888889L;

   @ExposeToClient
   @ManyToOne
   private RemoteServerDefinition remoteServer;

   @Version
   private Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   public RemoteServerContainer() {
   }

   public RemoteServerContainer(RemoteServerDefinition definition) {
      this.remoteServer = definition;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getVersion() {
      return version;
   }

   public void setVersion(Long version) {
      this.version = version;
   }

   public RemoteServerDefinition getRemoteServer() {
      return remoteServer;
   }

   public void setRemoteServer(RemoteServerDefinition remoteserver) {
      this.remoteServer = remoteserver;
   }

}
