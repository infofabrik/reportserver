package net.datenwerke.rs.onedrive.service.onedrive;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyOneDriveServiceImpl.class)
public interface OneDriveService extends BasicDatasinkService {

}