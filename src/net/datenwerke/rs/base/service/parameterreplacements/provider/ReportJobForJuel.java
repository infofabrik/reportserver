package net.datenwerke.rs.base.service.parameterreplacements.provider;

import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;

public class ReportJobForJuel {
   private String name = "";
   private String description = "";
   private Long id = -1l;

   public ReportJobForJuel(ReportExecuteJob job) {
      if (null != job) {
         if (null != job.getTitle())
            this.name = job.getTitle();
         if (null != job.getDescription())
            this.description = job.getDescription();
         if (null != job.getId())
            this.id = job.getId();
      }
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public Long getId() {
      return id;
   }

}
