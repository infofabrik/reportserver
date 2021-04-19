package net.datenwerke.rs.core.client.reportmanager.helper.reportselector;

import java.text.ParseException;

import net.datenwerke.gxtdto.client.baseex.widget.form.DwTriggerField;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.ReportSelectionDialogEventHandler;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.RepositoryProviderConfig;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportSelectionRepositoryProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.dom.client.NativeEvent;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.menu.Menu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;

public class ReportSelectionField extends DwTriggerField<ReportContainerDto> {

	@Inject
	public static Provider<ReportSelectionDialog> dialogProvider;
	private ReportSelectionDialog dialog;
	private RepositoryProviderConfig[] repositoryConfigs;
	
	public ReportSelectionField() {
		super(new TriggerFieldCell<ReportContainerDto>(), new PropertyEditor<ReportContainerDto>() {
			@Override
			public String render(ReportContainerDto object) {
				return object.toDisplayTitle();
			}

			@Override
			public ReportContainerDto parse(CharSequence text)
					throws ParseException {
				return null;
			}
		});
		
		/* build ui */
		initializeUI();
	}

	protected void initializeUI() {
		/* configure triggerField */
		setEditable(false);
		setTriggerIcon(BaseIcon.HAND_POINTER_O);
		
		/* listener */
		addTriggerClickHandler(new TriggerClickHandler() {
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				triggerClicked();
			}
		});
	}

	protected void triggerClicked() {
		if(null == dialog){
			dialog = dialogProvider.get();
			dialog.initSubmitButton();
			
			dialog.initRepositories(null, repositoryConfigs);
			
			dialog.setEventHandler(new ReportSelectionDialogEventHandler() {
				
				@Override
				public boolean handleSubmit(ReportContainerDto container) {
					setValue(container, true);
					return true;
				}
				
				@Override
				public void handleDoubleClick(ReportContainerDto dto,
						ReportSelectionRepositoryProviderHook hooker, NativeEvent event,
						Object... info) {
					setValue(dto, true);
					dialog.hide();
				}

				@Override
				public Menu getContextMenuFor(ReportContainerDto dto,
						ReportSelectionRepositoryProviderHook hooker, Object... info) {
					return null;
				}
			});
		}
		
		dialog.show();
	}

	public void setRepositoryConfigs(
			RepositoryProviderConfig... repositoryConfigs) {
		this.repositoryConfigs = repositoryConfigs;
	}

}
