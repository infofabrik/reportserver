package net.datenwerke.rs.tabledatasink.service.tabledatasink;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyTableDatasinkServiceImpl.class)
public interface TableDatasinkService extends BasicDatasinkService {

}
