package net.datenwerke.eximport.ex

import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil
import groovy.xml.XmlNodePrinter

class ExportFileProcessingHelperImpl implements ExportFileProcessingHelper {
   
   String xml;
   
   private def reportServerExport;
   
   
   @Override
   public void setXml(String xml) {
      this.xml = xml
      reportServerExport = new XmlSlurper(false, false).parseText(xml)
   }
   
   @Override
   public void addExporterDataBlock(String exporterDataBlock) {
      def toAdd = new XmlSlurper(false, false).parseText( exporterDataBlock )
      reportServerExport.data.appendNode toAdd
   }
   
   @Override
   public String serialize() {
      return XmlUtil.serialize(reportServerExport)
   }

   @Override
   public void replaceExportedItem(String itemId, String exporterDataBlockClazz, String replacementItemXml) {
      def replacement = new XmlSlurper(false, false).parseText( replacementItemXml )
      def exporterDataBlock = findExporterDataBlock(exporterDataBlockClazz)
         
      def exportedItem = exporterDataBlock
            .exportedItem
            .find { it.'@xml:id'.text() == itemId }
            
      // remove old node
      exportedItem.replaceNode{ }
      
      // add replacement
      exporterDataBlock.appendNode replacement
   }

   @Override
   public void addExportedItem(String exporterDataBlockClazz, String newItemXml) {
      def newItem = new XmlSlurper(false, false).parseText( newItemXml )
      def exporterDataBlock = findExporterDataBlock(exporterDataBlockClazz)
      exporterDataBlock.appendNode newItem
   }
   
   private findExporterDataBlock(String exporterDataBlockClazz) {
      return reportServerExport.data.exporterData
         .find{ it.'@exporterType'.text() == exporterDataBlockClazz }
   }
}