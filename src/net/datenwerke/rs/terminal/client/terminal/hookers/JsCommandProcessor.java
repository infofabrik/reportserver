package net.datenwerke.rs.terminal.client.terminal.hookers;

import com.google.gwt.core.client.Scheduler;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class JsCommandProcessor implements CommandResultProcessorHook {

	@Override
	public void process(CommandResultDto result) {
		if(null != result.getExtensions()){
			for(CommandResultExtensionDto ext : result.getExtensions()){
				if(ext instanceof CreJavaScriptDto){
					CreJavaScriptDto extension = (CreJavaScriptDto) ext;
					
					final String data = extension.getData();
					
					Scheduler.get().scheduleDeferred(() -> eval(data));
				}
			}
		}
	}

	public static native void eval(String data) /*-{
    	eval(data);
	}-*/;
		

}
