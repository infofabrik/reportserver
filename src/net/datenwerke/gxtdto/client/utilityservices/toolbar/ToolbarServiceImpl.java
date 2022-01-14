package net.datenwerke.gxtdto.client.utilityservices.toolbar;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.DataReader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.utils.labelprovider.DisplayTitleLabelProvider;
import net.datenwerke.gxtdto.client.utils.loadconfig.SearchLoadConfig;
import net.datenwerke.gxtdto.client.utils.loadconfig.SearchLoadConfigBean;
import net.datenwerke.rs.incubator.client.globalsearch.locale.GlobalSearchMessages;
import net.datenwerke.rs.incubator.client.managerhelpersearch.hookers.ManagerHelperSearchBar.SearchTemplates;
import net.datenwerke.rs.search.client.search.dto.EmptySearchResultDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.pa.SearchResultEntryDtoPA;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class ToolbarServiceImpl implements ToolbarService {

   private final Resources resources = GWT.create(Resources.class);

   interface Resources extends ClientBundle {
      @Source("toolbar.gss")
      Style css();
   }

   interface Style extends CssResource {
      @ClassName("rs-toolbar-item-plain")
      String itemPlain();
   }

   public ToolbarServiceImpl() {
      super();
      resources.css().ensureInjected();
   }

   @Override
   public DwTextButton createSmallButtonLeft(String text, ImageResource icon) {
      DwTextButton btn = new DwTextButton(text);
      btn.setIcon(icon);
      btn.setIconAlign(IconAlign.LEFT);
      btn.setArrowAlign(ButtonArrowAlign.RIGHT);
      btn.setScale(ButtonScale.SMALL);
      return btn;
   }

   @Override
   public DwTextButton createSmallButtonLeft(BaseIcon icon) {
      DwTextButton btn = new DwTextButton(icon);
      btn.setIconAlign(IconAlign.LEFT);
      btn.setArrowAlign(ButtonArrowAlign.RIGHT);
      btn.setScale(ButtonScale.SMALL);
      return btn;
   }

   @Override
   public DwTextButton createSmallButtonLeft(String text, BaseIcon icon) {
      DwTextButton btn = new DwTextButton(text, icon);
      btn.setIconAlign(IconAlign.LEFT);
      btn.setArrowAlign(ButtonArrowAlign.RIGHT);
      btn.setScale(ButtonScale.SMALL);
      return btn;
   }

   @Override
   public DwTextButton createLargeButtonLeft(String text, ImageResource icon) {
      DwTextButton btn = new DwTextButton(text);
      btn.setIcon(icon);
      btn.setIconAlign(IconAlign.LEFT);
      btn.setArrowAlign(ButtonArrowAlign.BOTTOM);
      btn.setScale(ButtonScale.LARGE);
      return btn;
   }

   @Override
   public <D extends TextButton> D configureButton(D button, String text, ImageResource icon) {
      button.setText(text);
      button.setIcon(icon);
      button.setIconAlign(IconAlign.LEFT);
      button.setArrowAlign(ButtonArrowAlign.RIGHT);
      return button;
   }

   @Override
   public <D extends TextButton> D configureButton(D button, String text, BaseIcon icon) {
      button.setText(text);
      if (button instanceof DwTextButton)
         ((DwTextButton) button).setIcon(icon);
      if (button instanceof DwSplitButton)
         ((DwSplitButton) button).setIcon(icon);
      button.setIconAlign(IconAlign.LEFT);
      button.setArrowAlign(ButtonArrowAlign.RIGHT);
      return button;
   }

   @Override
   public DwTextButton createLargeButtonTop(String text, ImageResource icon, String tooltip) {
      DwTextButton btn = createLargeButtonTop(text, icon);
      btn.setToolTip(tooltip);
      return btn;
   }

   @Override
   public DwTextButton createLargeButtonTop(String text, ImageResource icon) {
      DwTextButton btn = new DwTextButton(text);
      btn.setIcon(icon);
      btn.setIconAlign(IconAlign.TOP);
      btn.setArrowAlign(ButtonArrowAlign.BOTTOM);
      btn.setScale(ButtonScale.LARGE);
      return btn;
   }

   @Override
   public DwTextButton createLargeButtonTop(String text, BaseIcon icon) {
      DwTextButton btn;
      if (null != icon)
         btn = new DwTextButton(text, icon);
      else
         btn = new DwTextButton();
      btn.setIconAlign(IconAlign.TOP);
      btn.setArrowAlign(ButtonArrowAlign.BOTTOM);
      btn.setScale(ButtonScale.LARGE);
      return btn;
   }

   @Override
   public DwTextButton createPlainToolbarItem(ImageResource icon) {
      return createPlainToolbarItem("", icon);
   }

   @Override
   public Widget createPlainToolbarItem(BaseIcon icon) {
      return new HTML(icon.toSafeHtml());
   }

   @Override
   public DwTextButton createPlainToolbarItem(String name, ImageResource icon) {
      DwTextButton ib = new DwTextButton(name, icon) { // $NON-NLS-1$
         @Override
         public void onBrowserEvent(Event event) {
            // swallow
         }
      };
      ib.addStyleName(resources.css().itemPlain());
      return ib;
   }

   @Override
   public DwTextButton createUnstyledToolbarItem(String name, ImageResource icon) {
      DwTextButton ib = new DwTextButton(name, icon);
      ib.addStyleName(resources.css().itemPlain());
      return ib;
   }

   @Override
   public DwTextButton createUnstyledToolbarItem(String name, BaseIcon icon) {
      DwTextButton ib = new DwTextButton(name, icon);
      ib.addStyleName(resources.css().itemPlain());
      return ib;
   }

   @Override
   public void addPlainToolbarItem(ToolBar toolbar, ImageResource icon) {
      toolbar.add(createPlainToolbarItem(icon));
   }

   @Override
   public void addPlainToolbarItem(ToolBar toolbar, BaseIcon icon) {
      toolbar.add(createPlainToolbarItem(icon));
   }

   @Override
   public LabelToolItem createText(String text) {
      return new LabelToolItem(text);
   }

   @Override
   public void addSearchBar(ToolBar toolbar, SelectionHandler<SearchResultEntryDto> selectionHandler,
         final RpcProxy<SearchLoadConfig, SearchResultListDto> proxy) {
      DataReader<PagingLoadResult<SearchResultEntryDto>, SearchResultListDto> reader = new DataReader<PagingLoadResult<SearchResultEntryDto>, SearchResultListDto>() {
         @Override
         public PagingLoadResult<SearchResultEntryDto> read(Object loadConfig, SearchResultListDto data) {
            if (data.getTotalLength() > 0)
               return new PagingLoadResultBean<SearchResultEntryDto>(data.getData(), data.getTotalLength(),
                     data.getOffset());
            List<SearchResultEntryDto> emptyList = new ArrayList<SearchResultEntryDto>();
            emptyList.add(new EmptySearchResultDto(GlobalSearchMessages.INSTANCE.noResultTitle(),
                  GlobalSearchMessages.INSTANCE.noResultDesc(), BaseIcon.EXCLAMATION.toString()));
            return new PagingLoadResultBean<SearchResultEntryDto>(emptyList, data.getTotalLength(), data.getOffset());
         }
      };

      PagingLoader<SearchLoadConfig, PagingLoadResult<SearchResultEntryDto>> listLoader = new PagingLoader<SearchLoadConfig, PagingLoadResult<SearchResultEntryDto>>(
            proxy, reader);

      ListStore<SearchResultEntryDto> store = new ListStore<SearchResultEntryDto>(
            SearchResultEntryDtoPA.INSTANCE.dtoId());
      listLoader.addLoadHandler(
            new LoadResultListStoreBinding<SearchLoadConfig, SearchResultEntryDto, PagingLoadResult<SearchResultEntryDto>>(
                  store));

      final SearchTemplates template = GWT.create(SearchTemplates.class);

      ListView<SearchResultEntryDto, SearchResultEntryDto> view = new ListView<SearchResultEntryDto, SearchResultEntryDto>(
            store, new IdentityValueProvider<SearchResultEntryDto>());
      view.setCell(new AbstractCell<SearchResultEntryDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, SearchResultEntryDto value,
               SafeHtmlBuilder sb) {
            sb.append(template.render(value, BaseIcon.from(value.getIconSmall()).getCssName()));
         }
      });

      final ComboBox<SearchResultEntryDto> combo = new ComboBox<SearchResultEntryDto>(
            new ComboBoxCell<SearchResultEntryDto>(store, new DisplayTitleLabelProvider<SearchResultEntryDto>(), view) {

               /*
                * gxt workarround http://www.sencha.com/forum/showthread.php?185967
                */
               @Override
               protected PagingLoadConfig getParams(String query) {
                  SearchLoadConfig config = null;
                  if (loader.isReuseLoadConfig()) {
                     config = (SearchLoadConfig) loader.getLastLoadConfig();
                  } else {
                     config = new SearchLoadConfigBean();
                  }
                  config.setLimit(pageSize);
                  config.setOffset(0);
                  config.setQuery(query);

                  return config;
               }
            });
      combo.setWidth(150);
      combo.setMinListWidth(560);
      combo.setLoader(listLoader);
      combo.setHideTrigger(true);

      combo.addSelectionHandler(selectionHandler);

      addPlainToolbarItem(toolbar, BaseIcon.SEARCH);
      toolbar.add(combo);

   }

}
