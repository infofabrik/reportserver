package net.datenwerke.gf.client.treedb.selection;


import java.text.ParseException;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gf.client.history.HistoryUiService.JumpToObjectCallbackImpl;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.hooks.AddSelectionFieldMenuItemHook;
import net.datenwerke.gf.client.treedb.selection.hooks.AddSelectionFieldMenuItemHook.MenuNodeProvider;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwTwinTriggerField;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwTwinTriggerFieldCell;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.dnd.helpers.TypeAwareDropTarget;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class SingleTreeSelectionField extends DwTwinTriggerField<AbstractNodeDto> {

	@Inject
	private static HistoryUiService historyService;
	
	@Inject
	private static HookHandlerService hookHandlerService;
	
	@Inject
	private static ClipboardUiService clipboardService;
	
	protected UITree treePanel;
	protected Dto originalDTO;
	private final Class<? extends RsDto>[] types;
	
	protected boolean ignoreTriggerClick = false;

	private HandlerRegistration triggerClickHandler;

	private SelectionFilter selectionFilter = SelectionFilter.DUMMY_FILTER;
	
	public SingleTreeSelectionField(Class<? extends RsDto>... types){
		super(new DwTwinTriggerFieldCell<AbstractNodeDto>(), new PropertyEditor<AbstractNodeDto>() {
			@Override
			public String render(AbstractNodeDto object) {
				return object.toDisplayTitle();
			}

			@Override
			public AbstractNodeDto parse(CharSequence text)
					throws ParseException {
				return null;
			}
		});
		this.types = types;
		
		
		/* build ui */
		initializeUI();
	}

	private void initializeUI() {
		/* configure triggerField */
		setTriggerIcon(BaseIcon.SEARCH);
		setHideTwinTrigger(true);
		
		/* must come after appearance config */
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				setEditable(false);
			}
		});
		
		/* take care of drops */
		initDropTarget();
		
		/* init context menu */
		initContextMenu();
		
		addTriggerClickHandler();
	}
	
	private void addTriggerClickHandler() {
		if(null != triggerClickHandler)
			return;
		
		/* listener */
		triggerClickHandler = addTriggerClickHandler(new TriggerClickHandler() {
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				triggerClicked();
			}
		});
	}

	protected void initDropTarget() {
		new TypeAwareDropTarget(this, types){
			
			@Override
			protected void onDragDrop(DndDropEvent event) {
				if(! (event.getData() instanceof List))
					return;
				
				List list = (List) event.getData();

				if(list.size() > 0){
					setValue((AbstractNodeDto) ((TreeStore.TreeNode)list.get(0)).getData(), true);
				}

				super.onDragDrop(event);
			}
		};
	}
	
	public UITree getTreePanel() {
		return treePanel;
	}
	
	public void setTreePanel(UITree treePanel) {
		this.treePanel = treePanel;
	}

	protected void initContextMenu() {
		Menu menu = new DwMenu();
		
		ClipboardItem clipboardItem = clipboardService.getClipboardItem();
		if(clipboardItem instanceof ClipboardDtoItem){
			final Dto dto = ((ClipboardDtoItem)clipboardItem).getDto();
			
			if(null != dto && dto instanceof AbstractNodeDto && isValidType(dto.getClass())){
				MenuItem insert = new DwMenuItem(BaseMessages.INSTANCE.insert());
				menu.add(insert);
				menu.addSelectionHandler(new SelectionHandler<Item>() {
					@Override
					public void onSelection(SelectionEvent<Item> event) {
						setValue((AbstractNodeDto) dto, true);
					}
				});
				
				menu.add(new SeparatorMenuItem());
			}
		}
		
		menu.add(historyService.getJumpToMenuItem(new JumpToObjectCallbackImpl() {
			@Override
			public Dto getDtoTarget() {
				return getValue();
			}
		}));
		
		for(AddSelectionFieldMenuItemHook hooker : hookHandlerService.getHookers(AddSelectionFieldMenuItemHook.class)){
			hooker.addMenuEntries(this,menu,new MenuNodeProvider(){
				@Override
				public AbstractNodeDto getNode() {
					return getValue();
				}
			});
		}
		
		menu.add(new SeparatorMenuItem());
		
		MenuItem delete = new DwMenuItem(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
		menu.add(delete);
		
		delete.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				setValue(null, true);
			}
		});
		setContextMenu(menu);
	}
	
	public boolean isValidType(Class<?> needle) {
		while(null != needle){
			for(Class<?> type : types)
				if(type.equals(needle))
					return true;
			needle = needle.getSuperclass();
		}
		return false;
	}

	public void triggerClicked(){
		TreeSelectionPopup popup = new TreeSelectionPopup(treePanel, types){
			@Override
			protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
				if(selectedItems.size() > 0)
					setValue(selectedItems.get(0), true);
				else
					setValue(null, true);
			}
		};
		popup.setSelectedValues(getValue());
		popup.setSelectionFilter(selectionFilter);
		
		popup.show();
	}
	
	public void setIgnoreTriggerClick(boolean ignoreTriggerClick) {
		this.ignoreTriggerClick = ignoreTriggerClick;
		if(ignoreTriggerClick && null != triggerClickHandler)
			triggerClickHandler.removeHandler();
		else
			addTriggerClickHandler();
	}
	
	public boolean isIgnoreTriggerClick() {
		return ignoreTriggerClick;
	}
	
	public SelectionFilter getSelectionFilter() {
		return selectionFilter;
	}
	
	public void setSelectionFilter(SelectionFilter selectionFilter) {
		this.selectionFilter  = selectionFilter;
	}
}
