package net.datenwerke.rs.printer.client.printer.ui;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.inject.Inject;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.core.client.datasinkmanager.ui.forms.DatasinkSimpleForm;
import net.datenwerke.rs.printer.client.printer.PrinterUiService;
import net.datenwerke.rs.printer.client.printer.dto.pa.PrinterDatasinkDtoPA;

public class PrinterDatasinkForm extends DatasinkSimpleForm {

   final PrinterUiService printerUiService; 
   
   @Inject
   public PrinterDatasinkForm(PrinterUiService printerUiService) {
      super();
      this.printerUiService = printerUiService;
   }

   protected void configureSimpleFormCustomFields(SimpleForm form) {
      form.setFieldWidth(750);
      /* printer name */
      form.addField(List.class, PrinterDatasinkDtoPA.INSTANCE.printerName(),
            DatasinksMessages.INSTANCE.printerName(), new SFFCStaticDropdownList<String>() {
               public Map<String, String> getValues() {
                  return printerUiService.getAvailablePrinters()
                        .stream()
                        .collect(toMap(Function.identity(), Function.identity()));
               }
            });
   }
}
