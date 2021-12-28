package net.datenwerke.rs.search.client.search;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Provider;

import com.google.inject.Inject;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.homepage.DwMainViewportUiService;
import net.datenwerke.gf.client.homepage.modules.ClientTempModule;
import net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec;
import net.datenwerke.rs.search.client.search.locale.SearchMessages;
import net.datenwerke.rs.search.client.search.module.SearchAreaComponent;
import net.datenwerke.rs.search.client.search.module.SearchAreaModule;

public class SearchUiServiceImpl implements SearchUiService {

   public static final String TAG_BASE_TYPE = "baseType";
   public static final String TAG_EXACT_TYPE = "exactType";

   private final DwMainViewportUiService viewportService;
   private final Provider<SearchAreaComponent> searchAreaProvider;
   private final Provider<SearchAreaModule> searchModuleProvider;
   private final Provider<DtoInformationService> dtoInfoServiceProvider;

   @Inject
   public SearchUiServiceImpl(Provider<SearchAreaComponent> searchAreaProvider,
         Provider<SearchAreaModule> searchModuleProvider, DwMainViewportUiService viewportService,
         Provider<DtoInformationService> dtoInfoServiceProvider) {
      this.searchAreaProvider = searchAreaProvider;
      this.searchModuleProvider = searchModuleProvider;
      this.viewportService = viewportService;
      this.dtoInfoServiceProvider = dtoInfoServiceProvider;
   }

   @Override
   public void runSearch(String searchStr) {
      runSearch(searchStr, true);
   }

   private void runSearch(String searchStr, boolean serverCall) {
      /* get display component, add it to module and display module */
      SearchAreaComponent displayComponent = searchAreaProvider.get();

      SearchAreaModule module = null;
      for (ClientTempModule m : viewportService.getTempModules()) {
         if (m instanceof SearchAreaModule) {
            module = (SearchAreaModule) m;
            break;
         }
      }
      if (null == module)
         module = searchModuleProvider.get();
      module.addSearchComponent(searchStr, displayComponent);
      viewportService.addTempModule(module);

      /* run search */
      if (serverCall)
         displayComponent.runSearch(searchStr);
   }

   @Override
   public void displaySearchModule() {
      runSearch(SearchMessages.INSTANCE.emptySearchTabHeader(), false);
   }

   @Override
   public String enhanceQuery(String query) {
      if (null == query || query.trim().length() < 1)
         return "";

      if (query.startsWith("="))
         query = query.substring(1);
      else
         query = "*" + query.toLowerCase().trim() + "*";

      return query;
   }

   @Override
   public SearchFilterDto createFilterFor(List<Class<? extends RsDto>> allowedTypes, boolean exactTypes) {
      SearchFilterDtoDec filter = new SearchFilterDtoDec();
      filter.setLimit(25);
      final DtoInformationService dtoInfoService = dtoInfoServiceProvider.get();
      Set<SearchResultTagDto> tagSet = new HashSet<>();
      for (final Class<? extends RsDto> type : allowedTypes) {
         SearchResultTagDto tag = new SearchResultTagDto();
         SearchResultTagTypeDto tagType = new SearchResultTagTypeDto();
         if (exactTypes)
            tagType.setType(TAG_EXACT_TYPE);
         else
            tagType.setType(TAG_BASE_TYPE);
         tag.setType(tagType);
         tag.setValue(dtoInfoService.lookupPosoMapper(type).getCanonicalName());
         tagSet.add(tag);
      }
      filter.setTags(tagSet);

      return filter;
   }

}
