package net.datenwerke.rs.incubator.client.jaspertotable.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto;

public interface JasperToTableRpcServiceAsync {

   void getConfig(JasperReportDto report, AsyncCallback<JasperToTableConfigDto> callback);

   void setConfig(JasperReportDto report, JasperToTableConfigDto config, AsyncCallback<Void> callback);

}
