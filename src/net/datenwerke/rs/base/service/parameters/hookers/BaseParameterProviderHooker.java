package net.datenwerke.rs.base.service.parameters.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.base.service.parameters.blatext.BlatextParameterDefinition;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition;
import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterDefinition;
import net.datenwerke.rs.base.service.parameters.headline.HeadlineParameterDefinition;
import net.datenwerke.rs.base.service.parameters.separator.SeparatorParameterDefinition;
import net.datenwerke.rs.base.service.parameters.string.TextParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterProviderHook;

public class BaseParameterProviderHooker implements ParameterProviderHook {

   @Override
   public Collection<? extends Class<? extends ParameterDefinition>> getParameterDefinitions() {
      Set<Class<? extends ParameterDefinition>> definitions = new HashSet<Class<? extends ParameterDefinition>>();

      definitions.add(BlatextParameterDefinition.class);
      definitions.add(TextParameterDefinition.class);
      definitions.add(DateTimeParameterDefinition.class);
      definitions.add(DatasourceParameterDefinition.class);
      definitions.add(HeadlineParameterDefinition.class);
      definitions.add(SeparatorParameterDefinition.class);

      return definitions;
   }

}
