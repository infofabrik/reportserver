package net.datenwerke.rs.scp.service.scp;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyScpServiceImpl.class)
public interface ScpService extends BasicDatasinkService {

}
