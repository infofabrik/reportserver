package net.datenwerke.rs.fileserver.service.fileserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.FolderDto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.locale.FileserverMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

@Entity
@Table(name = "FILE_SERVER_FOLDER")
@Audited
@Indexed
@TreeDBAllowedChildren({ FileServerFolder.class, FileServerFile.class })
@GenerateDto(dtoPackage = "net.datenwerke.rs.fileserver.client.fileserver.dto", dtoImplementInterfaces = FolderDto.class, typeDescriptionMsg = BaseMessages.class, typeDescriptionKey = "folder", icon = "folder")
@InstanceDescription(msgLocation = FileserverMessages.class, objNameKey = "folderTypeName", icon = "folder")
public class FileServerFolder extends AbstractFileServerNode {

   /**
    * 
    */
   private static final long serialVersionUID = 5246206383961083936L;
   
   @Inject
   protected static Provider<FileServerService> fileServerServiceProvider;

   @ExposeToClient(view = DtoView.MINIMAL, displayTitle = true)
   @Column(length = 128)
   @Field
   @Title
   private String name;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Field
   @Description
   private String description;

   @ExposeToClient
   private boolean publiclyAccessible = false;

   public FileServerFolder() {
      super();
   }

   public FileServerFolder(String name) {
      super();
      this.setName(name);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      if (this.name != null && this.name.contentEquals(name)) return;
      if (!fileServerServiceProvider.get().checkNewName(name, this))
         throw new IllegalArgumentException("File with same name already exists.");
      this.name = name;
   }
   
   @Override
   public void setParent(AbstractNode<AbstractFileServerNode> abstractNode) {
      if (!fileServerServiceProvider.get().checkNewParent(abstractNode, this))
         throw new IllegalArgumentException("File with same name already exists in new parent.");
      super.setParent(abstractNode);
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public void setPubliclyAccessible(boolean publiclyAccessible) {
      this.publiclyAccessible = publiclyAccessible;
   }

   public boolean isPubliclyAccessible() {
      return publiclyAccessible;
   }

   @Override
   public boolean isFolder() {
      return true;
   }

   public FileServerFolder getSubfolderByName(String name) {
      if (null == name)
         return null;

      for (FileServerFolder f : getChildrenOfType(FileServerFolder.class))
         if (name.equals(f.getName()))
            return f;

      return null;
   }
}
