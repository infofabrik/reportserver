package net.datenwerke.rs.core.service.reportmanager.hookers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.PreviewImage;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitymerge.service.hooker.EntityMergeHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;


public class ReportDefaultMergeHooker<T extends Report> extends EntityMergeHooker implements EntityMergeHook {

   protected final Provider<ReportService> service;
   protected final Provider<ReportParameterService> pService;

   @Inject
   public ReportDefaultMergeHooker(Provider<ReportService> service, 
         Provider<EntityClonerService> cloneService, 
         Provider<ReportParameterService> pService, 
         @Assisted Class<T> targetClazz) {
      super(targetClazz,cloneService);
      this.service = service;
      this.pService = pService;
   }

   @Override
   protected void mergeSpecialFields(Object oldInstance, Object newInstance) {
      Report oldReport = (Report) oldInstance; //source object 
      Report newReport = (Report) newInstance; //target values
      
      /* Parameter instances / definitions */
      Collection<ParameterDefinition> parameterInstances = oldReport.getParameterDefinitions();
      List<ParameterDefinition> collect = new ArrayList<>(parameterInstances);
      collect.forEach(pService.get()::remove);
      List<ParameterDefinition> parameterDefinitions = newReport.getParameterDefinitions();
      parameterDefinitions.forEach(pd -> {
         ParameterDefinition copy = cloneService.get().cloneEntity(pd);
         pService.get().addParameterDefinition(oldReport, copy);
      });
      
      /* ReportProperties */
      Set<ReportProperty> reportPropertiesOld = oldReport.getReportProperties();
      reportPropertiesOld.clear();
      Set<ReportProperty> reportPropertiesNew = newReport.getReportProperties();
      reportPropertiesNew.forEach(prop -> {
         ReportProperty copy = cloneService.get().cloneEntity(prop);
         oldReport.addReportProperty(copy);
         service.get().persist(copy);
      });
      
      /* ReportMetadata */
      Set<ReportMetadata> reportMetadataOld = oldReport.getReportMetadata();
      reportMetadataOld.clear();
      Set<ReportMetadata> reportMetadataNew = newReport.getReportMetadata();
      reportMetadataNew.forEach(meta -> {
         ReportMetadata copy = cloneService.get().cloneEntity(meta);
         oldReport.addReportMetadata(copy);
         service.get().persist(copy);
      });
      
      /* Previewimage */
      PreviewImage previewImage = newReport.getPreviewImage();
      if(Objects.nonNull(previewImage)) 
         oldReport.setPreviewImage(cloneService.get().cloneEntity(previewImage));
      
   }
   
   @Override
   protected void commitChanges(Object oldInstance) {     
      service.get().merge((T) oldInstance);     
   }

}
