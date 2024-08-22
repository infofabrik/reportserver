package net.datenwerke.rs.base.service.reportengines.table.output.object;

public class TableReportInformationReport extends RSTableModel {

   private static final long serialVersionUID = 4866690411019026944L;
   final private int count;
   private long executeDuration;
   private RSTableModel tableModel;
   
   public TableReportInformationReport() {
      count = -1;
   }

   public TableReportInformationReport(int count, long executeDuration, RSTableModel tableModel) {
      super();
      this.count = count;
      this.executeDuration = executeDuration;
      this.tableModel = tableModel;
   }
   
   public int getDataCount() {
      return count;
   }

   public long getExecuteDuration() {
      return executeDuration;
   }
   
   public RSTableModel getTableModel() {
      return tableModel;
   }
}
