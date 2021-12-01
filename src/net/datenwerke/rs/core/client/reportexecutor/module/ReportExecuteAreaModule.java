package net.datenwerke.rs.core.client.reportexecutor.module;

import javax.inject.Provider;

import net.datenwerke.gf.client.homepage.modules.ClientTempModuleImpl;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;

public class ReportExecuteAreaModule extends ClientTempModuleImpl {

	private final Provider<ReportExecuteAreaMainWidget> mainWidgetProvider;
	private ReportExecuteAreaMainWidget mainWidget;
	
	@Inject
	public ReportExecuteAreaModule(
			Provider<ReportExecuteAreaMainWidget> mainWidgetProvider) {
		this.mainWidgetProvider = mainWidgetProvider;
	}

	@Override
	public String getModuleName() {
		return ReportexecutorMessages.INSTANCE.executeAreaModule();
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.REPORT.toImageResource();
	}

	@Override
	public ReportExecuteAreaMainWidget getMainWidget() {
		if(null == mainWidget){
			mainWidget = mainWidgetProvider.get();
			mainWidget.setModule(this);
		}
		return mainWidget;
	}

	public void addExecutionComponent(ReportDto report, Component displayComponent, String urlParameters) {
		getMainWidget().addExecutionComponent(report, displayComponent, urlParameters);
	}

	public void closeCurrent() {
		getMainWidget().closeCurrent();
	}
	
	public void removeModule(){
		viewport.removeTempModule(this);
	}
	
	@Override
	public void onMouseOver(MouseEvent be) {

	}

	public void markCurrentChanged() {
		getMainWidget().markCurrentChanged();
	}

	public void forceCloseCurrent() {
		getMainWidget().forceCloseCurrent();
	}
	
	@Override
	public boolean isRecyclable() {
		return true;
	}
}
