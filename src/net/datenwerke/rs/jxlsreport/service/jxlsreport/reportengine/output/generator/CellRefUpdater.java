package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator;

import org.jxls.command.CellDataUpdater;
import org.jxls.common.CellData;
import org.jxls.common.CellRef;
import org.jxls.common.Context;

public class CellRefUpdater implements CellDataUpdater {

   @Override
   public void updateCellData(CellData cellData, CellRef targetCell, Context context) {
      if (cellData.isFormulaCell() && cellData.getFormula() != null) {
         cellData.setEvaluationResult(
               cellData.getFormula().replaceAll("(?<=[A-Za-z])\\d", Integer.toString(targetCell.getRow() + 1)));
      }
   }
}