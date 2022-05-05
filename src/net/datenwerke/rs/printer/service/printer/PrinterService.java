package net.datenwerke.rs.printer.service.printer;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyPrinterServiceImpl.class)
public interface PrinterService extends BasicDatasinkService {

}
