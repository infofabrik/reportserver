package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.selection.TreeGridSelectionPopup;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.wizard.Validatable;
import net.datenwerke.gxtdto.client.forms.wizard.WizardDialog;
import net.datenwerke.gxtdto.client.forms.wizard.WizardResizer;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleConfigWizardPageProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.security.SchedulingAdminViewGenericTargetIdentifier;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUserPA;
import net.datenwerke.security.ext.client.usermanager.UserManagerDao;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIService;
import net.datenwerke.security.ext.client.usermanager.helper.simpleform.SFFCUserSelector;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeUsers;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class SchedulerBaseConfigurationForm extends DwContentPanel implements Validatable, WizardResizer {

	private final ReportExporterUIService reportExporterService;
	private final UserManagerUIService userManagerService;
	private final UserManagerDao userManagerDao;
	private final LoginService loginService;
	private final SecurityUIService securityService;
	private final SchedulerDao schedulerDao;
	
	private DateField date;
	private TimeField time;
	private TextField subject;
	private TextArea message;
	
	private SimpleForm executorForm;
	private String executorKey;
	
	private final UITree basicTreeForUserSelection;
	
	/* ie properties */
	private ListStore<StrippedDownUser> strippedUserStore;
	private ListStore<StrippedDownUser> selectedRecipientStore;
	private static ListStore<StrippedDownUser> selectedOwnerStore;
	
	@Inject
	public SchedulerBaseConfigurationForm(
		LoginService loginService,
		ReportExporterUIService reportExporterService,	
		UserManagerUIService userManagerService,
		UserManagerDao userManagerDao,
		SecurityUIService securityService,
		SchedulerDao schedulerDao,
		@UserManagerTreeUsers UITree basicTreeForUserSelection
		){
		
		/* store objects */
		this.loginService = loginService;
		this.reportExporterService = reportExporterService;
		this.userManagerService = userManagerService;
		this.userManagerDao = userManagerDao;
		this.schedulerDao = schedulerDao;
		this.securityService = securityService;
		this.basicTreeForUserSelection = basicTreeForUserSelection;
	}

	
	public void configureForm(final ReportDto report, final ReportScheduleDefinition definition, final WizardDialog wizard, final List<ScheduleConfigWizardPageProviderHook> addPages) {
		/* build form */
		setHeaderVisible(false);
		
		VerticalLayoutContainer fieldWrapper = new VerticalLayoutContainer();
		add(fieldWrapper, new MarginData(10));
		
		/* executor grid */
		Widget executors = getExecutorsSelection(definition);
		FieldLabel executorsLabel = new FieldLabel(executors, "executors");
		executorsLabel.setLabelAlign(LabelAlign.LEFT);
		fieldWrapper.add(executors, new VerticalLayoutData(1,80, new Margins(10,0,0,0)));
		
		/* owners grid */
		Component owners = getOwnersSelection(definition, report);
		FieldLabel ownersLabel = new FieldLabel(owners, SchedulerMessages.INSTANCE.owners());
		ownersLabel.setLabelAlign(LabelAlign.LEFT);
		fieldWrapper.add(ownersLabel, new VerticalLayoutData(1,150, new Margins(10,0,0,0)));
		
		/* recipients grid */
		Component recipients = getRecipientsSelection(definition);
		FieldLabel rcptLabel = new FieldLabel(recipients, SchedulerMessages.INSTANCE.recipients());
		rcptLabel.setLabelAlign(LabelAlign.LEFT);
		fieldWrapper.add(rcptLabel, new VerticalLayoutData(1,190, new Margins(10,0,5,0)));
		
		/* advanced options */
		final CheckBox advanced = new CheckBox();
		advanced.setToolTip(SchedulerMessages.INSTANCE.showAdvancedOptionsTooltip());
		advanced.setBoxLabel(SchedulerMessages.INSTANCE.showAdvancedOptionsTooltip());
		FieldLabel advLabel = new FieldLabel(advanced, SchedulerMessages.INSTANCE.showAdvancedOptions());
		advLabel.setLabelAlign(LabelAlign.LEFT);
		fieldWrapper.add(advLabel);

		Collections.reverse(addPages);
		advanced.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				showAdvanced(event.getValue(), report, definition, wizard, addPages);
			}
		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				for(ScheduleConfigWizardPageProviderHook pageProvider : addPages){
					if(pageProvider.isAdvanced()){
						if(pageProvider.isConfigured(report, definition)){
							advanced.setValue(true, true);
							showAdvanced(true, report, definition, wizard, addPages);
							break;
						}
					}
				}
			}
		});
	}
	
	protected void showAdvanced(Boolean show, ReportDto report, ReportScheduleDefinition definition, WizardDialog wizard, List<ScheduleConfigWizardPageProviderHook> addPages) {
		if(show){
			for(ScheduleConfigWizardPageProviderHook pageProvider : addPages){
				if(pageProvider.isAdvanced())
					wizard.addPage(wizard.getPageCount()-1, pageProvider.getPage(report, definition));
			}
		} else {
			for(ScheduleConfigWizardPageProviderHook pageProvider : addPages){
				if(pageProvider.isAdvanced())
					wizard.removePage(pageProvider.getPage(report, definition));
			}
		}
	}


	protected Component getOwnersSelection(final ReportScheduleDefinition definition, final ReportDto report) {
		if(null == strippedUserStore)
			strippedUserStore = userManagerService.getStrippedUserStore();
		
		selectedOwnerStore = new ListStore<StrippedDownUser>(StrippedDownUserPA.INSTANCE.dtoId());
		
		Map<ValueProvider<StrippedDownUser, String>, String> displayProperties = new LinkedHashMap<ValueProvider<StrippedDownUser, String>, String>();
		
		displayProperties.put(StrippedDownUserPA.INSTANCE.firstname(), SchedulerMessages.INSTANCE.firstName());
		displayProperties.put(StrippedDownUserPA.INSTANCE.lastname(), SchedulerMessages.INSTANCE.lastName());
		displayProperties.put(StrippedDownUserPA.INSTANCE.parentOu(), SchedulerMessages.INSTANCE.ou());
		
		final TreeGridSelectionPopup<StrippedDownUser> ownerGrid = new TreeGridSelectionPopup<StrippedDownUser>(displayProperties, 90, selectedOwnerStore, basicTreeForUserSelection, UserDto.class) {
			@Override
			protected void validateSelectedItems(final List<AbstractNodeDto> selectedItems,
					final AsyncCallback<Boolean> itemsValidatedCallback) {
				List<Long> ownerIds = new ArrayList<>();
				for (AbstractNodeDto selectedUser: selectedItems)
					ownerIds.add(selectedUser.getId());
				
				final List<RightDto> rights = new ArrayList<>();
				rights.add(new ExecuteDto());
				
				schedulerDao.assertOwnersHaveReportRights(ownerIds, report, rights, new RsAsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						itemsValidatedCallback.onSuccess(true);
					}
				});
				
			}
		};
		ownerGrid.setHeight(150);
		ownerGrid.getView().setShowDirtyCells(false);
		ownerGrid.setBorders(true);
		
		if(null != definition && null != definition.getOwners() && ! definition.getOwners().isEmpty()){
			ownerGrid.mask(BaseMessages.INSTANCE.loadingMsg());
			
			userManagerDao.fillStrippedDownUsersInfo(definition.getOwners(), new RsAsyncCallback<List<StrippedDownUser>>(){
				@Override
				public void onSuccess(List<StrippedDownUser> result) {
					ownerGrid.unmask();
					selectedOwnerStore.addAll(result);
				}
			});
			
		} else {
			UserDto user = loginService.getCurrentUser();
			StrippedDownUser sUser = StrippedDownUser.fromUser(user);
			selectedOwnerStore.add(sUser);
		}
		
		return ownerGrid;
	}
	
	protected Widget getExecutorsSelection(final ReportScheduleDefinition definition) {
		executorForm = SimpleForm.getInlineInstance();
		executorKey = executorForm.addField(UserDto.class, SchedulerMessages.INSTANCE.executor(), new SFFCUserSelector() {
			@Override
			public UserDto getUser() {
				return (UserDto) executorForm.getValue(executorKey);
			}
		});
		
		executorForm.loadFields();
		if(null != definition && null != definition.getExecutor()){
			executorForm.setValue(executorKey, definition.getExecutor());
		} else {
			UserDto user = loginService.getCurrentUser();
			executorForm.setValue(executorKey, user);
		}
		
		if(! securityService.hasRight(SchedulingAdminViewGenericTargetIdentifier.class, ExecuteDto.class))
			((Field<?>)executorForm.getField(executorKey)).disable();
		else
			((Field<?>)executorForm.getField(executorKey)).enable();
		
		return executorForm;
	}
	
	protected Component getRecipientsSelection(ReportScheduleDefinition definition) {
		if(null == strippedUserStore)
			strippedUserStore = userManagerService.getStrippedUserStore();
		
		selectedRecipientStore = new ListStore<StrippedDownUser>(StrippedDownUserPA.INSTANCE.dtoId());
		
		Map<ValueProvider<StrippedDownUser, String>, String> displayProperties = new LinkedHashMap<ValueProvider<StrippedDownUser, String>, String>();
		
		displayProperties.put(StrippedDownUserPA.INSTANCE.firstname(), SchedulerMessages.INSTANCE.firstName());
		displayProperties.put(StrippedDownUserPA.INSTANCE.lastname(), SchedulerMessages.INSTANCE.lastName());
		displayProperties.put(StrippedDownUserPA.INSTANCE.parentOu(), SchedulerMessages.INSTANCE.ou());
		
		final TreeGridSelectionPopup<StrippedDownUser> recipientsGrid = new TreeGridSelectionPopup<>(displayProperties, 90, selectedRecipientStore, basicTreeForUserSelection, UserDto.class);
		recipientsGrid.setHeight(175);
		recipientsGrid.getView().setShowDirtyCells(false);
		recipientsGrid.getView().setEmptyText(SchedulerMessages.INSTANCE.noRecipientSelected());
		recipientsGrid.setBorders(true);
		
		if(null != definition && null != definition.getRecipients() && ! definition.getRecipients().isEmpty()){
			recipientsGrid.mask(BaseMessages.INSTANCE.loadingMsg());
			
			userManagerDao.getStrippedDownUsers(definition.getRecipients(), new RsAsyncCallback<List<StrippedDownUser>>(){
				@Override
				public void onSuccess(List<StrippedDownUser> result) {
					recipientsGrid.unmask();
					selectedRecipientStore.addAll(result);
				}
			});
		} else {
			UserDto user = loginService.getCurrentUser();
			StrippedDownUser sUser = StrippedDownUser.fromUser(user);
			selectedRecipientStore.add(sUser);
		}
		
		return recipientsGrid;
	}
	
	public ListStore<StrippedDownUser> getSelectedRecipientsStore(){
		return selectedRecipientStore;
	}
	
	public ListStore<StrippedDownUser> getSelectedOwnerStore(){
		return selectedOwnerStore;
	}

	public DateField getDate() {
		return date;
	}

	public TimeField getTime() {
		return time;
	}

	public TextField getSubject() {
		return subject;
	}

	public TextArea getMessage() {
		return message;
	}
	
	@Override
	public boolean isValid() {
		if (null == getExecutor()) {
			new DwAlertMessageBox(SchedulerMessages.INSTANCE.executorConfig(), SchedulerMessages.INSTANCE.executorConfigError()).show();
			return false;
		}
		
		if (selectedOwnerStore.size() == 0) {
			new DwAlertMessageBox(SchedulerMessages.INSTANCE.ownerConfig(), SchedulerMessages.INSTANCE.ownerConfigError()).show();
			return false;
		}
		
		if(selectedRecipientStore.size() == 0) {
			new DwAlertMessageBox(SchedulerMessages.INSTANCE.recipientConfig(), SchedulerMessages.INSTANCE.recipientConfigError()).show();
			return false;
		}
		
		StrippedDownUser strippedDownExecutor = StrippedDownUser.fromUser(getExecutor());
		if (null == selectedOwnerStore.findModel(strippedDownExecutor)) {
			new DwAlertMessageBox(SchedulerMessages.INSTANCE.executorConfig(), SchedulerMessages.INSTANCE.errorExecutorNotOwner() 
					+ " " + strippedDownExecutor).show();
			return false;
		}
		
		return true;
	}


	public UserDto getExecutor() {
		return (UserDto) executorForm.getValue(executorKey);
	}


	@Override
	public int getPageHeight() {
		return 580;
	}
	
}
