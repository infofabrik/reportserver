package net.datenwerke.gxtdto.client.utilityservices.toolbar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.utils.loadconfig.SearchLoadConfig;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public interface ToolbarService {

	public DwTextButton createSmallButtonLeft(BaseIcon icon);
	public DwTextButton createSmallButtonLeft(String text, BaseIcon icon);
	public DwTextButton createSmallButtonLeft(String text, ImageResource icon);
	
	public DwTextButton createLargeButtonLeft(String text, ImageResource icon);
	public DwTextButton createLargeButtonTop(String text, ImageResource icon);
	public DwTextButton createLargeButtonTop(String text, ImageResource icon, String tooltip);

	public <D extends TextButton> D configureButton(D button, String text, ImageResource icon);
	public <D extends TextButton> D configureButton(D button, String text, BaseIcon icon);
	
	public void addPlainToolbarItem(ToolBar toolbar, ImageResource icon);

	DwTextButton createPlainToolbarItem(ImageResource icon);

	DwTextButton createPlainToolbarItem(String name, ImageResource icon);

	public Component createText(String views);

	public DwTextButton createLargeButtonTop(String label, BaseIcon icon);

	DwTextButton createUnstyledToolbarItem(String name, ImageResource icon);
	DwTextButton createUnstyledToolbarItem(String name, BaseIcon icon);

	public void addPlainToolbarItem(ToolBar toolbar, BaseIcon icon);

	Widget createPlainToolbarItem(BaseIcon icon);
	
	void addSearchBar(ToolBar toolbar, final SelectionHandler<SearchResultEntryDto> selectionHandler, 
	      RpcProxy<SearchLoadConfig, SearchResultListDto> proxy);
	
	
}