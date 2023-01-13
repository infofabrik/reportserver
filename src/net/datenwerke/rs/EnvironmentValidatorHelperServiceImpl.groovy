package net.datenwerke.rs;

import javax.xml.parsers.ParserConfigurationException
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathException
import javax.xml.xpath.XPathFactory

import org.w3c.dom.Document
import org.xml.sax.SAXException

import com.google.inject.Inject

import groovy.sql.Sql
import java.sql.SQLException
import net.datenwerke.rs.configservice.service.configservice.ConfigDirService
import net.datenwerke.rs.configservice.service.configservice.ConfigDirServiceImpl
import net.datenwerke.rs.utils.xml.SimpleNamespaceContext
import net.datenwerke.rs.utils.xml.XMLUtilsServiceImpl

public class EnvironmentValidatorHelperServiceImpl implements EnvironmentValidatorHelperService {   
   
   @Override
   public String getSchemaVersion() throws SQLException {
      def schemaVersion = null
      def jpaProperties = getJpaProperties()
      def db = [
         url: jpaProperties['hibernate.connection.url'],
         user: jpaProperties['hibernate.connection.username'],
         password: jpaProperties['hibernate.connection.password'],
         driver: jpaProperties['hibernate.connection.driver_class']
      ]
      Sql.newInstance(db.url, db.user, db.password, db.driver).withCloseable { sql ->
         def rows = sql.rows("SELECT * FROM RS_SCHEMAINFO WHERE KEY_FIELD = 'schemaversion' ORDER BY ENTITY_ID DESC")
         if (rows.size())
            schemaVersion = rows[0].value
      }
      if (!schemaVersion)
         return 'No version number found. Did you forget a commit during installation?'
         
      return schemaVersion
   }
   
   @Override   
   public Properties getJpaProperties() {
      def propkeys = [
         'hibernate.dialect', ,
         'hibernate.connection.driver_class',
         'hibernate.connection.url',
         'hibernate.connection.username',
         'hibernate.connection.password',
         'hibernate.default_schema'
      ]
      Properties jpaProperties = new Properties()
      
      try { /* persistence.xml  */
         InputStream pxmlstream = Thread.currentThread().getContextClassLoader()
               .getResourceAsStream("META-INF/persistence.xml");
         if (null != pxmlstream) {
            XMLUtilsServiceImpl xml = new XMLUtilsServiceImpl(true);
            Document pxml = xml.readInputStreamIntoJAXPDoc(pxmlstream);
            
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            xpath.setNamespaceContext(new SimpleNamespaceContext("p", "http://java.sun.com/xml/ns/persistence"));
            
            for (String propkey : propkeys) {
               String xpstr = ("/p:persistence/p:persistence-unit[@name='" + ReportServerPUModule.JPA_UNIT_NAME
                     + "']/p:properties/p:property[@name='" + propkey + "']/@value");
               String val = xpath.evaluate(xpstr, pxml);
               jpaProperties.setProperty(propkey, val);
            }
         }
      } catch (ParserConfigurationException e1) {
         e1.printStackTrace();
      } catch (SAXException e1) {
         e1.printStackTrace();
      } catch (IOException e1) {
         e1.printStackTrace();
      } catch (XPathException e) {
         e.printStackTrace();
      }
      
      ConfigDirService configDirService = new ConfigDirServiceImpl(null);
      ReportServerPUStartup.loadPersistenceProperties(configDirService, jpaProperties);
      
      return jpaProperties;
   }

}
