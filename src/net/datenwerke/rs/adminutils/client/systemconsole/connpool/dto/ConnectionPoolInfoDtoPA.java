package net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ConnectionPoolInfoDtoPA extends PropertyAccess<ConnectionPoolInfoDto> {

   public static final ConnectionPoolInfoDtoPA INSTANCE = GWT.create(ConnectionPoolInfoDtoPA.class);

   /* Properties */
   ValueProvider<ConnectionPoolInfoDto, Long> dsId();

   ValueProvider<ConnectionPoolInfoDto, String> dsName();

   ValueProvider<ConnectionPoolInfoDto, Integer> maxPoolSize();

   ValueProvider<ConnectionPoolInfoDto, Integer> noOfConnections();

   ValueProvider<ConnectionPoolInfoDto, Integer> busyConnections();

   ValueProvider<ConnectionPoolInfoDto, Integer> idleConnections();

   ValueProvider<ConnectionPoolInfoDto, Integer> threadsAwaitingConnection();

   ValueProvider<ConnectionPoolInfoDto, Integer> unclosedOrphanedConnections();

   ValueProvider<ConnectionPoolInfoDto, Long> timestamp();

   @Path("timestamp")
   ModelKeyProvider<ConnectionPoolInfoDto> key();
}