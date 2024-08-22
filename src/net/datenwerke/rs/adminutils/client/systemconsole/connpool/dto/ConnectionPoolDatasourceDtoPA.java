package net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ConnectionPoolDatasourceDtoPA extends PropertyAccess<ConnectionPoolDatasourceDto> {

   public static final ConnectionPoolDatasourceDtoPA INSTANCE = GWT.create(ConnectionPoolDatasourceDtoPA.class);

   /* Properties */
   ValueProvider<ConnectionPoolDatasourceDto, Long> dsId();

   ValueProvider<ConnectionPoolDatasourceDto, String> dsName();

   @Path("dsId")
   ModelKeyProvider<ConnectionPoolDatasourceDto> key();
}