package net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasinkmanager.FolderedDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.locale.LocalFileSystemMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;


/**
 * Used to define local file system datasinks that can be used in ReportServer
 * to send reports to a given local file system.
 */
@Entity
@Table(name = "LOCALFILESYSTEM_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto",
      icon = "upload"
      )
@InstanceDescription(
      msgLocation = LocalFileSystemMessages.class,
      objNameKey = "localFileSystemDatasinkTypeName",
      icon = "server"
      )
@Indexed
public class LocalFileSystemDatasink extends DatasinkDefinition implements FolderedDatasink {
   /**
    * 
    */
   private static final long serialVersionUID = -2439404920332697760L;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String path = "/path/to/your/local/dir";

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String folder = "./";
   
   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   @Override
   public String getFolder() {
      return folder;
   }

   public void setFolder(String folder) {
      this.folder = folder;
   }
}