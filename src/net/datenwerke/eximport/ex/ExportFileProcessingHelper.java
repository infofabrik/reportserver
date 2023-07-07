package net.datenwerke.eximport.ex;

public interface ExportFileProcessingHelper {

   void setXml(String xml);
   
   void addExporterDataBlock(String exporterDataBlock);
   
   String serialize();
   
   void replaceExportedItem(String itemId, String exporterDataBlockClazz, String replacementItemXml);

   void addExportedItem(String exporterDataBlockClazz, String newItemXml);
}
