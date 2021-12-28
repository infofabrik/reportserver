package net.datenwerke.rs.core.client.reportmanager.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeLoaderDao;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeManagerDao;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerUIModule;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.posomap.ReportVariantDto2PosoMap;
import net.datenwerke.rs.core.client.reportmanager.dto.posomap.ReportFolderDto2PosoMap;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportDto2PosoMap;

public class FolderAndReportTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final ReportManagerTreeLoaderDao reportLoaderDao;
   private final ReportManagerTreeManagerDao reportManagerDao;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public FolderAndReportTreeProvider(TreeDBUIService treeDBUIService, ReportManagerTreeLoaderDao reportLoaderDao,
         ReportManagerTreeManagerDao reportManagerDao, ManagerHelperTreeFactory treeFactory) {

      this.treeDBUIService = treeDBUIService;
      this.reportLoaderDao = reportLoaderDao;
      this.reportManagerDao = reportManagerDao;
      this.treeFactory = treeFactory;
   }

   public ManagerHelperTree get() {
      /* store */
      List<Dto2PosoMapper> wlFilters = new ArrayList<Dto2PosoMapper>();
      wlFilters.add(new ReportFolderDto2PosoMap());
      wlFilters.add(new ReportDto2PosoMap());

      List<Dto2PosoMapper> blFilters = new ArrayList<Dto2PosoMapper>();
      blFilters.add(new ReportVariantDto2PosoMap());
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractReportManagerNodeDto.class, reportLoaderDao,
            false, wlFilters, blFilters);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(ReportManagerUIModule.class, store, reportLoaderDao,
            reportManagerDao);
      tree.configureIconProvider();

      return tree;
   }
}
