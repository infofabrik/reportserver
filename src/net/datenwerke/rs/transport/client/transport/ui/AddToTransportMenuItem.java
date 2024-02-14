package net.datenwerke.rs.transport.client.transport.ui;

import java.util.List;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.TreeMenuItem;
import net.datenwerke.gf.client.treedb.selection.TreeSelectionPopup;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.TransportDao;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class AddToTransportMenuItem extends TreeMenuItem {

   final TransportDao transportDao;
   public AddToTransportMenuItem(UITree transportTree, TransportDao transportDao) {
      super();
      this.transportDao = transportDao;
      setIcon(BaseIcon.ADD);
      setText(TransportMessages.INSTANCE.addToTransportText());
      addMenuSelectionListener((tree, node) -> {
         if (null != node) {
            addToTransport(node, transportTree);
         }
      });
   }

   protected void addToTransport(AbstractNodeDto node, UITree transportTree) {
      TreeSelectionPopup popup = new TreeSelectionPopup(transportTree, TransportDto.class) {
         @Override
         protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
            final ModalAsyncCallback<Void> callback = new ModalAsyncCallback<Void>(
                  TransportMessages.INSTANCE.addedToTransport()) {
            };
            
            for (AbstractNodeDto transport : selectedItems) {
               if (transport instanceof TransportDto) {
                  if (node instanceof ReportFolderDto
                       || node instanceof DatasinkFolderDto
                       || node instanceof FileServerFolderDto
                       || node instanceof DatasourceFolderDto) {
                     //add only one object descendants
                     transportDao.addAllDescendants((TransportDto) transport, node, false, callback);
                  } else {
                     //add only one element
                     transportDao.addElement((TransportDto) transport, node, false, callback);
                  }
               }
            }
         }
      };
      popup.setHeading(TransportMessages.INSTANCE.addToTransportText());
      popup.show();
   }
}
