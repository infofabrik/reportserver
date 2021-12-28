package net.datenwerke.rs.base.client.parameters.headline;

import java.util.Collection;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;
import net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.headline.dto.pa.HeadlineParameterDefinitionDtoPA;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfiguratorImpl;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
@Singleton
public class HeadlineConfigurator
      extends ParameterConfiguratorImpl<HeadlineParameterDefinitionDto, HeadlineParameterInstanceDto> {

   @Override
   public Widget getEditComponentForDefinition(HeadlineParameterDefinitionDto definition, ReportDto report) {
      SimpleForm form = SimpleForm.getInlineInstance();

      form.addField(String.class, HeadlineParameterDefinitionDtoPA.INSTANCE.value(), RsMessages.INSTANCE.headline());

      /* bind definition */
      form.bind(definition);

      return form;
   }

   public String getName() {
      return RsMessages.INSTANCE.headline();
   }

   @Override
   protected HeadlineParameterDefinitionDto doGetNewDto() {
      return new HeadlineParameterDefinitionDto();
   }

   @Override
   public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
      return HeadlineParameterDefinitionDto.class.equals(type);
   }

   public ImageResource getIcon() {
      return BaseIcon.HEADER.toImageResource();
   }

   @Override
   public ParameterType getType() {
      return ParameterType.Separator;
   }

   @Override
   protected Widget doGetEditComponentForInstance(HeadlineParameterInstanceDto instance,
         Collection<ParameterInstanceDto> relevantInstances, HeadlineParameterDefinitionDto definition, boolean initial,
         int labelWidth, String executeReportToken, ReportDto report) {
      return SeparatorTextLabel.createHeadlineLarge(definition.getValue());
   }

}
