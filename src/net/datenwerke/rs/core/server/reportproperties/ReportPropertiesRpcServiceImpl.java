package net.datenwerke.rs.core.server.reportproperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;
import net.datenwerke.rs.core.client.reportproperties.rpc.ReportPropertiesRpcService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Write;

/**
 * 
 *
 */
@Singleton
public class ReportPropertiesRpcServiceImpl extends SecuredRemoteServiceServlet implements ReportPropertiesRpcService {

   private static final long serialVersionUID = -6592646535785857296L;

   private final DtoService dtoService;
   private final ReportService reportService;

   @Inject
   public ReportPropertiesRpcServiceImpl(DtoService dtoService, ReportService reportService) {

      /* store objects */
      this.dtoService = dtoService;
      this.reportService = reportService;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "report", isDto = true, verify = @RightsVerification(rights = Write.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public ReportDto updateProperties(@Named("report") ReportDto reportDto,
         List<ReportStringPropertyDto> addedProperties, List<ReportStringPropertyDto> modifiedProperties,
         List<ReportStringPropertyDto> removedProperties) throws ServerCallFailedException {
      Report report = (Report) dtoService.loadPoso(reportDto);

      /* remove */
      for (ReportStringPropertyDto propDto : removedProperties) {
         ReportStringProperty property = (ReportStringProperty) dtoService.loadPoso(propDto);

         if (!report.hasReportProperty(property))
            throw new ServerCallFailedException("Report does not have property");

         reportService.remove(report, property);
      }

      /* add */
      for (ReportStringPropertyDto propertyDto : addedProperties) {
         ReportStringProperty property = new ReportStringProperty();
         property.setName(propertyDto.getName());
         property.setStrValue(propertyDto.getStrValue());
         if (null == property.getName())
            property.setName(getUniqueName(report));
         report.addReportProperty(property);
         reportService.persist(property);
      }

      modifiedProperties.removeAll(removedProperties);
      modifiedProperties.removeAll(addedProperties);

      /* modify */

      for (ReportStringPropertyDto propertyDto : modifiedProperties) {
         ReportStringProperty property = (ReportStringProperty) dtoService.loadPoso(propertyDto);

         if (!report.hasReportProperty(property))
            throw new ServerCallFailedException("Report does not have property");

         /* merge data and store report */
         dtoService.mergePoso(propertyDto, property);

      }
      reportService.merge(report);

      return (ReportDto) dtoService.createDtoFullAccess(report);
   }

   protected String getUniqueName(Report report) {
      String name = "unnamed";
      int i = 0;
      while (hasProperty(report, name))
         name = "unnamed_" + (++i);

      return name;
   }

   protected boolean hasProperty(Report report, String name) {
      for (ReportProperty p : report.getReportProperties())
         if (p instanceof ReportStringProperty)
            if (name.equals(p.getName()))
               return true;
      return false;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<String> getPropertyKeys(ReportDto reportDto) throws ServerCallFailedException {
      List<String> filtered = reportService.getReportStringPropertyKeys();
      filtered.removeAll(getSupportedPropertyKeys(reportDto));
      return filtered;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<String> getSupportedPropertyKeys(ReportDto reportDto) throws ServerCallFailedException {
      List<String> propertyKeys = new ArrayList<>();

      List<AvailableReportProperties> props = new ArrayList<>();
      props.addAll(getCommonPropertyKeys());

      if (reportDto instanceof TableReportDto)
         props.addAll(getDynamicListSpecificPropertyKeys());

      Collections.sort(props);

      for (AvailableReportProperties prop : props)
         propertyKeys.add(prop.getValue());

      Collections.sort(propertyKeys); 
      
      return propertyKeys;
   }

   private List<AvailableReportProperties> getCommonPropertyKeys() {
      List<AvailableReportProperties> commonProps = new ArrayList<>();
      for (AvailableReportProperties property : AvailableReportProperties.values())
         if (!property.isDynamicListSpecificProperty())
            commonProps.add(property);

      return commonProps;
   }

   private List<AvailableReportProperties> getDynamicListSpecificPropertyKeys() {
      List<AvailableReportProperties> specificProps = new ArrayList<>();
      for (AvailableReportProperties property : AvailableReportProperties.values())
         if (property.isDynamicListSpecificProperty())
            specificProps.add(property);

      return specificProps;
   }

   @Override
   public List<ReportStringPropertyDto> getInheritedProperties(ReportDto reportDto) {
      Report report = (Report) dtoService.loadPoso(reportDto);

      if (!(report instanceof ReportVariant))
         return new ArrayList<>();

      return ((ReportVariant) report).getBaseReport().getReportProperties().stream()
            .filter(property -> property instanceof ReportStringProperty)
            .map(property -> (ReportStringPropertyDto) dtoService.createDtoFullAccess(property))
            .collect(Collectors.toList());
   }

}
