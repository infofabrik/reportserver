package net.datenwerke.rs.core.client.parameters.config;

import java.util.Collection;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * Used to configure parameters in front end.
 * 
 * 
 */
public interface ParameterConfigurator<D extends ParameterDefinitionDto, I extends ParameterInstanceDto> {

   public enum ParameterType {
      Normal, Separator, Container
   }

   public ParameterType getType();

   public ParameterDefinitionDto getNewDto(ReportDto report);

   public String getName();

   public Widget getEditComponentForDefinition(D definition, ReportDto report);

   public void updateDefinitionOnSubmit(D definition, Widget component);

   public Widget getEditComponentForInstance(I instance, D definition,
         Collection<ParameterInstanceDto> relevantInstances, boolean initial, int labelWidth, String executeReportToken,
         ReportDto report);

   public ImageResource getIcon();

   public boolean canHandle(ParameterProposalDto proposal);

   public ParameterDefinitionDto getNewDto(ParameterProposalDto proposal, ReportDto report);

   public void dependeeInstanceChanged(I instance, D aDefinition, Collection<ParameterInstanceDto> relevantInstances);

   public boolean consumes(Class<? extends ParameterDefinitionDto> type);

   boolean canDependOnParameters();

   public List<String> validateParameter(D definition, I instance, Widget widget);

   public boolean isAvailable();

}
