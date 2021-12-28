package net.datenwerke.gf.client.uiutils.date;

import java.util.Date;

public class DateFormulaContainer {

   private Date date;
   private String formula;

   public DateFormulaContainer() {

   }

   public DateFormulaContainer(Date date, String formula) {
      super();
      this.date = date;
      this.formula = formula;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getFormula() {
      return formula;
   }

   public void setFormula(String formula) {
      this.formula = formula;
   }

}
