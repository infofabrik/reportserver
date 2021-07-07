package net.datenwerke.rs.dsbundle.service.dsbundle;

import java.util.List;
import java.util.Set;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle;

@ImplementedBy(DummyDatasourceBundleServiceImpl.class)
public interface SimpleDatasourceBundleService {

   List<String> getAvailableBundleKeys();

   Set<DatasourceDefinition> getAllDatasources(DatasourceContainerProvider container, List<String> keys);
   
   Set<DatasourceDefinition> getAllDatasources(DatabaseBundle datasource, List<String> keys);

}
