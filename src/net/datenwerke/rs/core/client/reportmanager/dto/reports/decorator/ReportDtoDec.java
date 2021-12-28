package net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;

/**
 * Dto Decorator for {@link ReportDto}
 *
 */
public class ReportDtoDec extends ReportDto implements ReportContainerDto {

   private static final long serialVersionUID = 1L;

   public ReportDtoDec() {
      super();
   }

   public ParameterInstanceDto getParameterInstanceFor(ParameterDefinitionDto definition) {
      Set<ParameterInstanceDto> instances = getParameterInstances();
      if (null == instances)
         return null;

      for (ParameterInstanceDto instance : instances) {
         if (null == instance.getDefinition())
            throw new IllegalStateException("Instances should always have a definition"); //$NON-NLS-1$

         if (instance.getDefinition().equals(definition))
            return instance;
      }

      return null;
   }

   public ParameterDefinitionDto getParameterDefinitionByKey(String name) {
      List<ParameterDefinitionDto> defs = getParameterDefinitions();
      if (null == defs)
         return null;
      for (ParameterDefinitionDto def : defs)
         if (name.equals(def.getKey()))
            return def;
      return null;
   }

   public void removeReportProperty(String name) {
      ReportPropertyDto property = getReportPropertyByName(name);
      if (null != property)
         getReportProperties().remove(property);
   }

   public List<ReportStringPropertyDto> getReportStringProperties() {
      List<ReportStringPropertyDto> props = new ArrayList<ReportStringPropertyDto>();
      for (ReportPropertyDto p : getReportProperties())
         if (p instanceof ReportStringPropertyDto)
            props.add((ReportStringPropertyDto) p);

      return props;
   }

   public void addReportProperty(ReportPropertyDto property) {
      if (null == getReportProperties())
         setReportProperties(new HashSet<ReportPropertyDto>());
      Set<ReportPropertyDto> properties = getReportProperties();
      properties.add(property);
      setReportProperties(properties);
   }

   public boolean hasReportPropertyWithName(String name) {
      return null != getReportPropertyByName(name);
   }

   public ReportPropertyDto getReportPropertyByName(String name) {
      if (null == name)
         return null;
      for (ReportPropertyDto property : getReportProperties())
         if (name.equals(property.getName()))
            return property;
      return null;
   }

   public ReportPropertyDto getEffectiveReportProperty(String name) {
      ReportPropertyDto property = getReportPropertyByName(name);

      if (null == property && this instanceof ReportVariantDto)
         property = getParentReportPropertyByName(name);

      return property;
   }

   public boolean hasParentReportPropertyWithName(String name) {
      return null != getParentReportPropertyByName(name);
   }

   public ReportPropertyDto getParentReportPropertyByName(String name) {
      if (!(this instanceof ReportVariantDto))
         return null;
      if (null == name)
         return null;
      for (ReportPropertyDto property : getParentReportProperties())
         if (name.equals(property.getName()))
            return property;
      return null;
   }

   @Override
   public ReportDto getReport() {
      return this;
   }

   public boolean isBaseReportExecutable() {
      return true;
   }

   public String getEffectiveReportProperty(String propertyName, String defaultValue) {
      ReportPropertyDto prop = getEffectiveReportProperty(propertyName);

      if (null != prop && prop instanceof ReportStringPropertyDto)
         return ((ReportStringPropertyDto) prop).getStrValue();

      return defaultValue;
   }
}
