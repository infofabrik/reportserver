package net.datenwerke.rs.core.client.reportmanager.helper.reportselector;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.RepositoryProviderConfig;
import net.datenwerke.rs.core.client.reportmanager.hookers.ReportCatalogOnDemandRepositoryProvider;

public class ReportSelectorSimpleFormProvider extends FormFieldProviderHookImpl {

	
	
	@Override
	public boolean doConsumes(Class<?> type,
			SimpleFormFieldConfiguration... configs) {
		return type == ReportSelectionDialog.class;
	}

	@Override
	public Widget createFormField() {
		ReportSelectionField field = new ReportSelectionField();
	
		final SFFCReportSelection config = getConfig();
		if(null != config){
			Collection<RepositoryProviderConfig> configs = new ArrayList<RepositoryProviderConfig>();
			if(null != config.getRepositoryConfigs())
				configs.addAll(config.getRepositoryConfigs());
			configs.add(new ReportCatalogOnDemandRepositoryProvider.Config() {
            @Override
            public boolean includeVariants() {
               return config.showVariantsInCatalog();
            }
            
            @Override
            public boolean showCatalog() {
               return config.showCatalog();
            }

            @Override
            public boolean filterOnSchedulableReports() {
               return false;
            }

            @Override
            public boolean showEntriesWithUnaccessibleHistoryPath() {
               return true;
            }

            @Override
            public boolean filterOnTeamSpaceImportableReports() {
               return false;
            }
         });
			field.setRepositoryConfigs(configs.toArray(new RepositoryProviderConfig[]{}));
		}
		
		field.addValueChangeHandler(new ValueChangeHandler<ReportContainerDto>() {
			@Override
			public void onValueChange(ValueChangeEvent<ReportContainerDto> event) {
				ValueChangeEvent.fire(ReportSelectorSimpleFormProvider.this, event.getValue());
			}
		});
		
		return field;
	}
	
	
	private SFFCReportSelection getConfig() {
		for(SimpleFormFieldConfiguration conf : configs)
			if(conf instanceof SFFCReportSelection)
				return (SFFCReportSelection) conf;
		return null;
	}

	public Object getValue(Widget field){
		return ((ReportSelectionField)field).getValue();
	}
}
