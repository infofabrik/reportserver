package net.datenwerke.rs.samba.service.samba;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummySambaServiceImpl.class)
public interface SambaService extends BasicDatasinkService {

}
