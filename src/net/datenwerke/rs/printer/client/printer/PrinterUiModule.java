package net.datenwerke.rs.printer.client.printer;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto;
import net.datenwerke.rs.printer.client.printer.provider.PrinterTreeProvider;
import net.datenwerke.rs.printer.client.printer.provider.annotations.DatasinkTreePrinter;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class PrinterUiModule extends AbstractGinModule {

   public final static String NAME = "Printer";
   public final static BaseIcon ICON = BaseIcon.PRINT;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = PrinterDatasinkDto.class;

   @Override
   protected void configure() {
      bind(PrinterUiService.class).to(PrinterUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreePrinter.class).toProvider(PrinterTreeProvider.class);
      bind(PrinterUiStartup.class).asEagerSingleton();

   }
}
