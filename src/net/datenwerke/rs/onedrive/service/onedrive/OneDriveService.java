package net.datenwerke.rs.onedrive.service.onedrive;

import java.io.IOException;
import java.util.Optional;

import com.github.scribejava.core.model.Response;
import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;

@ImplementedBy(DummyOneDriveServiceImpl.class)
public interface OneDriveService extends BasicDatasinkService {

   /**
    * Acquire a onedrive-datasink from through the object resolver
    * @param query that describes an onedrive-datasink
    * @param session refers to a terminal-session
    * @return OneDriveDatasink if found
    * @throws IllegalArgumentException if no matching datasink is found or multiple datasinks match
    */
   OneDriveDatasink getOneDriveDatasink(String query, TerminalSession session) throws IllegalArgumentException;

   /**
    * Acquires all microsoft groups that the user has access to
    * @param oneDriveDatasink which configures tenant-id, app-key, app-secret, OAuthApi and access token
    * @param optionalAccessToken if set to != null this access token is used instead of the default one of oneDriveDatasink
    * @return Response[] that holds onedrive-group objects that refer to the onedrive graph api call "https://graph.microsoft.com/v1.0/groups/[group-id]"
    * @throws IOException is thrown if an api call fails with code != [200|201]
    */
   Response[] groupGetMyGroups(OneDriveDatasink oneDriveDatasink, Optional<String> optionalAccessToken) throws IOException;

   /**
    * Acquires all available drives of a given group
    * @param oneDriveDatasink which configures tenant-id, app-key, app-secret, OAuthApi and access token
    * @param groupId refers to the onedrive-group which will be queried
    * @param optionalAccessToken if set to != null this access token is used instead of the default one of oneDriveDatasink
    * @return Response that holds all available onedrive-drive objects of the given group
    * @throws IOException is thrown if an api call fails with code != [200|201]
    */
   Response groupGetDrivesOf(OneDriveDatasink oneDriveDatasink, String groupId, Optional<String> optionalAccessToken) throws IOException;

}