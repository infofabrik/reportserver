package net.datenwerke.rs.base.client.datasinks.hooks;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

public interface DatasinkAuthenticatorConfiguratorHook extends Hook {

   Widget configureForm(SimpleFormView mainForm, DatasinkDefinitionDto datasink);

   String getAuthenticatorLabel();

   String getAuthenticatorName();

   boolean consumes(DatasinkDefinitionDto datasink);

   boolean isUploadForm();

}
