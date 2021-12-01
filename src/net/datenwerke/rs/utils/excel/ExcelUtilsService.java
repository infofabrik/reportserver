package net.datenwerke.rs.utils.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.inject.ImplementedBy;

@ImplementedBy(ExcelUtilsServiceImpl.class)
public interface ExcelUtilsService {
	public void setCellComment(Workbook workbook, int cellsCount, Row row, String text);
}
