package net.datenwerke.rs.printer.client.printer.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;

public class ScheduleAsPrinterFileInformation implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = 8015739165901762987L;

   private PrinterDatasinkDto printerDatasinkDto;

   public PrinterDatasinkDto getPrinterDatasinkDto() {
      return printerDatasinkDto;
   }

   public void setPrinterDatasinkDto(PrinterDatasinkDto printerDatasinkDto) {
      this.printerDatasinkDto = printerDatasinkDto;
   }

}
