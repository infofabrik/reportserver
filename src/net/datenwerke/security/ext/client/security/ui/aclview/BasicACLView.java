package net.datenwerke.security.ext.client.security.ui.aclview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService;
import net.datenwerke.gxtdto.client.dtomanager.ClientDtoManagerService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldChanged;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCSeparator.TYPE;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCSeparatorImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticRadioList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.Separator;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.dto.AccessTypeDto;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.security.dto.HierarchicalAceDto;
import net.datenwerke.security.client.security.dto.InheritanceTypeDto;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.security.dto.SecureeDto;
import net.datenwerke.security.client.security.dto.SecurityViewInformation;
import net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec;
import net.datenwerke.security.client.security.dto.decorator.AceDtoDec;
import net.datenwerke.security.client.security.dto.pa.AceDtoPA;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.ext.client.security.SecurityDao;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIService;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeBasicSingleton;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 * @param <T>
 */
public abstract class BasicACLView<T> extends VerticalLayoutContainer {

	@Inject
	protected ClientDtoManagerService dtoService;
	
	@Inject
	protected DtoInformationService dtoInfoService;
	
	@Inject
	protected SecurityDao securityDao;
	
	@Inject
	protected ToolbarService toolbarService;
	
	@Inject
	protected UserManagerUIService userManagerService;
	
	protected final UITree userManagerTree;
	
	protected ListStore<AceDto> aceStore;

	protected T target;

	private DwContentPanel panel;
	
	private static AceDtoPA acePa = GWT.create(AceDtoPA.class);
	
	@Inject
	public BasicACLView(
		@UserManagerTreeBasicSingleton UITree userManagerTree
		){
		
		/* store objects */
		this.userManagerTree = userManagerTree;
		
		initializeUI();
	}

	private void initializeUI() {
		disable();
		
		/* add ace grid to wrapper */
		panel = new DwContentPanel();
	}
	
	public void initialize(T target){
		this.target = target;
		
		
		doInitialize(new RsAsyncCallback<SecurityViewInformation>() {
			@Override
			public void onSuccess(SecurityViewInformation information){
				renderContents(information);
			}
		});
	}
	
	protected abstract void doInitialize(AsyncCallback<SecurityViewInformation> callback);

	
	public void addInfoText(String text){
		ToolButton toolButton = new ToolButton(ToolButton.QUESTION);
		toolButton.setToolTip(text);
		panel.addTool(toolButton);
	}

	protected void renderContents(SecurityViewInformation information) {
		panel.clear();
		panel.setLightHeader();
		panel.setHeading(SecurityMessages.INSTANCE.accessControlEntries());
		add(panel, new VerticalLayoutData(1,-1));
		
		panel.add(createACEGrid(information));
		
		/* add inherited aces */
		if(isIncludeInheritedACEs()){
			DwContentPanel inheritedPanel = new DwContentPanel();
			inheritedPanel.setLightHeader();
			inheritedPanel.setHeading(SecurityMessages.INSTANCE.inheritedACEs());
			add(inheritedPanel, new VerticalLayoutData(1,-1, new Margins(25,0,30,0)));
			
			if(null != information.getInheritedAces() && ! information.getInheritedAces().isEmpty()){
				inheritedPanel.add(createInheritedACEGrid(information));
			} else 
				inheritedPanel.add(createEmptyInheritedACEs());
		}

		/* redo layout */
		forceLayout();
		
		/* activate panel */
		enable();
	}

	/**
	 * May be overriden to change this dialogs default behaviour.
	 */
	protected boolean isIncludeInheritedACEs() {
		return true;
	}

	private Widget createEmptyInheritedACEs() {
		return SeparatorTextLabel.createText(SecurityMessages.INSTANCE.noInheritedACEs()); 
	}

