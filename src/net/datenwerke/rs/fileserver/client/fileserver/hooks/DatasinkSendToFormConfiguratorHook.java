package net.datenwerke.rs.fileserver.client.fileserver.hooks;

import java.util.Map;
import java.util.Optional;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public interface DatasinkSendToFormConfiguratorHook extends Hook {

   boolean consumes(Class<? extends DatasinkDefinitionDto> datasinkType);

   void installAdditionalFields(SimpleForm form);

   Optional<Map<String, Object>> getAdditionalFieldsValues(SimpleForm form);

   String getWindowTitle();

   BaseIcon getIcon();

   int getWindowHeight();

   boolean isFolderedDatasink();

}
