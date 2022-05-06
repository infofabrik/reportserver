package net.datenwerke.rs.printer.client.printer.ui;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.printer.client.printer.PrinterUiService;
import net.datenwerke.rs.printer.client.printer.dto.pa.PrinterDatasinkDtoPA;

public class PrinterDatasinkForm extends SimpleFormView {

   final PrinterUiService printerUiService; 
   
   @Inject
   public PrinterDatasinkForm(
         PrinterUiService printerUiService
         ) {
      this.printerUiService = printerUiService;
   }
   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name */
      form.addField(String.class, PrinterDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      /* description */
      form.addField(String.class, PrinterDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);

      /* printer name */
      form.addField(List.class, PrinterDatasinkDtoPA.INSTANCE.printerName(),
            BaseMessages.INSTANCE.printerName(), new SFFCStaticDropdownList<String>() {
               public Map<String, String> getValues() {
                  return printerUiService.getAvailablePrinters()
                        .stream()
                        .collect(toMap(Function.identity(), Function.identity()));
               }
            });

   }

}
