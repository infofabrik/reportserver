package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.security.service.usermanager.entities.User;

@Entity
@Table(name = "FILESEL_PARAM_INST")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.ext.client.parameters.fileselection.dto", createDecorator = true)
public class FileSelectionParameterInstance extends ParameterInstance<FileSelectionParameterDefinition> {

   /**
    * 
    */
   private static final long serialVersionUID = 5397643575398899447L;

   @JoinTable(name = "FILESEL_PARAM_IN_2_FILE")
   @ExposeToClient
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @EnclosedEntity
   private List<SelectedParameterFile> selectedFiles = new ArrayList<SelectedParameterFile>();

   @Override
   public List getSelectedValue(User user) {
      return new ArrayList<SelectedParameterFile>(selectedFiles);
   }

   @Override
   public List<SelectedParameterFile> getDefaultValue(User user, ParameterSet parameterSet) {
      return new ArrayList<SelectedParameterFile>();
   }

   @Override
   protected Class getType() {
      return Object.class;
   }

   public List<SelectedParameterFile> getSelectedFiles() {
      return selectedFiles;
   }

   public void setSelectedFiles(List<SelectedParameterFile> selectedFiles) {
      this.selectedFiles.clear();
      if (null != selectedFiles)
         this.selectedFiles.addAll(selectedFiles);
   }

   public void addSelectedFile(SelectedParameterFile file) {
      selectedFiles.add(file);
   }

   public UploadedParameterFile getUploadedFileById(Long id) {
      if (null == id)
         return null;
      for (SelectedParameterFile file : selectedFiles) {
         if (null != file.getUploadedFile() && id.equals(file.getUploadedFile().getId()))
            return file.getUploadedFile();
      }
      return null;
   }

}
