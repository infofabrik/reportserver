package net.datenwerke.rs.onedrive.service.onedrive;

import java.util.Optional;

import com.github.scribejava.core.model.Response;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.security.service.usermanager.entities.User;

public class DummyOneDriveServiceImpl implements OneDriveService {

   @Override
   public Optional<OneDriveDatasink> getDefaultDatasink() {
      return null;
   }

   @Override
   public String getDatasinkPropertyName() {
      return null;
   }

   @Override
   public StorageType getStorageType() {
      return null;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return null;
   }

   @Override
   public void doExportIntoDatasink(Object report, User user, DatasinkDefinition datasinkDefinition,
         DatasinkConfiguration config) throws DatasinkExportException {
   }

   @Override
   public OneDriveDatasink getOneDriveDatasink(String query, TerminalSession session) {
      return null;
   }

   @Override
   public Response[] groupGetMyGroups(OneDriveDatasink oneDriveDatasink, Optional<String> optionalAccessToken) {
      return null;
   }

   @Override
   public Response groupGetDrivesOf(OneDriveDatasink oneDriveDatasink, String groupId, Optional<String> optionalAccessToken) {
      return null;
   }

}