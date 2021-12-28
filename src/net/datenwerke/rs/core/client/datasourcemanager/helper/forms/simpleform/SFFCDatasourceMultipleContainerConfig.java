package net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;

/**
 * Can be used to further configure the datasource field.
 * 
 *
 */
public interface SFFCDatasourceMultipleContainerConfig extends SimpleFormFieldConfiguration {

   public DatasourceContainerProviderDto getSpecialDatasourceContainer(DwModel model);
}
