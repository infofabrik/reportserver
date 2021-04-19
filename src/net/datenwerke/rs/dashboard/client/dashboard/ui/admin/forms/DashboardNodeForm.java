package net.datenwerke.rs.dashboard.client.dashboard.ui.admin.forms;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormSubmissionCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCCustomComponentImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardDao;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardNodeDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetCatalogFactory;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardContainer;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardView;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardView.EditSuccessCallback;
import net.datenwerke.rs.dashboard.client.dashboard.ui.helper.SFFCDashboardLayout;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DashboardNodeForm extends SimpleFormView {

	class MyDashboardContainer implements DashboardContainer {
		
		private DwContentPanel viewWrapper;
		private Provider<DashboardView> viewProvider;
		private DashboardNodeDto dashboardNode;

		public MyDashboardContainer(
			DwContentPanel wrapperPanel, 
			DwContentPanel viewWrapper,
			Provider<DashboardView> viewProvider, DashboardNodeDto dashboardNode) {
			this.viewWrapper = viewWrapper;
			this.viewProvider = viewProvider;
			this.dashboardNode = dashboardNode;
			
			wrapperPanel.addClassName("rs-dashboardnode");
		}
		
		@Override
		public void remove(DashboardDto dashboard, DadgetDto dadget) {
			dashboard.getDadgets().remove(dadget);
			dashboard.setDadgets(dashboard.getDadgets());
		}
		
		@Override
		public void resizeDadget(DashboardDto dashboard, DadgetDto dadget,
				int offsetHeight) {
			dadget.setHeight(offsetHeight);	
		}
		
		@Override
		public void reload(DashboardDto dashboard) {
			viewWrapper.clear();
			
			DashboardView dashboardView = viewProvider.get();
			viewWrapper.add(dashboardView);
			dashboardView.init(this, dashboard);
			
			viewWrapper.forceLayout();
		}
		
		@Override
		public void addDadget(DashboardDto dashboard, DadgetDto dadget) {
			dashboard.getDadgets().add(dadget);
			dashboard.setDadgets(dashboard.getDadgets());
			reload(dashboard);
		}
		
		@Override
		public void dadgetConfigured(final DashboardDto dashboard, final DadgetDto dadget, ConfigType type, final EditSuccessCallback callback){
//			dashboardDao.updateDadget(dadget, new RsAsyncCallback<DadgetDto>(){
//				@Override
//				public void onSuccess(DadgetDto result) {
//					((DashboardDtoDec)dashboard).updateDadget(dadget, result);
//				    callback.onSuccess(dashboard, result);
//				}
//			});
			/* mark dashboard as changed */
			dashboardNode.setDashboard(dashboard);

		    callback.onSuccess(dashboard, dadget);
		    
		    if(type == ConfigType.CONFIG && (dadget instanceof ReportDadgetDto || dadget instanceof LibraryDadgetDto)){
				ConfirmMessageBox cmb = new DwConfirmMessageBox(DashboardMessages.INSTANCE.storeDashboardPromptTitle(), DashboardMessages.INSTANCE.storeToConfigure());
				cmb.addDialogHideHandler(new DialogHideHandler() {
					
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton() == PredefinedButton.YES){
							onSubmit(new SimpleFormSubmissionCallback(null) {
								@Override
								public void formSubmitted() {
								}
							});
						}
					}
				});
				cmb.show();
		    }
		}
		@Override
		public void edited(DashboardDto dashboard) {
		}
	};
	
	protected void onSuccessfulSubmit() {
		mask(BaseMessages.INSTANCE.storingMsg());
		reloadNodeAndView();
	}
	
	private final ToolbarService toolbarService;
	private final Provider<DashboardView> viewProvider;
	private final DadgetCatalogFactory catalogFactory;
	private final DashboardDao dashboardDao;
	
	@Inject
	public DashboardNodeForm(
		ToolbarService toolbarService,
		Provider<DashboardView> viewProvider,
		DashboardDao dashboardDao,
		DadgetCatalogFactory catalogFactory) {
		super();
		this.toolbarService = toolbarService;
		this.viewProvider = viewProvider;
		this.dashboardDao = dashboardDao;
		this.catalogFactory = catalogFactory;
	}


	@Override
	public void configureSimpleForm(final SimpleForm form) {
		form.setHeading(DashboardMessages.INSTANCE.editDashboardNode() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));
		
		form.addField(String.class, DashboardNodeDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName()); 
		
		form.addField(String.class, DashboardNodeDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());
		
		/* create dashboard */
		if(null == ((DashboardNodeDto)getSelectedNode()).getDashboard())
			((DashboardNodeDto)getSelectedNode()).setDashboard(new DashboardDtoDec());
		final DashboardDto dashboard = ((DashboardNodeDto)getSelectedNode()).getDashboard();
		
		/* mark dashboard as changed */
		((DashboardNodeDto)getSelectedNode()).setDashboard(dashboard);
		
		/* init dashboard container */
		final DwContentPanel wrapperPanel = DwContentPanel.newInlineInstance();
		wrapperPanel.setBorders(true);
		wrapperPanel.setBodyBorder(true);
		DwContentPanel viewWrapper = DwContentPanel.newInlineInstance();
		final MyDashboardContainer container = new MyDashboardContainer(wrapperPanel, viewWrapper, viewProvider, (DashboardNodeDto) getSelectedNode());
		
		form.addField(Boolean.class, new ValueProvider<DashboardNodeDto, Boolean>(){
			@Override
			public Boolean getValue(DashboardNodeDto object) {
				return object.getDashboard().isSinglePage();
			}

			@Override
			public void setValue(DashboardNodeDto object, Boolean value) {
				object.getDashboard().setSinglePage(value);
				container.reload(dashboard);
			}

			@Override
			public String getPath() {
				return "__singlePage";
			}
			
		}, DashboardMessages.INSTANCE.singlePageLabel());
		
		form.setFieldWidth(400);
		form.addField(List.class, new ValueProvider<DashboardNodeDto, LayoutTypeDto>(){
			@Override
			public LayoutTypeDto getValue(DashboardNodeDto object) {
				return object.getDashboard().getLayout();
			}

			@Override
			public void setValue(DashboardNodeDto object, LayoutTypeDto value) {
				object.getDashboard().setLayout(value);
				container.reload(dashboard);
			}

			@Override
			public String getPath() {
				return "__layout";
			}
			
		}, DashboardMessages.INSTANCE.layoutLabel(), new SFFCDashboardLayout());
		
	
		
		form.setFieldWidth(1);
		
		/* add dashboard to form */
		FieldLabel labeledWrapper = new FieldLabel(wrapperPanel, DashboardMessages.INSTANCE.dashboard());
		labeledWrapper.setLabelAlign(LabelAlign.TOP);
		form.addField(CustomComponent.class, DashboardMessages.INSTANCE.dashboard(), new SFFCCustomComponentImpl(labeledWrapper));
		
		/* container for view */
		VerticalLayoutContainer vContainer = new VerticalLayoutContainer();
		vContainer.setScrollMode(ScrollMode.AUTOY);
		wrapperPanel.add(vContainer, new MarginData(10));
		
		DwToolBar toolbar = new DwToolBar();
		
		vContainer.add(toolbar, new VerticalLayoutData(1,-1));
		vContainer.add(viewWrapper, new VerticalLayoutData(1,700));
		
		/* configure view */
		DashboardView dashboardView = viewProvider.get();
		viewWrapper.add(dashboardView);
		dashboardView.init(container, dashboard);
		
		/* configure toolbar */
		toolbar.add(new FillToolItem());
		
		/* add dadget */
		DwTextButton addDadgetBtn = toolbarService.createSmallButtonLeft(DashboardMessages.INSTANCE.addDadget(), BaseIcon.DADGET);
		toolbar.add(addDadgetBtn);
		
		addDadgetBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				catalogFactory.create(dashboard, container).show();
			}
			
		});
	}
	

	
}