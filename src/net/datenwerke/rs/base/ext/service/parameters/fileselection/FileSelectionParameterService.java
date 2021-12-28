package net.datenwerke.rs.base.ext.service.parameters.fileselection;

public interface FileSelectionParameterService {

   public void persist(UploadedParameterFile file);

   void persist(SelectedParameterFile file);

   FileSelectionParameterInstance getParameterInstanceWithFile(SelectedParameterFile file);

   SelectedParameterFile getSelectedFileById(Long id);
}
