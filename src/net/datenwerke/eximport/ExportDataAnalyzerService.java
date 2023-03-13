package net.datenwerke.eximport;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.w3c.dom.NodeList;

import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.ItemProperty;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * Helper Service that allows to analize an export file.
 * 
 * @see ExportService
 *
 */
public interface ExportDataAnalyzerService {

   /**
    * Returns the export name of a given {@link Document}
    * 
    * @return The export name of the {@link Document}
    */
   String getExportName(ExportDataProvider dataProvider);

   /**
    * Returns the export description of a given {@link Document}
    * 
    * @return The export description of the {@link Document}
    */
   String getExportDescription(ExportDataProvider dataProvider);

   /**
    * Returns the export {@link Date} of a given {@link Document}
    * 
    * @return The export {@link Date}
    */
   Date getExportDate(ExportDataProvider dataProvider);

   /**
    * Returns a {@link NodeList} of exporter elements of a given {@link Document}
    * 
    * @return A {@link NodeList} of exporter elements
    */
   Elements getExporterElements(ExportDataProvider dataProvider);

   /**
    * Returns a {@link Collection} of exporter {@link Class}es of a given
    * {@link Document}
    * 
    * @return A {@link Collection} of exporter {@link Class}es
    * @throws ClassNotFoundException
    */
   Collection<Class<?>> getExporterClasses(ExportDataProvider dataProvider) throws ClassNotFoundException;

   /**
    * Returns the {@link Class} of the exporter of the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The {@link Class} of the exporter
    * @throws ClassNotFoundException
    */
   Class<?> getExporterClass(Element el) throws ClassNotFoundException;

   /**
    * Get a {@link List} of ExportedItems from the given {@link Document} and
    * {@link Exporter}
    * 
    * @param exporter The {@link Exporter} to use
    * @return A {@link List} of ExportedItems
    * @throws ClassNotFoundException
    */
   List<ExportedItem> getExportedItemsFor(ExportDataProvider dataProvider, Class<? extends Exporter> exporter)
         throws ClassNotFoundException;

   /**
    * Gets the {@link ExportedItem} for the given {@link Element}
    * 
    * @param el           The {@link Element}
    * @param dataProvider
    * @return The {@link ExportedItem}
    * @throws ClassNotFoundException
    */
   ExportedItem getExportedItemFor(Element el, ExportDataProvider dataProvider) throws ClassNotFoundException;

   /**
    * Returns a {@link Collection} of {@link ItemProperty}s for the given
    * {@link Element}
    * 
    * @param el The {@link Element}
    * @return A {@link Collection} of {@link ItemProperty}s
    * @throws ClassNotFoundException
    */
   Collection<ItemProperty> getItemPropertiesFor(final Element el) throws ClassNotFoundException;

   /**
    * Returns the {@link ItemProperty} for the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The {@link ItemProperty}
    * @throws ClassNotFoundException
    */
   ItemProperty getItemPropertyFor(Element el) throws ClassNotFoundException;

   /**
    * Returns the {@link EnclosedItemProperty} for the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The {@link EnclosedItemProperty}
    * @throws ClassNotFoundException
    */
   EnclosedItemProperty getEnclosedPropertyFor(Element el) throws ClassNotFoundException;

   /**
    * Returns a {@link List} of {@link ItemProperty}s for the given
    * {@link Element}s collection value element
    * 
    * @param el The {@link Element}
    * @return A {@link List} of {@link ItemProperty}s
    * @throws ClassNotFoundException
    */
   List<ItemProperty> getCollectionValuesFor(final Element el) throws ClassNotFoundException;

   /**
    * Returns the collection value elements for a given {@link element} as a
    * {@link NodeList}
    * 
    * @param el The {@link Element}
    * @return The collection value elements as a {@link NodeList}
    */
   List<Element> getCollectionValueElementsFor(Element el);

   /**
    * Returns the exported item elements for a {@link Document} with a given
    * {@link Exporter} as a {@link NodeList}
    * 
    */
   Elements getExportedItemElementsFor(ExportDataProvider dataProvider, Class<? extends Exporter> exporter);

   /**
    * Checks wether a given {@link Element} is an exported item element or not
    * 
    * @param el The {@link Element}
    * @return True if the {@link Element} is an exporter item element; False
    *         otherwise
    */
   boolean isExportedItemElement(Element el);

   /**
    * Validates the given exported item {@link Element}
    * 
    * @param el The exported item {@link Element}
    */
   void validateExportedItemElement(Element el);

   /**
    * Returns the ID attribute of the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The ID attribute
    */
   String getItemId(Element el);

   /**
    * Returns the type attribute of the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The type attribute
    */
   String getItemType(Element el);

   /**
    * Returns the type attribute of the given {@link Element} as a {@link Class}
    * 
    * @param el The {@link Element}
    * @return The type attribute as a {@link Class}
    * @throws ClassNotFoundException
    */
   Class<?> getItemTypeAsClass(Element el) throws ClassNotFoundException;

   /**
    * Returns the {@link Element}s property elements as a {@link NodeList}
    * 
    * @param el The {@link Element}
    * @return The property elements as a {@link NodeList}
    */
   List<Element> getItemsPropertyElements(Element el);

   /**
    * Checks wether the given {@link Element} has the given attribute or not
    * 
    * @param el        The {@link Element}
    * @param attribute The attribute
    * @return True if the {@link Element} as the given attribute; False otherwise
    */
   boolean hasAttribute(Element el, String attribute);

   /**
    * Returns the {@link Element}s value of the given attribute
    * 
    * @param el        The {@link Element}
    * @param attribute The attribute
    * @return The value of the given attribute
    */
   String getAttribute(Element el, String attribute);

   /**
    * Gets the {@link ExportedItem} identified by the given ID.
    * 
    * @param id The ID of the {@link ExportedItem}
    * @return The {@link ExportedItem} or <b>null</b>
    * @throws ClassNotFoundException
    */
   ExportedItem getExportedItemById(ExportDataProvider dataProvider, String id) throws ClassNotFoundException;

   /**
    * Gets the {@link ExportedItem} identified by the given ID including enclosed
    * elements.
    * 
    * @param id The ID of the {@link ExportedItem}
    * @return The {@link ExportedItem} including enclosed elements or <b>null</b>
    * @throws ClassNotFoundException
    */
   ExportedItem getExportedItemWithEnclosed(ExportDataProvider dataProvider, String id) throws ClassNotFoundException;

   /**
    * Gets the {@link Class} of the exporter type used for the enclosed elements of
    * the {@link Element} identified by the given ID.
    * 
    * @param id The ID of the exported element
    * @return The {@link Class} of the used exporter type or <b>null</b>
    * @throws ClassNotFoundException
    */
   Class<?> getExporterForEnclosed(ExportDataProvider dataProvider, String id) throws ClassNotFoundException;

   /**
    * Returns the {@link EnclosedItemProperty} for the exported element identified
    * by the given ID.
    * 
    * @param id The ID of the exported Element
    * @return The {@link EnclosedItemProperty} of the exported element
    * @throws ClassNotFoundException
    */
   EnclosedItemProperty getEnclosedPropertyFor(ExportDataProvider dataProvider, String id)
         throws ClassNotFoundException;

   String getRootId(ExportDataProvider dataProvider, Class<? extends Exporter> exporter) throws ClassNotFoundException;

}
