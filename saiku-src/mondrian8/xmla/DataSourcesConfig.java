// 
// Decompiled by Procyon v0.5.36
// 

package mondrian8.xmla;

import org.eigenbase.xom.XMLAttrVector;
import org.eigenbase.xom.XMLOutput;
import java.io.PrintWriter;
import org.eigenbase.xom.NodeDef;
import org.eigenbase.xom.XOMException;
import org.eigenbase.xom.DOMElementParser;
import org.eigenbase.xom.DOMWrapper;
import org.eigenbase.xom.ElementDef;

public class DataSourcesConfig
{
    public static String[] _elements;
    public static final String DataSourceName = "DataSourceName";
    public static final String DataSourceDescription = "DataSourceDescription";
    public static final String URL = "URL";
    public static final String DataSourceInfo = "DataSourceInfo";
    public static final String ProviderName = "ProviderName";
    public static final String ProviderType = "ProviderType";
    public static final String AuthenticationMode = "AuthenticationMode";
    public static final String Definition = "Definition";
    
    public static Class getXMLDefClass() {
        return DataSourcesConfig.class;
    }
    
    static {
        DataSourcesConfig._elements = new String[] { "DataSources", "DataSource", "DataSourceName", "DataSourceDescription", "URL", "DataSourceInfo", "ProviderName", "ProviderType", "AuthenticationMode", "Definition", "Catalogs", "Catalog" };
    }
    
    public static class DataSources extends ElementDef
    {
        public DataSource[] dataSources;
        
        public DataSources() {
        }
        
