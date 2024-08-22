package net.datenwerke.rs.dropbox.service.dropbox;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyDropboxServiceImpl.class)
public interface DropboxService extends BasicDatasinkService {

}
