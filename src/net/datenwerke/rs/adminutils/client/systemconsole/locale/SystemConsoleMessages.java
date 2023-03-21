package net.datenwerke.rs.adminutils.client.systemconsole.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface SystemConsoleMessages extends Messages {

   public final SystemConsoleMessages INSTANCE = GWT.create(SystemConsoleMessages.class);

   public String systemConsole();

   public String systemConsolePermissionModuleDescription();

   public String generalInfo();

   public String versionLabel();

   public String javaVersionLabel();

   public String groovyVersionLabel();

   public String applicationServerLabel();

   public String maxMemoryLabel();

   public String operationSystemLabel();

   public String userAgentLabel();

   public String staticPamsLabel();

   public String jvmLiveMemory();

   public String jvmMemory();

   public String jvmGC();

   public String stop();

   public String start();

   public String numberUnits();

   public String unitInterval();

   public String jvmGCCalled();

   public String showMax();

   public String maximumPoolSize();

   public String numberOfConnections();

   public String busyConnections();

   public String connectionPool();

   public String connections();

   public String threadsAwaitingCheckout();

   public String unclosedOrphanedConnections();

   public String refreshDatasources();

   public String used();

   public String total();

   public String maximum();

   public String adminPanel();

   public String openTerminal();

   public String openTerminalInNewWindow();

}
