package net.datenwerke.rs.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.inject.Provider;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import net.datenwerke.rs.utils.xml.annotations.DisableXMLValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.google.inject.Inject;

public class XMLUtilsServiceImpl implements XMLUtilsService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final Provider<Boolean> disableValidation;
	
	@Inject
	public XMLUtilsServiceImpl(
		@DisableXMLValidation Provider<Boolean> disableValidation) 
		{

		/* store objects */
		this.disableValidation = disableValidation;
	}
	
	public XMLUtilsServiceImpl(final Boolean disableValidation) 
		{

		/* store objects */
		this.disableValidation = new Provider<Boolean>() {
			@Override
			public Boolean get() {
				return disableValidation;
			}
		};
	}
	/**
	 * Reads a file into a JAXP XML document.
	 */
	public Document readInputFileIntoJAXPDoc(File file) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setNamespaceAware(true);
		
		docBuilderFactory.setValidating(! disableValidation.get());
		
		DocumentBuilder docBuilder;
        Document doc = null;
       
		docBuilder = docBuilderFactory.newDocumentBuilder();
		
		doc = docBuilder.parse(file);
	    
        return doc;
	}
	
	/**
	 * Reads a file into a JAXP XML document.
	 */
	public Document readStringIntoJAXPDoc(String data) throws ParserConfigurationException, SAXException, IOException {
		return readInputStreamIntoJAXPDoc(new ByteArrayInputStream(data.getBytes()));
	}
	
	/**
	 * Reads a file into a JAXP XML document.
	 */
	public Document readStringIntoJAXPDoc(String data, String encoding) throws ParserConfigurationException, SAXException, IOException {
		return readInputStreamIntoJAXPDoc(new ByteArrayInputStream(data.getBytes(encoding)));
	}
	
	/**
	 * Reads an InputStream into a JAXP XML document.
	 */
	public Document readInputStreamIntoJAXPDoc(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		
		docBuilderFactory.setValidating(! disableValidation.get());
		
        docBuilderFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder;
        Document doc = null;
       
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse(in);
	    
        return doc;
	}
	
	/**
	 * Creates an empty JAXP XML document.
	 * 
	 * @return An empty JAXP XML document.
	 */
	public Document getNewEmptyJAXPDoc(){
		DocumentBuilderFactory documentBuilderFactory =	DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder;

		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			return documentBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			logger.warn( e.getMessage(), e);
		}
		
		return null;
	}
	
	public String getXMLNodeAsString(Node node){
		return getXMLNodeAsString(node, false);
	}
	
	/**
	 * Transforms an XML node (from a JAXP XML document) into a formatted string.
	 * 
	 * @param node The node to transform.
	 * @return A string representation of the node. 
	 */
	public String getXMLNodeAsString(Node node, boolean omitXMLDeclaration){
		StringWriter writer = new StringWriter();
		
		Transformer serializer;

		try {
			TransformerFactory tf = TransformerFactory.newInstance(); 
			
			serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8"); //$NON-NLS-1$
			serializer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
			serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, omitXMLDeclaration ? "yes" : "no"); //$NON-NLS-1$ //$NON-NLS-2$
			serializer.transform(new DOMSource(node), new StreamResult(writer));
		} catch (TransformerConfigurationException e) {
			logger.warn( e.getMessage(), e);
		} catch (TransformerFactoryConfigurationError e) {
			logger.warn( e.getMessage(), e);
		} catch (TransformerException e) {
			logger.warn( e.getMessage(), e);
		}
		
		return writer.toString().replace("\r\n", "\n");
	}
	
	public String xsltTranform(Source xsltSource, Source xmlSource) {
		StringWriter writer = new StringWriter();
		
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer(xsltSource);
			
			trans.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
			trans.setOutputProperty(OutputKeys.ENCODING, "utf-8"); //$NON-NLS-1$
			trans.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
			trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
		
			trans.transform(xmlSource, new StreamResult(writer));
		
		} catch (TransformerException e) {
			logger.warn( e.getMessage(), e);
		}
		
		return writer.toString();
	}
	
	@Override
	public XPathExpression getXPath(String xpath) throws XPathException {
		XPathFactory factory = XPathFactory.newInstance();
		return factory.newXPath().compile(xpath);
	}
	
	@Override
	public XPathExpression getXPath(String xpath, NamespaceContext namespaceContext) throws XPathException {
		XPathFactory factory = XPathFactory.newInstance();
		
		XPath xpathExpression = factory.newXPath();
		xpathExpression.setNamespaceContext(namespaceContext);
		
		return xpathExpression.compile(xpath);
	}
	
}
	
