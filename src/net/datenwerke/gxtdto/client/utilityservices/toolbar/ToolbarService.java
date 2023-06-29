package net.datenwerke.gxtdto.client.utilityservices.toolbar;

import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.utils.loadconfig.SearchLoadConfig;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public interface ToolbarService {

   DwTextButton createSmallButtonLeft(BaseIcon icon);

   /**
    * Creates a small button with the given icon aligned to the left of the button.
    * 
    * @param text the button text
    * @param icon the icon
    * @return the button created
    */
   DwTextButton createSmallButtonLeft(String text, BaseIcon icon);

   /**
    * Creates a small button with the given icon aligned to the left of the button.
    * 
    * @param text the button text
    * @param icon the icon
    * @return the button created
    */
   DwTextButton createSmallButtonLeft(String text, ImageResource icon);

   /**
    * Creates a large button with the given icon aligned to the left of the button.
    * 
    * @param text The button text
    * @param icon The icon
    * @return the button created
    */
   DwTextButton createLargeButtonLeft(String text, ImageResource icon);

   DwTextButton createLargeButtonTop(String text, ImageResource icon);

   DwTextButton createLargeButtonTop(String text, ImageResource icon, String tooltip);

   <D extends TextButton> D configureButton(D button, String text, ImageResource icon);

   <D extends TextButton> D configureButton(D button, String text, BaseIcon icon);

   void addPlainToolbarItem(ToolBar toolbar, ImageResource icon);

   DwTextButton createPlainToolbarItem(ImageResource icon);

   DwTextButton createPlainToolbarItem(String name, ImageResource icon);

   Component createText(String views);

   DwTextButton createLargeButtonTop(String label, BaseIcon icon);

   DwTextButton createUnstyledToolbarItem(String name, ImageResource icon);

   DwTextButton createUnstyledToolbarItem(String name, BaseIcon icon);

   void addPlainToolbarItem(ToolBar toolbar, BaseIcon icon);

   Widget createPlainToolbarItem(BaseIcon icon);

   void addSearchBar(ToolBar toolbar, final SelectionHandler<SearchResultEntryDto> selectionHandler,
         RpcProxy<SearchLoadConfig, SearchResultListDto> proxy);

}