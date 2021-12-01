package net.datenwerke.rs.dashboard.client.dashboard.dadgets;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCodeMirror;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.StaticHtmlDadgetDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class StaticHtmlDadgetProcessor implements DadgetProcessorHook {

	private final UtilsUIService utilsService;
	
	@Inject
	public StaticHtmlDadgetProcessor(UtilsUIService utilsService) {
		super();
		this.utilsService = utilsService;
	}

	@Override
	public BaseIcon getIcon() {
		return BaseIcon.CODE;
	}
	
	@Override
	public boolean isRedrawOnMove() {
		return true;
	}


	@Override
	public String getTitle() {
		return DashboardMessages.INSTANCE.staticHtmlDadgetTitle();
	}

	@Override
	public String getDescription() {
		return DashboardMessages.INSTANCE.staticHtmlDadgetDescription();
	}

	@Override
	public boolean consumes(DadgetDto dadget) {
		return dadget instanceof StaticHtmlDadgetDto;
	}

	@Override
	public DadgetDto instantiateDadget() {
		StaticHtmlDadgetDtoDec dadget = new StaticHtmlDadgetDtoDec();
		
		dadget.setData("<!DOCTYPE html>\n" +
				"<html>\n" +
				"\t<head>\n" +
				"\t</head>\n" +
				"\t<body>\n" +
				"\t</body>\n" +
				"</html>");
		
		return dadget;
	}

	@Override
	public void draw(DadgetDto dadget, DadgetPanel panel) {
		
		panel.setHeading(((StaticHtmlDadgetDto)dadget).getTitle());
		
		panel.add(utilsService.asIframe(((StaticHtmlDadgetDto)dadget).getData()));
	}

	@Override
	public void displayConfigDialog(final DadgetDto dadget,
			final DadgetConfigureCallback dadgetConfigureCallback) {
		final DwWindow window = new DwWindow();
		window.setSize(800, 640);
		
		final SimpleForm form = SimpleForm.getInlineInstance();
		
		final String titleField = form.addField(String.class, StaticHtmlDadgetDtoPA.INSTANCE.title(), DashboardMessages.INSTANCE.staticHtmlDadgetTitleLabel());
		final String reloadField = form.addField(Long.class, StaticHtmlDadgetDtoPA.INSTANCE.reloadInterval(), DashboardMessages.INSTANCE.reloadIntervalLabel());
		final String heightField = form.addField(Integer.class, StaticHtmlDadgetDtoPA.INSTANCE.height(), DashboardMessages.INSTANCE.heightLabel());
		
		final String codeMirrorField = form.addField(String.class, StaticHtmlDadgetDtoPA.INSTANCE.data(), DashboardMessages.INSTANCE.staticHtmlDadgetHtmlLabel(), new SFFCCodeMirror() {
			
			@Override
			public int getWidth() {
				return -1;
			}
			
			@Override
			public int getHeight() {
				return 380;
			}
			
			@Override
			public String getLanguage() {
				return "htmlmixed";
			}
			@Override
			public boolean lineNumbersVisible() {
				return true;
			}


			@Override
			public ToolBarEnhancer getEnhancer() {
				return null;
		 	}
		});
		
		form.loadFields();
		
		final StaticHtmlDadgetDto sDadget = (StaticHtmlDadgetDto) dadget; 
		if(null != sDadget.getTitle())
			form.setValue(titleField, sDadget.getTitle());
		if(null != sDadget.getData())
			form.setValue(codeMirrorField, sDadget.getData());
		form.setValue(reloadField, dadget.getReloadInterval());
		form.setValue(heightField, dadget.getHeight());
		
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
				
				sDadget.setTitle((String) form.getValue(titleField));
				sDadget.setReloadInterval((long) form.getValue(reloadField));
				sDadget.setHeight((int) form.getValue(heightField));
				sDadget.setData((String) form.getValue(codeMirrorField));
				
				dadgetConfigureCallback.configuringDone();
			}
		});
		
		
		window.show();
	}
	
	@Override
	public Widget getAdminConfigDialog(DadgetDto dadget, SimpleForm wrappingForm) {
		if(dadget.getHeight() < 1)
			dadget.setHeight(250);
		
		final SimpleForm form = SimpleForm.getInlineInstance();
		
		form.addField(String.class, StaticHtmlDadgetDtoPA.INSTANCE.data(), DashboardMessages.INSTANCE.staticHtmlDadgetHtmlLabel(), new SFFCCodeMirror() {
			
			@Override
			public int getWidth() {
				return -1;
			}
			
			@Override
			public int getHeight() {
				return 380;
			}
			
			@Override
			public String getLanguage() {
				return "text/html";
			}
			@Override
			public boolean lineNumbersVisible() {
				return true;
			}

			
			@Override
			public ToolBarEnhancer getEnhancer() {
				return null;
		 	}
		});
		
		form.bind(dadget);
		
		return form;
	}

	@Override
	public boolean supportsDadgetLibrary() {
		return true;
	}
	
	@Override
	public boolean readyToDisplayParameters(DadgetPanel dadgetPanel) {
		return false;
	}


	@Override
	public boolean hasConfigDialog() {
		return true;
	}

	@Override
	public void addTools(DadgetPanel dadgetPanel) {
		// TODO Auto-generated method stub
		
	}

}
