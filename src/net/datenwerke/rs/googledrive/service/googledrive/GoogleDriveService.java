package net.datenwerke.rs.googledrive.service.googledrive;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyGoogleDriveServiceImpl.class)
public interface GoogleDriveService extends BasicDatasinkService {

}
