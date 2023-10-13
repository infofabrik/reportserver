package net.datenwerke.rs.transport.service.transport.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.FolderDto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

/**
 * 
 *
 */
@Entity
@Table(name = "TRANSPORT_FOLDER")
@Audited
@Indexed
@TreeDBAllowedChildren({ Transport.class, TransportFolder.class })
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.transport.client.transport.dto", 
      dtoImplementInterfaces = FolderDto.class, 
      typeDescriptionMsg = BaseMessages.class, 
      typeDescriptionKey = "folder", 
      icon = "folder"
)
@InstanceDescription(
      msgLocation = TransportManagerMessages.class, 
      objNameKey = "transportFolderTypeName", 
      icon = "folder"
)
public class TransportFolder extends AbstractTransportManagerNode implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -8497522954279869294L;

   @ExposeToClient(displayTitle = true)
   @Column(length = 128)
   @Title
   @Field
   private String name;
   
   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Description
   @Field
   private String description;
   
   public TransportFolder() {
   }

   public TransportFolder(String name) {
      setName(name);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public boolean isFolder() {
      return true;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
