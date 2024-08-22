package net.datenwerke.rs.core.client.reportmanager;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.rpc.ReportManagerTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class ReportManagerTreeManagerDao extends TreeDbManagerDao {

   @Inject
   public ReportManagerTreeManagerDao(ReportManagerTreeManagerAsync treeManager) {
      super(treeManager);
   }

   @Override
   public Dto2PosoMapper getBaseNodeMapper() {
      return AbstractReportManagerNodeDto.newPosoMapper();
   }

   public void duplicateReportWithVariants(AbstractNodeDto toDuplicate, List<AbstractNodeDto> variants,
         AsyncCallback<AbstractNodeDto> callback) {
      ((ReportManagerTreeManagerAsync) treeManager).duplicateReportWithVariants(toDuplicate, variants, state,
            transformDtoCallback(callback));
   }

}
