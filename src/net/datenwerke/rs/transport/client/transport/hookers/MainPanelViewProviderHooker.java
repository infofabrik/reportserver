package net.datenwerke.rs.transport.client.transport.hookers;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportFolderDto;
import net.datenwerke.rs.transport.client.transport.ui.forms.FolderForm;
import net.datenwerke.rs.transport.client.transport.ui.forms.TransportForm;
import net.datenwerke.security.ext.client.security.ui.SecurityView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MainPanelViewProviderHooker implements MainPanelViewProviderHook {

   
   private final Provider<FolderForm> folderFormProvider;
   private final Provider<TransportForm> transportFormProvider;

   private final Provider<SecurityView> securityViewProvider;

   @Inject
   public MainPanelViewProviderHooker(
         Provider<FolderForm> folderFormProvider,
         Provider<SecurityView> securityViewProvider,
         Provider<TransportForm> transportFormProvider
         ) {

      this.folderFormProvider = folderFormProvider;
      this.securityViewProvider = securityViewProvider;
      this.transportFormProvider = transportFormProvider;
   }

   public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
      if (node instanceof TransportFolderDto)
         return getViewForTransportFolder();
      if (node instanceof TransportDto)
         return getViewForTransport((TransportDto) node);
      return null;
   }
   
   private List<MainPanelView> getViewForTransport(final TransportDto transport) {
      return Arrays.asList(transportFormProvider.get(), securityViewProvider.get());
   }


   private List<MainPanelView> getViewForTransportFolder() {
      return Arrays.asList(folderFormProvider.get(), securityViewProvider.get());
   }

}