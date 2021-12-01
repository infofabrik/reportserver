package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskTreeManagerDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskFolderDtoPA;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskGeneralReferenceDtoPA;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskReportReferenceDtoPA;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MenuRenameHooker implements TsFavoriteMenuHook {
	
	private final TsDiskTreeManagerDao treeManagerDao;
	private final TeamSpaceUIService teamSpaceService;
	
	@Inject
	public MenuRenameHooker(
		TsDiskTreeManagerDao treeManagerDao,
		TeamSpaceUIService teamSpaceService
		){
	
		/* store objects */
		this.treeManagerDao = treeManagerDao;
		this.teamSpaceService = teamSpaceService;
	}
	
	
	@Override
	public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector, 
			final TsDiskMainComponent mainComponent) {
		if(null == items || items.isEmpty() || items.size() > 1)
			return false;
		if(! teamSpaceService.isUser(mainComponent.getCurrentSpace()))
			return false;
		if(items.get(0) instanceof TsDiskRootDto)
			return false;
		
		MenuItem renameItem = new DwMenuItem(BaseMessages.INSTANCE.rename());
		menu.add(renameItem);
		renameItem.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				displayChangeNameDialog(items.get(0), mainComponent);
			}
		});
		
		return true;
	}

	protected void displayChangeNameDialog( final AbstractTsDiskNodeDto node, final TsDiskMainComponent mainComponent) {
		if(null == node)
			return;
		
		final DwWindow window = new DwWindow();
		window.setSize(430, 290);
		window.setHeading(BaseMessages.INSTANCE.rename());
		
		/* create form */
		final SimpleForm form = SimpleForm.getInlineInstance();
		window.add(form, new MarginData(10));
		
		final ValueProvider nameProp = 
			(node instanceof TsDiskFolderDto) ? TsDiskFolderDtoPA.INSTANCE.name() : 
				node instanceof TsDiskReportReferenceDto ? TsDiskReportReferenceDtoPA.INSTANCE.name() : 
					 TsDiskGeneralReferenceDtoPA.INSTANCE.name();
		final String nameKey = form.addField(String.class, 
				nameProp, 
				BaseMessages.INSTANCE.name(),
				new SFFCAllowBlank() {
					@Override
					public boolean allowBlank() {
						return false;
					}
				});
		
		final ValueProvider descProp = 
			(node instanceof TsDiskFolderDto) ? TsDiskFolderDtoPA.INSTANCE.description() : 
				node instanceof TsDiskReportReferenceDto ? TsDiskReportReferenceDtoPA.INSTANCE.description() :
					TsDiskGeneralReferenceDtoPA.INSTANCE.description();
		final String descriptionKey = form.addField(String.class, 
				descProp, 
				BaseMessages.INSTANCE.propertyDescription(),
				new SFFCTextAreaImpl(){
					@Override
					public int getHeight() {
						return 100;
					}
		});
		
		/* store old data */
		final String oldName = (String) nameProp.getValue(node);
		final String oldDesc = (String) descProp.getValue(node);

		/* bind item */
		form.bind(node);
		
		/* create buttons */
		DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
		window.addButton(cancelBtn);
		cancelBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				nameProp.setValue(node, oldName);
				descProp.setValue(node, oldDesc);
				window.hide();
			}
		});
		
		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		window.addButton(submitBtn);
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if(! form.isValid())
					return;
				window.hide();
				
				mainComponent.mask(BaseMessages.INSTANCE.storingMsg());
				treeManagerDao.setState(mainComponent.getCurrentSpace());
				treeManagerDao.updateName(node, true, (String)form.getValue(nameKey), (String)form.getValue(descriptionKey), new RsAsyncCallback<AbstractNodeDto>(){
					@Override
					public void onSuccess(AbstractNodeDto result) {
						mainComponent.unmask();
						mainComponent.itemSelected((AbstractTsDiskNodeDto) result);
					}
					@Override
					public void onFailure(Throwable caught) {
						mainComponent.unmask();
					}
				});
			}
		});
		
		window.show();
	}

}
