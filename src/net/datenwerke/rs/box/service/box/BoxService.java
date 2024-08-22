package net.datenwerke.rs.box.service.box;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyBoxServiceImpl.class)
public interface BoxService extends BasicDatasinkService {

}
