package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCSpace;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticLabel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.Separator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.GeneralInfoDao;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;

/**
 * 
 *
 */
@Singleton
public class GeneralInfoPanel extends DwContentPanel {

	private final GeneralInfoDao generalInfoDao;
	
	private VerticalLayoutContainer wrapper;

	@Inject
	public GeneralInfoPanel(
		GeneralInfoDao licenseDao
		){
		
		this.generalInfoDao = licenseDao;
		
		/* initialize ui */
		initializeUI();
	}

	private void initializeUI() {
		setHeading(SystemConsoleMessages.INSTANCE.generalInfo());
		addStyleName("rs-generalinfo");
		
		wrapper = new VerticalLayoutContainer();
		wrapper.setScrollMode(ScrollMode.AUTOY);
		add(wrapper);
		
		updateView();
	}
	

	protected void updateView() {
		mask(BaseMessages.INSTANCE.loadingMsg());
		
		wrapper.clear();
		
		generalInfoDao.loadGeneralInfo(new RsAsyncCallback<GeneralInfoDto>(){
			@Override
			public void onSuccess(GeneralInfoDto result) {
				super.onSuccess(result);
				init(result);
				unmask();
			}
			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
				unmask();
			}
		});
	}

	protected void init(final GeneralInfoDto result) {
		final SimpleForm form = SimpleForm.getNewInstance();
		form.setHeading(SystemConsoleMessages.INSTANCE.generalInfo());
		form.setLabelAlign(LabelAlign.LEFT);
		
		form.setLabelWidth(150);
		
		/* version */
		form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.versionLabel(), new SFFCStaticLabel() {
			@Override
			public String getLabel() {
				return result.getRsVersion();
			}
		});
		
		/* Java version */
		if(null != result.getJavaVersion()){
			form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.javaVersionLabel(), new SFFCStaticLabel() {
				@Override
				public String getLabel() {
					return result.getJavaVersion();
				}
			});
		}
		
		/* Vm Arguments */
		if(null != result.getVmArguments()){
			form.addField(StaticLabel.class, "JVM Args", new SFFCStaticLabel() {
				@Override
				public String getLabel() {
					return result.getVmArguments();
				}
			});
		}

		/* Application server */
		if(null != result.getApplicationServer()){
			form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.applicationServerLabel(), new SFFCStaticLabel() {
				@Override
				public String getLabel() {
					return result.getApplicationServer();
				}
			});
		}
		
		/* Operation system */
		if(null != result.getOsVersion()){
			form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.operationSystemLabel(), new SFFCStaticLabel() {
				@Override
				public String getLabel() {
					return result.getOsVersion();
				}
			});
		}
		
		form.addField(Separator.class, new SFFCSpace());
		
		/* Browser name */
		if(null != result.getBrowserName()){
			form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.browserNameLabel(), new SFFCStaticLabel() {
				@Override
				public String getLabel() {
					return result.getBrowserName();
				}
			});
		}

		/* Browser version */
		if(null != result.getBrowserVersion()){
			form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.browserVersionLabel(), new SFFCStaticLabel() {
				@Override
				public String getLabel() {
					return result.getBrowserVersion();
				}
			});
		}
		
		form.loadFields();

		wrapper.add(form, new VerticalLayoutData(1,-1, new Margins(10)));
		
		form.clearButtonBar();
		
		Scheduler.get().scheduleDeferred(forceLayoutCommand);
	}

}
