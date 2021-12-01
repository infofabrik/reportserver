package net.datenwerke.rs.terminal.client.terminal.hookers;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class MessageCommandProcessor implements CommandResultProcessorHook {

	@Override
	public void process(CommandResultDto result) {
		if(null != result.getExtensions()){
			result.getExtensions().stream().forEach(ext -> {
				if(ext instanceof CreMessageDto){
					CreMessageDto extension = (CreMessageDto) ext;
					
					final DwWindow window = new DwWindow();
					window.setModal(true);
					window.setSize(extension.getWidth() == 0 ? 500 : extension.getWidth(), extension.getHeight() == 0 ? 300 : extension.getHeight());
					window.setBodyBorder(true);
					if(null != extension.getWindowTitle())
						window.setHeading(extension.getWindowTitle());
					
					VerticalLayoutContainer lc = new VerticalLayoutContainer();
					lc.setScrollMode(ScrollMode.AUTOY);
					window.add(lc,new MarginData(5));
					if(null != extension.getTitle()){
						SeparatorTextLabel title = SeparatorTextLabel.createHeadlineLarge(extension.getTitle());
						title.addStyleName("rs-cre-message-title");
						lc.add(title);
					}
					
					Widget w;
					if(null != extension.getHtml())
						w = new HTML(extension.getHtml());
					else
						w = SeparatorTextLabel.createText(extension.getText());
					w.addStyleName("rs-cre-message-msg");
					lc.add(w);
					
					DwTextButton ok = new DwTextButton(BaseMessages.INSTANCE.ok());
					ok.addSelectHandler(event -> window.hide());
					window.addButton(ok);
					
					window.show();
				}
			});
		}
	}

}
