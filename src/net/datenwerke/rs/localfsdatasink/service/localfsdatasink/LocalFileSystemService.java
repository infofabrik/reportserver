package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyLocalFileSystemServiceImpl.class)
public interface LocalFileSystemService extends BasicDatasinkService {

}