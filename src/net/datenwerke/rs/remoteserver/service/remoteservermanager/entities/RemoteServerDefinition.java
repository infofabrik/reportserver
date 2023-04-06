package net.datenwerke.rs.remoteserver.service.remoteservermanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.common.base.MoreObjects;
import com.google.inject.Injector;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerManagerMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;

/**
 * Used to define remote servers.
 *
 */
@Entity
@Table(name = "REMOTE_SERVER_DEFINITION")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.remoteserver.client.remoteservermanager.dto", 
      abstractDto = true, 
      typeDescriptionMsg = RemoteServerManagerMessages.class, 
      typeDescriptionKey = "remoteServers"
      )
abstract public class RemoteServerDefinition extends AbstractRemoteServerManagerNode {

   /**
    * 
    */
   private static final long serialVersionUID = -5049862067210491425L;

   @ExposeToClient(displayTitle = true)
   @Field
   @Column(length = 128)
   @Title
   private String name;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Description
   private String description;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   @Transient
   public String escapeString(Injector injector, String string) {
      return string;
   }

   @Override
   public boolean hasChildren() {
      return false;
   }

   @Override
   public String toString() {
       return MoreObjects.toStringHelper(getClass())
             .add("ID", getIdOrOldTransient())
             .add("Name", name)
             .toString();
   }
}