package net.datenwerke.rs.dashboard.client.dashboard.hooks;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public interface DadgetProcessorHook extends Hook {

	public interface DadgetConfigureCallback{
		void configuringDone();
		void cancelled();
	}
	
	public BaseIcon getIcon();
	
	public String getTitle();
	
	public String getDescription();
	
	public boolean consumes(DadgetDto dadget);

	public DadgetDto instantiateDadget();

	public void draw(DadgetDto dadget, DadgetPanel panel);

	public Widget getAdminConfigDialog(DadgetDto dadget, SimpleForm form);
	
	public void displayConfigDialog(DadgetDto dadget,
			DadgetConfigureCallback dadgetConfigureCallback);

	public boolean hasConfigDialog();

	public void addTools(DadgetPanel dadgetPanel);

	public boolean isRedrawOnMove();

	public boolean supportsDadgetLibrary();

	public boolean readyToDisplayParameters(DadgetPanel dadgetPanel);
}
