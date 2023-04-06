package net.datenwerke.rs.remoteserver.client.remoteservermanager.hookers;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.hooks.RemoteServerDefinitionConfigProviderHook;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.ui.forms.FolderForm;
import net.datenwerke.security.ext.client.security.ui.SecurityView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MainPanelViewProviderHooker implements MainPanelViewProviderHook {

   private final HookHandlerService hookHandler;
   
   private final Provider<FolderForm> folderFormProvider;

   private final Provider<SecurityView> securityViewProvider;

   @Inject
   public MainPanelViewProviderHooker(HookHandlerService hookHandler, 
         Provider<FolderForm> folderFormProvider,
         Provider<SecurityView> securityViewProvider) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.folderFormProvider = folderFormProvider;
      this.securityViewProvider = securityViewProvider;
   }

   public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
      if (node instanceof RemoteServerFolderDto)
         return getViewForRemoteServerFolder();
      if (node instanceof RemoteServerDefinitionDto)
         return getViewForRemoteServers((RemoteServerDefinitionDto) node);
      return null;
   }
   
   private List<MainPanelView> getViewForRemoteServers(final RemoteServerDefinitionDto remoteServerDefinition) {
      final List<MainPanelView> views = new ArrayList<>();

      views.addAll(hookHandler.getHookers(RemoteServerDefinitionConfigProviderHook.class).stream()
            .filter(config -> config.consumes(remoteServerDefinition))
            .flatMap(config -> config.getAdminViews(remoteServerDefinition).stream()).collect(toList()));

      views.add(securityViewProvider.get());
      return views;
   }


   private List<MainPanelView> getViewForRemoteServerFolder() {
      return Arrays.asList(folderFormProvider.get(), securityViewProvider.get());
   }

}