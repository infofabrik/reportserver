package net.datenwerke.rs.core.service.reportmanager.metadata;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;

public class ReportMetadataForJuel {

   private String name = "unnamed";

   private String value;

   private Long id;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public static ReportMetadataForJuel fromReportMetadata(ReportMetadata m) {
      ReportMetadataForJuel rmfj = new ReportMetadataForJuel();
      rmfj.setName(m.getName());
      rmfj.setId(m.getId());
      rmfj.setValue(m.getValue());

      return rmfj;
   }

}
