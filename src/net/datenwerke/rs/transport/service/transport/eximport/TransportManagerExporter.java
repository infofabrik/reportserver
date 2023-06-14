package net.datenwerke.rs.transport.service.transport.eximport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.datenwerke.eximport.obj.ComplexItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.SimpleItemProperty;
import net.datenwerke.rs.transport.service.transport.TransportModule;
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TransportManagerExporter extends TreeNodeExporter {

   private static final String EXPORTER_ID = "TransportManagerExporter";
   
   public static final String EXPORTER_NAME = "Transport-Export";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<? extends AbstractNode<?>> getTreeType() {
      return AbstractTransportManagerNode.class;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { TransportFolder.class, Transport.class };
   }
   
   @Override
   public String getDisplayNameFor(ExportedItem exportedItem) {
      if (exportedItem.getType().equals(Transport.class)) {
         String name = "";

         SimpleItemProperty createdOnProperty = (SimpleItemProperty) exportedItem.getPropertyByName("createdOn");
         String dateStr = createdOnProperty.getValue();
         SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
         String formattedDate = "";
         try {
            Date createdOn = formatter.parse(dateStr); 
            formattedDate = DateUtils.formatLocal(createdOn);
         } catch (ParseException e) {
            throw new IllegalArgumentException("Cannot parse date: " + dateStr);
         }
         
         if (null != createdOnProperty)
            name += formattedDate + "-";
         
         ComplexItemProperty shortKeyProperty = (ComplexItemProperty) exportedItem.getPropertyByName("key");
         if (null != shortKeyProperty && null != shortKeyProperty.getElement().getValue())
            name += shortKeyProperty.getElement().getValue().substring(0, TransportModule.SHORT_KEY_LENGTH);

         return name;
      } else
         return super.getDisplayNameFor(exportedItem);
   }

}