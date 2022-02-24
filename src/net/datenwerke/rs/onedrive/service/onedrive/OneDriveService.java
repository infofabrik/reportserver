package net.datenwerke.rs.onedrive.service.onedrive;

import java.util.Optional;

import com.github.scribejava.core.model.Response;
import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;

@ImplementedBy(DummyOneDriveServiceImpl.class)
public interface OneDriveService extends BasicDatasinkService {

   OneDriveDatasink getOneDriveDatasink(String query, TerminalSession session);

   Response[] groupGetMyGroups(OneDriveDatasink oneDriveDatasink, Optional<String> optionalAccessToken);

   Response groupGetDrivesOf(OneDriveDatasink oneDriveDatasink, String groupId, Optional<String> optionalAccessToken);

}