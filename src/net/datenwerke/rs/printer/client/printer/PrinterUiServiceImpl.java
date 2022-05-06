package net.datenwerke.rs.printer.client.printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class PrinterUiServiceImpl implements PrinterUiService {

   private Map<StorageType, Boolean> enabledConfigs = new HashMap<>();
   
   private List<String> availablePrinters = new ArrayList<>();

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return enabledConfigs;
   }

   public void setEnabledConfigs(Map<StorageType, Boolean> enabledConfigs) {
      this.enabledConfigs = enabledConfigs;
   }
   
   @Override
   public List<String> getAvailablePrinters() {
      return availablePrinters;
   }
   
   public void setAvailablePrinters(List<String> availablePrinters) {
      this.availablePrinters = availablePrinters;
   }
   
}
