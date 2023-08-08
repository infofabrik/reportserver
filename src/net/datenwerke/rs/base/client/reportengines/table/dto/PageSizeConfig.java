package net.datenwerke.rs.base.client.reportengines.table.dto;

import java.io.Serializable;

public class PageSizeConfig implements Serializable {

   private static final long serialVersionUID = -2682352821676661988L;

   private int minCols;
   private int maxCols;
   private int numberOfRows;

   public PageSizeConfig() {
   }

   public PageSizeConfig(int minCols, int maxCols, int numberOfRows) {
      this.minCols = minCols;
      this.maxCols = maxCols;
      this.numberOfRows = numberOfRows;
   }

   public int getMinCols() {
      return minCols;
   }

   public void setMinCols(int minCols) {
      this.minCols = minCols;
   }

   public int getMaxCols() {
      return maxCols;
   }

   public void setMaxCols(int maxCols) {
      this.maxCols = maxCols;
   }

   public int getNumberOfRows() {
      return numberOfRows;
   }

   public void setNumberOfRows(int numberOfRows) {
      this.numberOfRows = numberOfRows;
   }

}