	private Widget createInheritedACEGrid(final SecurityViewInformation information) {
		/* create store for aces */
		ListStore<AceDto> aceStore = new ListStore<AceDto>(acePa.dtoId());
		for(AceDto ace : information.getInheritedAces())
			aceStore.add((AceDtoDec) ace);
		
		/* configure columns */
		List<ColumnConfig<AceDto,?>> columns = new ArrayList<ColumnConfig<AceDto,?>>();
		
		/* create folk column */
		ColumnConfig<AceDto,AbstractUserManagerNodeDto> ccFolk  = createFolkColumnConfig();
		columns.add(ccFolk);
		
		/* create column for access type */
		ColumnConfig<AceDto, AceDto>  ccAccessType = createAccessTypeColumnConfig();
		columns.add(ccAccessType);
		
		/* create Column per securee */
		addRightsColumnConfigs(columns, information);
		
		ColumnConfig<AceDto, String> definedOn = new ColumnConfig<AceDto, String>(new ValueProvider<AceDto, String>() {

			@Override
			public String getValue(AceDto model) {
				AbstractNodeDto node = information.getReferencesToInheritedNodes().get(model);
				if(null == node)
					return "";
				return node.toDisplayTitle();
			}

			@Override
			public void setValue(AceDto object, String value) {
			}

			@Override
			public String getPath() {
				return "__dependsOn";
			}
		}, 150, SecurityMessages.INSTANCE.dependsOn());
		columns.add(definedOn);
		
		/* create grid */
		Grid<AceDto> aceGrid = new Grid<AceDto>(aceStore, new ColumnModel<AceDto>(columns));
		aceGrid.getView().setSortingEnabled(false);
		aceGrid.disable();
		
		return aceGrid;
	}

