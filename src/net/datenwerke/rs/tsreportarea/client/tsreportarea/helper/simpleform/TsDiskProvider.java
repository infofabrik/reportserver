package net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;

import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.UiTreeFactory;
import net.datenwerke.gf.client.treedb.selection.SelectionFilter;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCTreeNodeSelectionFilter;
import net.datenwerke.gf.client.treedb.simpleform.TreeNodeDtoProvider;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskTreeLoaderDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIService;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.AbstractTsDiskNodeDto2PosoMap;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TsDiskProvider extends TreeNodeDtoProvider {
	
	private final TsDiskTreeLoaderDao treeLoaderDao;
	private final TreeDBUIService treeDBUIService;
	private final UiTreeFactory uiTreeFactory;
	private final TsDiskUIService tsDiskUIService;
	
	@Inject
	public TsDiskProvider(
		ClipboardUiService clipboardService,
		TsDiskTreeLoaderDao treeLoaderDao, 
		TreeDBUIService treeDBUIService,
		UiTreeFactory uiTreeFactory,
		TsDiskUIService tsDiskUIService) {
		super(clipboardService);
		
		this.treeLoaderDao = treeLoaderDao;
		this.treeDBUIService = treeDBUIService;
		this.uiTreeFactory = uiTreeFactory;
		this.tsDiskUIService = tsDiskUIService;
		
	}

	@Override
	public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
		if(configs.length == 0 || !( configs[0] instanceof SFFCTsTeamSpaceSelector))
			return false;
		
		while(type != null){
			if(type.equals(AbstractTsDiskNodeDto.class))
				return true;
			type = type.getSuperclass();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Widget createFormField() {
		final SFFCTsTeamSpaceSelector config = (SFFCTsTeamSpaceSelector) configs[0];
		
		final SelectionFilter filter = getSelectionFilter(configs);
		
		final SingleTreeSelectionField ssf = new SingleTreeSelectionField(AbstractTsDiskNodeDto.class);
		ssf.setIgnoreTriggerClick(true);
		ssf.addTriggerClickHandler(new TriggerClickHandler() {
			
			private TeamSpaceDto theTeamSpace;
			
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				TeamSpaceDto teamSpace = config.getTeamSpace();
				if(null == ssf.getTreePanel() || null == theTeamSpace || ! theTeamSpace.equals(teamSpace)){
					theTeamSpace = teamSpace;
					if(null == theTeamSpace){
						new DwAlertMessageBox (TsFavoriteMessages.INSTANCE.noTeamSpaceSelectedTitle(), TsFavoriteMessages.INSTANCE.noTeamSpaceSelectedMsg()).show();
					} else {
						treeLoaderDao.setState(theTeamSpace);

						Set<Dto2PosoMapper> filters = new HashSet<Dto2PosoMapper>();
						filters.add(new AbstractTsDiskNodeDto2PosoMap());
						EnhancedTreeStore treeStore = treeDBUIService.getUITreeStore(AbstractTsDiskNodeDto.class, treeLoaderDao, false, filters);

						ssf.setSelectionFilter(filter);
						
						UITree tree = uiTreeFactory.create(treeStore);
						tree.setIconProvider(tsDiskUIService.getIconProvider());
						ssf.setTreePanel(tree);
						ssf.getTreePanel().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
						
						ssf.triggerClicked();
					}
				} else if(theTeamSpace.equals(teamSpace))
					ssf.triggerClicked();
					
			}
		});
		
		ssf.addValueChangeHandler(new ValueChangeHandler<AbstractNodeDto>() {
			@Override
			public void onValueChange(ValueChangeEvent<AbstractNodeDto> event) {
				ValueChangeEvent.fire(TsDiskProvider.this, event.getValue());
			}
		});
		
		ssf.setName(name);
		
		return ssf;
	}

	private SelectionFilter getSelectionFilter(
			SimpleFormFieldConfiguration[] configs) {
		for(SimpleFormFieldConfiguration c : configs)
			if(c instanceof SFFCTreeNodeSelectionFilter)
				return ((SFFCTreeNodeSelectionFilter)c).getFilter();
		return SelectionFilter.DUMMY_FILTER;
	}

	
	
}
