package net.datenwerke.rs.core.service.datasinkmanager.hookers.factory;

import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.DatasinkDefaultMergeHooker;

public interface DatasinkDefaultMergeHookerFactory {
   
   DatasinkDefaultMergeHooker<? extends DatasinkDefinition> create(Class<? extends DatasinkDefinition> targetClass);

}
