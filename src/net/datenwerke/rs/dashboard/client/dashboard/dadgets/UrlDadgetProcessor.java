package net.datenwerke.rs.dashboard.client.dashboard.dadgets;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.UrlDadgetDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class UrlDadgetProcessor implements DadgetProcessorHook {

	@Override
	public BaseIcon getIcon() {
		return BaseIcon.WEBPAGE;
	}

	@Override
	public boolean isRedrawOnMove() {
		return false;
	}

	
	@Override
	public String getTitle() {
		return DashboardMessages.INSTANCE.urlDadgetTitle();
	}

	@Override
	public String getDescription() {
		return DashboardMessages.INSTANCE.urlDadgetDescription();
	}

	@Override
	public boolean consumes(DadgetDto dadget) {
		return dadget instanceof UrlDadgetDto;
	}
	
	@Override
	public DadgetDto instantiateDadget() {
		return new UrlDadgetDtoDec();
	}
	
	@Override
	public void draw(final DadgetDto dadget, final DadgetPanel panel) {
		if(null != ((UrlDadgetDto)dadget).getTitle() && ! "".equals(((UrlDadgetDto)dadget).getTitle()))
			panel.setHeading(((UrlDadgetDto)dadget).getTitle());
		else
			panel.setHeading(Format.ellipse(((UrlDadgetDto)dadget).getUrl(), 100));
		
		if(null != ((UrlDadgetDto)dadget).getUrl())
			panel.setUrl(((UrlDadgetDto)dadget).getUrl());
	}

	@Override
	public void displayConfigDialog(final DadgetDto dadget,
			final DadgetConfigureCallback dadgetConfigureCallback) {
		final DwWindow window = new DwWindow();
		window.setHeading(DashboardMessages.INSTANCE.configDadgetTitle());
		window.setHeaderIcon(BaseIcon.COG);
		window.setSize("450px", "300px");
		
		SimpleForm form = SimpleForm.getInlineInstance();
		form.setFieldWidth(400);
		form.setLabelWidth(90);
		form.addField(String.class, UrlDadgetDtoPA.INSTANCE.title(), DashboardMessages.INSTANCE.staticHtmlDadgetTitleLabel());
		form.addField(String.class, UrlDadgetDtoPA.INSTANCE.url(), DashboardMessages.INSTANCE.urlLabel());
		form.addField(Long.class, UrlDadgetDtoPA.INSTANCE.reloadInterval(), DashboardMessages.INSTANCE.reloadIntervalLabel());
		form.addField(Integer.class, UrlDadgetDtoPA.INSTANCE.height(), DashboardMessages.INSTANCE.heightLabel());
		
		final UrlDadgetDto copy = new UrlDadgetDtoDec();
		copy.setUrl(((UrlDadgetDto)dadget).getUrl());
		copy.setTitle(((UrlDadgetDto)dadget).getTitle());
		copy.setReloadInterval(((UrlDadgetDto)dadget).getReloadInterval());
		copy.setHeight(((UrlDadgetDto)dadget).getHeight());
		
		form.bind(copy);
		window.add(form);
		
		DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
		window.addButton(cancelBtn);
		cancelBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				window.hide();
				dadgetConfigureCallback.cancelled();
			}
		});
		
		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		window.addButton(submitBtn);
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				window.hide();
				
				((UrlDadgetDto)dadget).setTitle(copy.getTitle());
				((UrlDadgetDto)dadget).setUrl(copy.getUrl());
				((UrlDadgetDto)dadget).setReloadInterval(copy.getReloadInterval());
				((UrlDadgetDto)dadget).setHeight(copy.getHeight());
				
				dadgetConfigureCallback.configuringDone();
			}
		});
		
		window.show();
	}

	@Override
	public Widget getAdminConfigDialog(DadgetDto dadget, SimpleForm wrappingForm) {
		if(dadget.getHeight() < 1)
			dadget.setHeight(250);
		
		SimpleForm form = SimpleForm.getInlineInstance();
		form.setFieldWidth(400);
		form.setLabelWidth(90);
		form.addField(String.class, UrlDadgetDtoPA.INSTANCE.url(), DashboardMessages.INSTANCE.urlLabel());
		form.bind(dadget);
		
		return form;
	}
	
	@Override
	public boolean supportsDadgetLibrary() {
		return true;
	}

	@Override
	public boolean hasConfigDialog() {
		return true;
	}

	@Override
	public boolean readyToDisplayParameters(DadgetPanel dadgetPanel) {
		return false;
	}
	
	@Override
	public void addTools(DadgetPanel dadgetPanel) {
		// TODO Auto-generated method stub
		
	}
}
