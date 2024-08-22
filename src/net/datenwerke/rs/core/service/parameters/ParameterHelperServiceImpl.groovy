package net.datenwerke.rs.core.service.parameters

import javax.inject.Inject

import com.google.inject.Provider

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService
import net.datenwerke.rs.core.service.reportmanager.ReportService
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant
import net.datenwerke.rs.terminal.service.terminal.CopyResultType
import net.datenwerke.rs.utils.entitycloner.EntityClonerService

public class ParameterHelperServiceImpl implements ParameterHelperService {

   private final Provider<EntityClonerService> entityClonerServiceProvider
   private final Provider<ReportParameterService> parameterServiceProvider
   private final Provider<ReportService> reportServiceProvider
   
   @Inject
   public ParameterHelperServiceImpl(
      Provider<EntityClonerService> entityClonerServiceProvider,
      Provider<ReportParameterService> parameterServiceProvider,
      Provider<ReportService> reportServiceProvider
      ) {
         this.entityClonerServiceProvider = entityClonerServiceProvider
         this.parameterServiceProvider = parameterServiceProvider
         this.reportServiceProvider = reportServiceProvider
   }
   
   @Override
   public Map<CopyResultType, List<ParameterDefinition>> copyParameterDefinitions(Report origin, Report target, 
      boolean replaceExistingParameters) {
      if (!origin)
         throw new IllegalArgumentException('origin is null')
      if (!target)
         throw new IllegalArgumentException('target is null')

      if (origin instanceof ReportVariant)
         origin = origin.baseReport
      if (target instanceof ReportVariant)
         target = target.baseReport
         
      def entityClonerService = entityClonerServiceProvider.get()
      def parameterService = parameterServiceProvider.get()
      def reportService = reportServiceProvider.get()

      def parameterDefsKeysFrom = origin.parameterDefinitions*.key
      if (null in parameterDefsKeysFrom)
         throw new IllegalStateException("The report '$origin' contains parameter definitions with NULL key")

      def parameterDefsFrom = origin.parameterDefinitions
      def clonedParameterDefsFrom = parameterDefsFrom
            .collect{ entityClonerService.cloneEntity(it) }

      def parameterDefsKeysTo = target.parameterDefinitions*.key

      def copiedParamKeys = []
      def existingParamKeys = []

      clonedParameterDefsFrom
            .each{ param ->
               if (param.key in parameterDefsKeysTo) {
                  existingParamKeys = existingParamKeys << param
                  if (replaceExistingParameters) {
                     def existingParameter = parameterService.getParameterByKey(target.id, param.key)
                     parameterService.remove existingParameter
                     persist param, origin, target, parameterService
                  }
               } else {
                  copiedParamKeys = copiedParamKeys << param
                  persist param, origin, target, parameterService
               }
               // important: always merge report to avoid lost ParameterInstances
               reportService.merge target
            }
            
      return [
         (CopyResultType.COPYIED): copiedParamKeys.sort{it.key},
         (CopyResultType.EXISTING): existingParamKeys.sort{it.key}
         ]
   }

   private def persist(param, Report from, Report to, parameterService) {
      parameterService.persist param
      parameterService.addParameterDefinition to, param
   }
}
