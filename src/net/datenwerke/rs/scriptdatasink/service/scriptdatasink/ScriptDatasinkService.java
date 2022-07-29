package net.datenwerke.rs.scriptdatasink.service.scriptdatasink;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyScriptDatasinkServiceImpl.class)
public interface ScriptDatasinkService extends BasicDatasinkService {

   String DATASINK_CONFIG = "datasinkConfiguration";

}