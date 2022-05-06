package net.datenwerke.rs.printer.service.printer;

import java.util.List;
import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.service.usermanager.entities.User;

public class DummyPrinterServiceImpl implements PrinterService {

   @Override
   public Optional<PrinterDatasink> getDefaultDatasink() {
      return null;
   }

   @Override
   public String getDatasinkPropertyName() {
      return null;
   }

   @Override
   public StorageType getStorageType() {
      return null;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return null;
   }

   @Override
   public void doExportIntoDatasink(Object report, User user, DatasinkDefinition datasinkDefinition,
         DatasinkConfiguration config) throws DatasinkExportException {
   }

   @Override
   public List<String> getAvailablePrinters() {
      return null;
   }

}
