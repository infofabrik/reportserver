package net.datenwerke.rs.birt.service.reportengine.hooks;

import org.eclipse.birt.report.engine.api.RenderOption;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public interface AdaptBirtRenderOptionsHook extends Hook {

   public RenderOption adapt(RenderOption options);

}