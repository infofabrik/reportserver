package net.datenwerke.rs.saiku.service.saiku.reportengine.hooks;

import org.olap4j.OlapConnection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;

@HookConfig
public interface OlapConnectionHook extends Hook {

   public OlapConnection postprocessConnection(MondrianDatasource mondrianDatasource, OlapConnection connection);

}
