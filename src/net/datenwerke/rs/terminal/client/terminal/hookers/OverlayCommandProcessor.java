package net.datenwerke.rs.terminal.client.terminal.hookers;

import com.google.inject.Inject;
import com.sencha.gxt.core.client.dom.XElement;

import net.datenwerke.gxtdto.client.overlay.OverlayService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class OverlayCommandProcessor implements CommandResultProcessorHook {

	private final OverlayService overlayService;
	
	@Inject
	public OverlayCommandProcessor(OverlayService overlayService) {
		this.overlayService = overlayService;
	}



	@Override
	public void process(CommandResultDto result) {
		if(null != result.getExtensions()){
			for(CommandResultExtensionDto ext : result.getExtensions()){
				if(ext instanceof CreOverlayDto){
					CreOverlayDto extension = (CreOverlayDto) ext;
					
					XElement overlay = null;
					if(null == extension.getName())
						overlay = overlayService.overlay(extension.getText());
					else {
						if(extension.isRemove()){
							overlayService.remove(extension.getName());
							continue;
						}
							
						overlay = overlayService.overlay(extension.getName(), extension.getText());
					}
					
					StringBuilder styles = new StringBuilder();
					extension.getCssProperties().keySet()
						.stream()
						.forEach(key -> 
							styles.append(key).append(": ").append(extension.getCssProperties().get((String) key)).append("; "));
					overlay.applyStyles(styles.toString());
				}
			}
		}
	}

}
