package net.datenwerke.rs.birt.service.reportengine.output.generator;

import org.eclipse.birt.report.engine.api.RenderOption;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.birt.service.reportengine.hooks.AdaptBirtRenderOptionsHook;

public abstract class BirtOutputGeneratorImpl implements BirtOutputGenerator {

	@Inject
	protected HookHandlerService hookHandler;
	
	@Override
	public boolean isCatchAll() {
		return false;
	}
	
	protected RenderOption adapt(RenderOption options) {
		for(AdaptBirtRenderOptionsHook adapter : hookHandler.getHookers(AdaptBirtRenderOptionsHook.class)){
			RenderOption newOptions = adapter.adapt(options);
			if(null != newOptions)
				options = newOptions;
				
		}
		
		return options;
	}

}
