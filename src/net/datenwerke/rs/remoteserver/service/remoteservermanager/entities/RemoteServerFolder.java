package net.datenwerke.rs.remoteserver.service.remoteservermanager.entities;

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
import net.datenwerke.rs.remoteserver.service.remoteservermanager.locale.RemoteServerManagerMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

/**
 * Entity that can be used to organize remoteservers hierarchically.
 * 
 *
 */
@Entity
@Table(name = "REMOTE_SERVER_FOLDER")
@Audited
@Indexed
@TreeDBAllowedChildren({ RemoteServerFolder.class, RemoteServerDefinition.class })
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.remoteserver.client.remoteservermanager.dto", 
      dtoImplementInterfaces = FolderDto.class, 
      typeDescriptionMsg = BaseMessages.class, 
      typeDescriptionKey = "folder", 
      icon = "folder"
)
@InstanceDescription(
      msgLocation = RemoteServerManagerMessages.class, 
      objNameKey = "remoteServerFolderTypeName", 
      icon = "folder"
)
public class RemoteServerFolder extends AbstractRemoteServerManagerNode {

   /**
    * 
    */
   private static final long serialVersionUID = 3139118358223220435L;

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

   public RemoteServerFolder() {

   }

   public RemoteServerFolder(String folder) {
      setName(folder);
   }

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

   @Override
   public boolean isFolder() {
      return true;
   }
}