	private Widget createACEGrid(final SecurityViewInformation information) {
		/* create store for aces */
		aceStore = new ListStore<AceDto>(acePa.dtoId());
		for(AceDto ace : information.getAces())
			aceStore.add((AceDtoDec) ace);
		
		/* configure columns */
		List<ColumnConfig<AceDto,?>> columns = new ArrayList<ColumnConfig<AceDto,?>>();
		
		/* create folk column */
		ColumnConfig<AceDto,AbstractUserManagerNodeDto> ccFolk  = createFolkColumnConfig();
		columns.add(ccFolk);
		
		/* create column for access type */
		ColumnConfig<AceDto, AceDto> ccAccessType = createAccessTypeColumnConfig();
		columns.add(ccAccessType);
		
		/* create column for inheritance type */
		if(isIncludeInheritedACEs()){
			ColumnConfig<AceDto, AceDto> ccInheritanceType = createInheritanceColumnConfig();
			columns.add(ccInheritanceType);
		}
		
		/* create Column per securee */
		addRightsColumnConfigs(columns, information);
		
		/* create grid */
		Grid<AceDto> aceGrid = new Grid<AceDto>(aceStore, new ColumnModel<AceDto>(columns));
		aceGrid.getView().setSortingEnabled(false);
		
		
		/* add double click listener */
		aceGrid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				AceDto selectedACE = aceStore.get(event.getRowIndex());
				displayEditDialog(selectedACE, information);
			}
		});
		
		/* make it draggable */
		new GridDragSource<AceDto>(aceGrid);
		final GridDropTarget<AceDto> target = new GridDropTarget<AceDto>(aceGrid){

			@Override
			protected void onDragDrop(DndDropEvent e) {
				super.onDragDrop(e);
				  
				List<?> list = (List<?>) e.getData();
				if(list.size() > 0){
					AceDto ace = (AceDto) list.get(0);
					int index = aceStore.indexOf(ace);
					aceMoved(ace, index);
				}
			}
			
		};
		target.setFeedback(Feedback.INSERT);
		target.setAllowSelfAsSource(true);
		
		/* build wrapper for toolbar */
		VerticalLayoutContainer nsContainer = new VerticalLayoutContainer();
		
		/* create toolbar */
		DwToolBar toolbar = new DwToolBar();
		nsContainer.add(toolbar, new VerticalLayoutData(1,-1));
		nsContainer.add(aceGrid, new VerticalLayoutData(1,-1));
		
		/* add toolbar capabilities */
		addAddRemoveButtons(aceGrid, toolbar);
		
		return nsContainer;
	}
	


	private void addAddRemoveButtons(final Grid<AceDto> grid, DwToolBar toolbar) {
		/* remove buttons */
		DwTextButton addBtn = toolbarService.createSmallButtonLeft(BaseMessages.INSTANCE.add(), BaseIcon.LOCK_ADD);
		addBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				addAce();
			}
		});
		
		toolbar.add(addBtn);
		
		DwSplitButton removeButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
		removeButton.setIcon(BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<AceDto> selectedItems = grid.getSelectionModel().getSelectedItems();
				removeACEs(selectedItems);
			}
		});
		toolbar.add(removeButton);
		
		MenuItem removeAllButton = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ConfirmMessageBox cmb = new DwConfirmMessageBox(SecurityMessages.INSTANCE.confirmDeleteHeader(), SecurityMessages.INSTANCE.confirmDeleteText());
				cmb.addDialogHideHandler(new DialogHideHandler() {
					
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton() == PredefinedButton.YES)
							removeACEs(new ArrayList(grid.getStore().getAll()));
					}
				});
				
				cmb.show();
			}
		});
		
		Menu remMenu = new DwMenu();
		remMenu.add(removeAllButton);
		removeButton.setMenu(remMenu);
	}


	private void addRightsColumnConfigs(List<ColumnConfig<AceDto,?>> columns,
			SecurityViewInformation information) {
		for(final SecureeDto securee : information.getAvailableSecurees()){
			
			ColumnConfig<AceDto,String> ccSecuree = new ColumnConfig<AceDto,String>(new ValueProvider<AceDto,String>() {

				@Override
				public String getValue(AceDto model) {
					String rightsString = ""; //$NON-NLS-1$
					
					AceAccessMapDtoDec accessMap = (AceAccessMapDtoDec) ((AceDtoDec) model).getAccessMap(securee.getSecureeId());
					for(RightDto right : securee.getRights()){
						if(null != accessMap && accessMap.hasAccessRight(right.getBitField()))
							rightsString += right.getAbbreviation();
						else
							rightsString += "-"; //$NON-NLS-1$
					}
					
					return rightsString;
				}

				@Override
				public void setValue(AceDto object, String value) {
				}

				@Override
				public String getPath() {
					return "__secureeRightsString";
				}
			}, 80, securee.getName());

			ccSecuree.setMenuDisabled(true);
			columns.add(ccSecuree);
		}
		
	}

	private ColumnConfig<AceDto, AceDto> createInheritanceColumnConfig() {
		ColumnConfig<AceDto, AceDto> ccInheritanceType = new ColumnConfig<AceDto, AceDto>(new IdentityValueProvider<AceDto>(), 120, SecurityMessages.INSTANCE.inheritance());
		ccInheritanceType.setCell(new AbstractCell<AceDto>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					AceDto value, SafeHtmlBuilder sb) {
				HierarchicalAceDto hace = (HierarchicalAceDto) value;
				if(InheritanceTypeDto.BOTH == hace.getInheritancetype())
					sb.append(BaseIcon.ARROWS_IN_OUT.toSafeHtml());
				else if(InheritanceTypeDto.HERE == hace.getInheritancetype())
					sb.append(BaseIcon.ARROW_LEFT.toSafeHtml()); 
				else
					sb.append(BaseIcon.ARROW_RIGHT.toSafeHtml()); 
				
			}
		});
		ccInheritanceType.setMenuDisabled(true);
		
		return ccInheritanceType;
	}

	private ColumnConfig<AceDto, AceDto> createAccessTypeColumnConfig() {
		ColumnConfig<AceDto,AceDto> ccAccessType = new ColumnConfig<AceDto, AceDto>(new IdentityValueProvider<AceDto>(), 120, SecurityMessages.INSTANCE.accessType());
		ccAccessType.setMenuDisabled(true);
		ccAccessType.setCell(new AbstractCell<AceDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					AceDto model, SafeHtmlBuilder sb) {
				if(AccessTypeDto.ALLOW == model.getAccesstype())
					sb.append(BaseIcon.ACCEPT.toSafeHtml()); 
				else 
					sb.append(BaseIcon.DELETE.toSafeHtml()); 
			}
		});

		return ccAccessType;
	}

	private ColumnConfig<AceDto,AbstractUserManagerNodeDto> createFolkColumnConfig() {
		ColumnConfig<AceDto,AbstractUserManagerNodeDto> ccFolk = new ColumnConfig<AceDto,AbstractUserManagerNodeDto>(acePa.folk(), 200, SecurityMessages.INSTANCE.folk()); 
		ccFolk.setMenuDisabled(true);
		ccFolk.setCell(new AbstractCell<AbstractUserManagerNodeDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					AbstractUserManagerNodeDto folk, SafeHtmlBuilder sb) {
				if(null == folk){
					sb.appendEscaped(SecurityMessages.INSTANCE.undefined());
					return;
				}
				
				BaseIcon icon = userManagerService.getIcon(folk);
				if(null == icon && null != folk.toDisplayTitle())
					sb.appendEscaped(folk.toDisplayTitle());
				else 
					sb.append(icon.toSafeHtml()).appendHtmlConstant("&nbsp;&nbsp;").appendEscaped(folk.toDisplayTitle());
			}
		});
		
		return ccFolk;
	}

	protected void displayEditDialog(final AceDto selectedACE, final SecurityViewInformation information) {
		final DwWindow window = new DwWindow();
		window.setHeaderIcon(BaseIcon.LOCK);
		window.setHeading(SecurityMessages.INSTANCE.editACE()); 
		window.setSize(400, 500);
		
		/* prepare base model for form */
		/* create form */
		final SimpleForm form = SimpleForm.getInlineInstance();
		form.setLabelAlign(LabelAlign.LEFT);

		/* folk */
		form.addField(AbstractUserManagerNodeDto.class, AceDto.PROPERTY_FOLK, SecurityMessages.INSTANCE.folk(), selectedACE.getFolk(), new SFFCGenericTreeNode() { 
			public UITree getTreeForPopup() {
				return userManagerTree;
			}
		});
		
		/* access type */
		form.addField(List.class, AceDto.PROPERTY_ACCESSTYPE, SecurityMessages.INSTANCE.accessType(), selectedACE.getAccesstype(),
			new SFFCStaticRadioList<AccessTypeDto>() {
				@Override
				public Map<String, AccessTypeDto> getValues() {
					Map<String, AccessTypeDto> map = new HashMap<String, AccessTypeDto>();
					
					map.put(SecurityMessages.INSTANCE.allow(), AccessTypeDto.ALLOW);
					map.put(SecurityMessages.INSTANCE.deny(), AccessTypeDto.DENY);
					
					return map;
				}
				
				@Override
				public Container getRadioContainer() {
					HorizontalLayoutContainer cont = new HorizontalLayoutContainer();
					cont.setHeight(20);
					return cont;
				}
		});
		
		/* inheritance type */
		if(selectedACE instanceof HierarchicalAceDto){
			form.addField(List.class, HierarchicalAceDto.PROPERTY_INHERITANCETYPE, SecurityMessages.INSTANCE.inheritance(), ((HierarchicalAceDto) selectedACE).getInheritancetype(), 
				new SFFCStaticRadioList<InheritanceTypeDto>() {
					@Override
					public Map<String, InheritanceTypeDto> getValues() {
						Map<String, InheritanceTypeDto> map = new HashMap<String, InheritanceTypeDto>();
						
						map.put(SecurityMessages.INSTANCE.here(), InheritanceTypeDto.HERE);
						map.put(SecurityMessages.INSTANCE.inherited(), InheritanceTypeDto.INHERITED);
						map.put(SecurityMessages.INSTANCE.hereAndInherited(), InheritanceTypeDto.BOTH);
						
						return map;
					}
			});
		}
		
		/* add type */
		final String fastAccessKey = form.addField(
			List.class, "fastAccess", SecurityMessages.INSTANCE.fastAccess(), //$NON-NLS-1$ //$NON-NLS-2$
			new SFFCStaticDropdownList<Integer>() {
				@Override
				public Map<String, Integer> getValues() {
					Map<String, Integer> map = new LinkedHashMap<String, Integer>();
					
					map.put(SecurityMessages.INSTANCE.fullAccess(), 0); 
					map.put(SecurityMessages.INSTANCE.noAccess(), 1); 
					
					return map;
				}
		});
		
		form.addCondition(fastAccessKey, new FieldChanged(), new SimpleFormAction() {
			public void onSuccess(SimpleForm form) {
				Object oValue = form.getValue(fastAccessKey);
				int value = -1;
				if(oValue instanceof Integer)
					value = (Integer)oValue;
				
				if(value < 0)
					return;
				
				for(SecureeDto securee : information.getAvailableSecurees()){
					form.beginFieldset(securee.getName());
					for(RightDto right : securee.getRights()){
						String rightId = createRightId(securee, right);
						Widget widget = form.getField(rightId);
						if(widget instanceof CheckBox){
							CheckBox cb = (CheckBox)widget;
							cb.setValue(value == 0, true);
						}
					}
				}
			}
			
			public void onFailure(SimpleForm form) {
			}
		});
		
		
		for(SecureeDto securee : information.getAvailableSecurees()){
			AceAccessMapDtoDec accessMap = (AceAccessMapDtoDec) ((AceDtoDec) selectedACE).getAccessMap(securee.getSecureeId());
			
			form.addField(Separator.class, new SFFCSeparatorImpl(TYPE.H_SMALL, securee.getName()));

			form.setLabelWidth(150);
			for(RightDto right : securee.getRights()){
				String rightId = createRightId(securee, right);

				/* add field */
				form.addField(
					Boolean.class, 
					rightId, 
					right.getDescription() + " (" + right.getAbbreviation() + ")", //$NON-NLS-1$ //$NON-NLS-2$
					accessMap.hasAccessRight(right.getBitField())); 
			}
		}
		
		/* load fields and add form to window */
		form.loadFields();
		
		window.add(form, new MarginData(10));
		
		/* add buttons */
		window.addCancelButton();
		
		DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.apply()); 
		okButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				editACE(selectedACE, form, information);
				window.hide();
			}
		});
		window.addButton(okButton);
		
		/* display window */
		window.show();
	}
	

	private String createRightId(SecureeDto securee, RightDto right) {
		return "right_" + securee.getSecureeId() + right.getBitField(); //$NON-NLS-1$
	}

	protected void editACE(AceDto selectedACE, SimpleForm form, SecurityViewInformation information) {
		/* inherit basic properties */
		selectedACE.setFolk((AbstractUserManagerNodeDto) form.getValue(AceDto.PROPERTY_FOLK));
		selectedACE.setAccesstype((AccessTypeDto) form.getValue(AceDto.PROPERTY_ACCESSTYPE));
		if(selectedACE instanceof HierarchicalAceDto)
			((HierarchicalAceDto)selectedACE).setInheritancetype((InheritanceTypeDto) form.getValue(HierarchicalAceDto.PROPERTY_INHERITANCETYPE)); 
		
		/* inherit rights information */
		for(SecureeDto securee : information.getAvailableSecurees()){
			AceAccessMapDtoDec accessMap = (AceAccessMapDtoDec) (((AceDtoDec) selectedACE).getAccessMap(securee.getSecureeId()));

			/* remove all rights */
			accessMap.clearRights();
			
			for(RightDto right : securee.getRights()){
				String rightId = createRightId(securee, right);

				if(Boolean.TRUE.equals(form.getValue(rightId)))
					accessMap.addAccessRight(right.getBitField());
			}
			
			((AceDtoDec)selectedACE).addAccessMap(accessMap);
		}
		
		/* perform server call */
		doEditACE(selectedACE, new NotamCallback<AceDto>(SecurityMessages.INSTANCE.aceApplied()) {
			@Override
			public void doOnSuccess(AceDto result) {
				aceStore.update(result);
			}
		});
	}

	abstract protected void doEditACE(AceDto ace, AsyncCallback<AceDto> callback);

	protected void removeACEs(final List<AceDto> aces){
		doRemoveACEs(aces, new NotamCallback<Void>(SecurityMessages.INSTANCE.acesDeleted()) { 
			@Override
			public void doOnSuccess(Void result) {
				for(AceDto ace : aces)
					aceStore.remove(ace);
			}
		});
	}
	
	abstract protected void doRemoveACEs(final List<AceDto> aces, AsyncCallback<Void> callback);
	
	protected void addAce(){
		doAddAce(new NotamCallback<AceDto>(SecurityMessages.INSTANCE.aceAdded()) {
			@Override
			public void doOnSuccess(AceDto result) {
				aceStore.add(result);
			}
		});
	}
	
	abstract protected void doAddAce(AsyncCallback<AceDto> callback);
	
	protected void aceMoved(AceDto ace, int index){
		doAceMoved(ace, index, new NotamCallback<AceDto>(SecurityMessages.INSTANCE.aceAtPosition(index)){
			@Override
			public void doOnSuccess(AceDto result) {
				aceStore.update(result);
			}
		});
	}
	
	abstract protected void doAceMoved(AceDto ace, int index, AsyncCallback<AceDto> callback);
}
