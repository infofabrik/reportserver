package net.datenwerke.rs.box.service.box;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyBoxServiceImpl.class)
public interface BoxService extends BasicDatasinkService {

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<BoxDatasink> getDefaultDatasink();

}
