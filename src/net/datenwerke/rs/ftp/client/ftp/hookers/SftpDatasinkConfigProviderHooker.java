package net.datenwerke.rs.ftp.client.ftp.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.ftp.client.ftp.FtpUiModule;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.ui.SftpDatasinkForm;

public class SftpDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

   private final Provider<SftpDatasinkForm> formProvider;

   @Inject
   public SftpDatasinkConfigProviderHooker(Provider<SftpDatasinkForm> formProvider) {

      /* store objects */
      this.formProvider = formProvider;
   }

   @Override
   public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
      return getDatasinkClass().equals(datasinkDefinition.getClass());
   }

   @Override
   public Collection<? extends MainPanelView> getAdminViews(DatasinkDefinitionDto datasinkDefinition) {
      return Collections.singleton(formProvider.get());
   }

   @Override
   public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
      return FtpUiModule.SFTP_TYPE;
   }

   @Override
   public String getDatasinkName() {
      return FtpUiModule.SFTP_NAME;
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new SftpDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return FtpUiModule.SFTP_ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }
}
