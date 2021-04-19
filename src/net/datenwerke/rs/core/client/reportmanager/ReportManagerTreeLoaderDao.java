package net.datenwerke.rs.core.client.reportmanager;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.rpc.ReportManagerTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class ReportManagerTreeLoaderDao extends TreeDbLoaderDao {

	
	@Inject
	public ReportManagerTreeLoaderDao(
		ReportManagerTreeLoaderAsync treeHandler, TreeDbFtoConverter treeDbFtoConverter	
		){
		super(treeHandler, treeDbFtoConverter);
	}
	

	public void getReportsInCatalog(ReportFolderDto folder, boolean showVariants, AsyncCallback<List<ReportDto>> callback){
		final AsyncCallback handledCallback = transformListCallback(callback);
		((ReportManagerTreeLoaderAsync)treeLoader).getReportsInCatalog(folder, showVariants, new AsyncCallback<String[][]>() {

			@Override
			public void onFailure(Throwable caught) {
				handledCallback.onFailure(caught);
			}

			@Override
			public void onSuccess(String[][] result) {
				List<AbstractNodeDto> nodes = new ArrayList<AbstractNodeDto>();
				
				for(String[] fto : result){
					AbstractNodeDtoDec dto = treeDbFtoConverter.convert(fto);
					nodes.add(dto);
				}
				
				handledCallback.onSuccess(nodes);
			}
		});
	}


	public void getReportsInCatalog(boolean showVariants,
			RsAsyncCallback<List<ReportDto>> rsAsyncCallback) {
		getReportsInCatalog(null, showVariants, rsAsyncCallback);
	}
}
