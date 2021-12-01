package net.datenwerke.rs.incubator.client.jaspertotable;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto;
import net.datenwerke.rs.incubator.client.jaspertotable.rpc.JasperToTableRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class JasperToTableDao extends Dao {

	final private JasperToTableRpcServiceAsync rpcService;
	
	@Inject
	public JasperToTableDao(JasperToTableRpcServiceAsync rpcService){
		
		/* store object */
		this.rpcService = rpcService;
	}
	
	public void getConfig(JasperReportDto report,
			AsyncCallback<JasperToTableConfigDto> callback){
		rpcService.getConfig(report, transformAndKeepCallback(callback));
	}
	
	public void setConfig(JasperReportDto report, JasperToTableConfigDto config,
			AsyncCallback<Void> callback){
		rpcService.setConfig(report, config, transformAndKeepCallback(callback));
	}
	
	
}
