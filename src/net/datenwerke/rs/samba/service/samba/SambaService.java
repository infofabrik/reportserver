package net.datenwerke.rs.samba.service.samba;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;

@ImplementedBy(DummySambaServiceImpl.class)
public interface SambaService extends BasicDatasinkService {

   Optional<SambaDatasink> getDefaultDatasink();
}
