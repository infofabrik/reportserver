package net.datenwerke.treedb.ext.client.eximport.im.ui;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.widget.core.client.TabItemConfig;

import net.datenwerke.gxtdto.client.baseex.widget.DwTabPanel;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;

/**
 * 
 *
 */

abstract public class ImporterConfigPanel<C extends ImportConfigDto> extends DwTabPanel {

	protected final ImporterItemsPanel<C> itemsPanel;
	protected final ImporterMainPropertiesPanel<C> mainPropertiesPanel;
	
	public ImporterConfigPanel(
		ImporterItemsPanel<C> itemsPanel,
		ImporterMainPropertiesPanel<C> mainPropertiesPanel
		){
		super(GWT.<TabPanelAppearance> create(TabPanelBottomAppearance.class));
		
		/* store objects */
		this.itemsPanel = itemsPanel;
		this.mainPropertiesPanel = mainPropertiesPanel;

		/* init */
		initializeUI();
		
	}

	private void initializeUI() {
		setBodyBorder(false);
		setBorders(false);
		
		TabItemConfig tabMainProperties = new TabItemConfig(ExImportMessages.INSTANCE.importMainProperties());
		add(mainPropertiesPanel, tabMainProperties);
		
		TabItemConfig tabItems = new TabItemConfig(ExImportMessages.INSTANCE.importItems());
		add(itemsPanel, tabItems);
	}

	public ImportConfigDto getConfiguration() throws NotProperlyConfiguredException {
		C config = createConfigObject();
		
		itemsPanel.populateConfig(config);
		mainPropertiesPanel.populateConfig(config);
		
		itemsPanel.validateConfig(config);
		mainPropertiesPanel.validateConfig(config);
		
		return config;
	}
	
	public void resetConfig() {
		itemsPanel.resetConfig();
		mainPropertiesPanel.resetConfig();
	}

	abstract protected C createConfigObject();
	
}