        public DataSources(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DataSourcesConfig.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)DataSource.class, 0, 0);
                this.dataSources = new DataSource[_tempArray.length];
                for (int _i = 0; _i < this.dataSources.length; ++_i) {
                    this.dataSources[_i] = (DataSource)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "DataSources";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "dataSources", (NodeDef[])this.dataSources, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("DataSources", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.dataSources);
            _out.endTag("DataSources");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final DataSources _cother = (DataSources)_other;
            final boolean _diff = displayElementArrayDiff("dataSources", (NodeDef[])this.dataSources, (NodeDef[])_cother.dataSources, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class DataSource extends ElementDef
    {
        public String name;
        public String description;
        public String url;
        public String dataSourceInfo;
        public String providerName;
        public String providerType;
        public String authenticationMode;
        public Catalogs catalogs;
        public static final String PROVIDER_TYPE_TDP = "TDP";
        public static final String PROVIDER_TYPE_MDP = "MDP";
        public static final String PROVIDER_TYPE_DMP = "DMP";
        public static final String AUTH_MODE_UNAUTHENTICATED = "Unauthenticated";
        public static final String AUTH_MODE_AUTHENTICATED = "Authenticated";
        public static final String AUTH_MODE_INTEGRATED = "Integrated";
        
        public DataSource() {
        }
        
        public DataSource(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DataSourcesConfig.class);
                this.name = _parser.getString("DataSourceName", true);
                this.description = _parser.getString("DataSourceDescription", true);
                this.url = _parser.getString("URL", true);
                this.dataSourceInfo = _parser.getString("DataSourceInfo", true);
                this.providerName = _parser.getString("ProviderName", true);
                this.providerType = _parser.getString("ProviderType", true);
                this.authenticationMode = _parser.getString("AuthenticationMode", true);
                this.catalogs = (Catalogs)_parser.getElement((Class)Catalogs.class, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "DataSource";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayString(_out, "name", this.name, _indent + 1);
            displayString(_out, "description", this.description, _indent + 1);
            displayString(_out, "url", this.url, _indent + 1);
            displayString(_out, "dataSourceInfo", this.dataSourceInfo, _indent + 1);
            displayString(_out, "providerName", this.providerName, _indent + 1);
            displayString(_out, "providerType", this.providerType, _indent + 1);
            displayString(_out, "authenticationMode", this.authenticationMode, _indent + 1);
            displayElement(_out, "catalogs", (ElementDef)this.catalogs, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("DataSource", new XMLAttrVector());
            displayXMLString(_out, "DataSourceName", this.name);
            displayXMLString(_out, "DataSourceDescription", this.description);
            displayXMLString(_out, "URL", this.url);
            displayXMLString(_out, "DataSourceInfo", this.dataSourceInfo);
            displayXMLString(_out, "ProviderName", this.providerName);
            displayXMLString(_out, "ProviderType", this.providerType);
            displayXMLString(_out, "AuthenticationMode", this.authenticationMode);
            displayXMLElement(_out, (ElementDef)this.catalogs);
            _out.endTag("DataSource");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final DataSource _cother = (DataSource)_other;
            boolean _diff = displayStringDiff("name", this.name, _cother.name, _out, _indent + 1);
            _diff = (_diff && displayStringDiff("description", this.description, _cother.description, _out, _indent + 1));
            _diff = (_diff && displayStringDiff("url", this.url, _cother.url, _out, _indent + 1));
            _diff = (_diff && displayStringDiff("dataSourceInfo", this.dataSourceInfo, _cother.dataSourceInfo, _out, _indent + 1));
            _diff = (_diff && displayStringDiff("providerName", this.providerName, _cother.providerName, _out, _indent + 1));
            _diff = (_diff && displayStringDiff("providerType", this.providerType, _cother.providerType, _out, _indent + 1));
            _diff = (_diff && displayStringDiff("authenticationMode", this.authenticationMode, _cother.authenticationMode, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("catalogs", (NodeDef)this.catalogs, (NodeDef)_cother.catalogs, _out, _indent + 1));
            return _diff;
        }
        
        public String getDataSourceName() {
            return this.name;
        }
        
        public String getDataSourceDescription() {
            return this.description;
        }
        
        public String getURL() {
            return this.url;
        }
        
        public String getDataSourceInfo() {
            return this.dataSourceInfo;
        }
        
        public String getProviderName() {
            return this.providerName;
        }
        
        public String[] getProviderType() {
            return new String[] { "MDP" };
        }
        
        public String getAuthenticationMode() {
            return this.authenticationMode;
        }
    }
    
    public static class Catalogs extends ElementDef
    {
        public Catalog[] catalogs;
        
        public Catalogs() {
        }
        
        public Catalogs(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DataSourcesConfig.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)Catalog.class, 0, 0);
                this.catalogs = new Catalog[_tempArray.length];
                for (int _i = 0; _i < this.catalogs.length; ++_i) {
                    this.catalogs[_i] = (Catalog)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Catalogs";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "catalogs", (NodeDef[])this.catalogs, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Catalogs", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.catalogs);
            _out.endTag("Catalogs");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Catalogs _cother = (Catalogs)_other;
            final boolean _diff = displayElementArrayDiff("catalogs", (NodeDef[])this.catalogs, (NodeDef[])_cother.catalogs, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class Catalog extends ElementDef
    {
        public String name;
        public String dataSourceInfo;
        public String definition;
        
        public Catalog() {
        }
        
        public Catalog(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DataSourcesConfig.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.dataSourceInfo = _parser.getString("DataSourceInfo", false);
                this.definition = _parser.getString("Definition", true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Catalog";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayString(_out, "dataSourceInfo", this.dataSourceInfo, _indent + 1);
            displayString(_out, "definition", this.definition, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Catalog", new XMLAttrVector().add("name", (Object)this.name));
            displayXMLString(_out, "DataSourceInfo", this.dataSourceInfo);
            displayXMLString(_out, "Definition", this.definition);
            _out.endTag("Catalog");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Catalog _cother = (Catalog)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayStringDiff("dataSourceInfo", this.dataSourceInfo, _cother.dataSourceInfo, _out, _indent + 1));
            _diff = (_diff && displayStringDiff("definition", this.definition, _cother.definition, _out, _indent + 1));
            return _diff;
        }
    }
}
