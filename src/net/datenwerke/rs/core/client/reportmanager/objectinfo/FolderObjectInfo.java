package net.datenwerke.rs.core.client.reportmanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FolderObjectInfo extends GeneralObjectInfoImpl<ReportFolderDto> {

   @Override
   public boolean consumes(Object object) {
      return object instanceof ReportFolderDto;
   }

   @Override
   protected String doGetName(ReportFolderDto folder) {
      return folder.getName();
   }

   @Override
   protected String doGetType(ReportFolderDto folder) {
      return ReportmanagerMessages.INSTANCE.folder();
   }

   @Override
   public ImageResource doGetIconSmall(ReportFolderDto object) {
      return BaseIcon.FOLDER_O.toImageResource();
   }
}
