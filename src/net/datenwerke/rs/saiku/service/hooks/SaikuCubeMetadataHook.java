package net.datenwerke.rs.saiku.service.hooks;

import org.olap4j.metadata.Cube;
import org.saiku.olap.dto.SaikuCubeMetadata;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public interface SaikuCubeMetadataHook extends Hook {

   public void adapt(SaikuCubeMetadata metadata, Cube cube);

}
