// 
// Decompiled by Procyon v0.5.36
// 

package mondrian8.olap;

import java.util.HashMap;
import java.util.Map;
import mondrian8.rolap.sql.SqlQuery;
import mondrian8.spi.Dialect;
import org.eigenbase.xom.XMLAttrVector;
import org.eigenbase.xom.XMLOutput;
import java.io.PrintWriter;
import org.eigenbase.xom.NodeDef;
import org.eigenbase.xom.XOMException;
import org.eigenbase.xom.DOMElementParser;
import org.eigenbase.xom.DOMWrapper;
import org.eigenbase.xom.ElementDef;

public class MondrianDef
{
    public static String[] _elements;
    
    public static Class getXMLDefClass() {
        return MondrianDef.class;
    }
    
    static {
        MondrianDef._elements = new String[] { "Schema", "CubeDimension", "Cube", "VirtualCube", "CubeUsages", "CubeUsage", "VirtualCubeDimension", "VirtualCubeMeasure", "DimensionUsage", "Dimension", "Hierarchy", "Level", "Closure", "Property", "Measure", "CalculatedMember", "CalculatedMemberProperty", "NamedSet", "Formula", "MemberReaderParameter", "RelationOrJoin", "Relation", "View", "SQL", "Join", "Table", "Hint", "InlineTable", "ColumnDefs", "ColumnDef", "Rows", "Row", "Value", "AggTable", "AggName", "AggPattern", "AggExclude", "AggColumnName", "AggFactCount", "AggMeasureFactCount", "AggIgnoreColumn", "AggForeignKey", "AggLevel", "AggLevelProperty", "AggMeasure", "Expression", "Column", "ExpressionView", "KeyExpression", "ParentExpression", "OrdinalExpression", "NameExpression", "CaptionExpression", "MeasureExpression", "Role", "Grant", "SchemaGrant", "CubeGrant", "DimensionGrant", "HierarchyGrant", "MemberGrant", "Union", "RoleUsage", "UserDefinedFunction", "Parameter", "Annotations", "Annotation", "Script", "ElementFormatter", "CellFormatter", "MemberFormatter", "PropertyFormatter" };
    }
    
    public static class Schema extends ElementDef
    {
        public String name;
        public String description;
        public String measuresCaption;
        public String defaultRole;
        public Annotations annotations;
        public Parameter[] parameters;
        public Dimension[] dimensions;
        public Cube[] cubes;
        public VirtualCube[] virtualCubes;
        public NamedSet[] namedSets;
        public Role[] roles;
        public UserDefinedFunction[] userDefinedFunctions;
        
        public Schema() {
        }
        
        public Schema(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.measuresCaption = (String)_parser.getAttribute("measuresCaption", "String", (String)null, (String[])null, false);
                this.defaultRole = (String)_parser.getAttribute("defaultRole", "String", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                NodeDef[] _tempArray = _parser.getArray((Class)Parameter.class, 0, 0);
                this.parameters = new Parameter[_tempArray.length];
                for (int _i = 0; _i < this.parameters.length; ++_i) {
                    this.parameters[_i] = (Parameter)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)Dimension.class, 0, 0);
                this.dimensions = new Dimension[_tempArray.length];
                for (int _i = 0; _i < this.dimensions.length; ++_i) {
                    this.dimensions[_i] = (Dimension)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)Cube.class, 0, 0);
                this.cubes = new Cube[_tempArray.length];
                for (int _i = 0; _i < this.cubes.length; ++_i) {
                    this.cubes[_i] = (Cube)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)VirtualCube.class, 0, 0);
                this.virtualCubes = new VirtualCube[_tempArray.length];
                for (int _i = 0; _i < this.virtualCubes.length; ++_i) {
                    this.virtualCubes[_i] = (VirtualCube)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)NamedSet.class, 0, 0);
                this.namedSets = new NamedSet[_tempArray.length];
                for (int _i = 0; _i < this.namedSets.length; ++_i) {
                    this.namedSets[_i] = (NamedSet)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)Role.class, 0, 0);
                this.roles = new Role[_tempArray.length];
                for (int _i = 0; _i < this.roles.length; ++_i) {
                    this.roles[_i] = (Role)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)UserDefinedFunction.class, 0, 0);
                this.userDefinedFunctions = new UserDefinedFunction[_tempArray.length];
                for (int _i = 0; _i < this.userDefinedFunctions.length; ++_i) {
                    this.userDefinedFunctions[_i] = (UserDefinedFunction)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Schema";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "measuresCaption", (Object)this.measuresCaption, _indent + 1);
            displayAttribute(_out, "defaultRole", (Object)this.defaultRole, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElementArray(_out, "parameters", (NodeDef[])this.parameters, _indent + 1);
            displayElementArray(_out, "dimensions", (NodeDef[])this.dimensions, _indent + 1);
            displayElementArray(_out, "cubes", (NodeDef[])this.cubes, _indent + 1);
            displayElementArray(_out, "virtualCubes", (NodeDef[])this.virtualCubes, _indent + 1);
            displayElementArray(_out, "namedSets", (NodeDef[])this.namedSets, _indent + 1);
            displayElementArray(_out, "roles", (NodeDef[])this.roles, _indent + 1);
            displayElementArray(_out, "userDefinedFunctions", (NodeDef[])this.userDefinedFunctions, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Schema", new XMLAttrVector().add("name", (Object)this.name).add("description", (Object)this.description).add("measuresCaption", (Object)this.measuresCaption).add("defaultRole", (Object)this.defaultRole));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElementArray(_out, (NodeDef[])this.parameters);
            displayXMLElementArray(_out, (NodeDef[])this.dimensions);
            displayXMLElementArray(_out, (NodeDef[])this.cubes);
            displayXMLElementArray(_out, (NodeDef[])this.virtualCubes);
            displayXMLElementArray(_out, (NodeDef[])this.namedSets);
            displayXMLElementArray(_out, (NodeDef[])this.roles);
            displayXMLElementArray(_out, (NodeDef[])this.userDefinedFunctions);
            _out.endTag("Schema");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Schema _cother = (Schema)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("measuresCaption", (Object)this.measuresCaption, (Object)_cother.measuresCaption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("defaultRole", (Object)this.defaultRole, (Object)_cother.defaultRole, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("parameters", (NodeDef[])this.parameters, (NodeDef[])_cother.parameters, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("dimensions", (NodeDef[])this.dimensions, (NodeDef[])_cother.dimensions, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("cubes", (NodeDef[])this.cubes, (NodeDef[])_cother.cubes, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("virtualCubes", (NodeDef[])this.virtualCubes, (NodeDef[])_cother.virtualCubes, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("namedSets", (NodeDef[])this.namedSets, (NodeDef[])_cother.namedSets, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("roles", (NodeDef[])this.roles, (NodeDef[])_cother.roles, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("userDefinedFunctions", (NodeDef[])this.userDefinedFunctions, (NodeDef[])_cother.userDefinedFunctions, _out, _indent + 1));
            return _diff;
        }
        
        Cube getCube(final String cubeName) {
            for (int i = 0; i < this.cubes.length; ++i) {
                if (this.cubes[i].name.equals(cubeName)) {
                    return this.cubes[i];
                }
            }
            throw Util.newInternal("Cannot find cube '" + cubeName + "'");
        }
        
        Dimension getPublicDimension(final String dimensionName) {
            for (int i = 0; i < this.dimensions.length; ++i) {
                if (this.dimensions[i].name.equals(dimensionName)) {
                    return this.dimensions[i];
                }
            }
            throw Util.newInternal("Cannot find public dimension '" + dimensionName + "'");
        }
    }
    
    public abstract static class CubeDimension extends ElementDef
    {
        public String name;
        public String caption;
        public Boolean visible;
        public String description;
        public String foreignKey;
        public Boolean highCardinality;
        public Annotations annotations;
        
        public CubeDimension() {
        }
        
        public CubeDimension(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", "true", (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.foreignKey = (String)_parser.getAttribute("foreignKey", "String", (String)null, (String[])null, false);
                this.highCardinality = (Boolean)_parser.getAttribute("highCardinality", "Boolean", "false", (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "CubeDimension";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "foreignKey", (Object)this.foreignKey, _indent + 1);
            displayAttribute(_out, "highCardinality", (Object)this.highCardinality, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CubeDimension", new XMLAttrVector().add("name", (Object)this.name).add("caption", (Object)this.caption).add("visible", (Object)this.visible).add("description", (Object)this.description).add("foreignKey", (Object)this.foreignKey).add("highCardinality", (Object)this.highCardinality));
            displayXMLElement(_out, (ElementDef)this.annotations);
            _out.endTag("CubeDimension");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CubeDimension _cother = (CubeDimension)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("visible", (Object)this.visible, (Object)_cother.visible, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("foreignKey", (Object)this.foreignKey, (Object)_cother.foreignKey, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("highCardinality", (Object)this.highCardinality, (Object)_cother.highCardinality, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            return _diff;
        }
        
        public abstract Dimension getDimension(final Schema p0);
    }
    
    public static class Cube extends ElementDef
    {
        public String name;
        public String caption;
        public Boolean visible;
        public String description;
        public String defaultMeasure;
        public Boolean cache;
        public Boolean enabled;
        public Annotations annotations;
        public Relation fact;
        public CubeDimension[] dimensions;
        public Measure[] measures;
        public CalculatedMember[] calculatedMembers;
        public NamedSet[] namedSets;
        
        public Cube() {
        }
        
        public Cube(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", "true", (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.defaultMeasure = (String)_parser.getAttribute("defaultMeasure", "String", (String)null, (String[])null, false);
                this.cache = (Boolean)_parser.getAttribute("cache", "Boolean", "true", (String[])null, false);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                this.fact = (Relation)_parser.getElement((Class)Relation.class, true);
                NodeDef[] _tempArray = _parser.getArray((Class)CubeDimension.class, 0, 0);
                this.dimensions = new CubeDimension[_tempArray.length];
                for (int _i = 0; _i < this.dimensions.length; ++_i) {
                    this.dimensions[_i] = (CubeDimension)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)Measure.class, 0, 0);
                this.measures = new Measure[_tempArray.length];
                for (int _i = 0; _i < this.measures.length; ++_i) {
                    this.measures[_i] = (Measure)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)CalculatedMember.class, 0, 0);
                this.calculatedMembers = new CalculatedMember[_tempArray.length];
                for (int _i = 0; _i < this.calculatedMembers.length; ++_i) {
                    this.calculatedMembers[_i] = (CalculatedMember)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)NamedSet.class, 0, 0);
                this.namedSets = new NamedSet[_tempArray.length];
                for (int _i = 0; _i < this.namedSets.length; ++_i) {
                    this.namedSets[_i] = (NamedSet)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Cube";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "defaultMeasure", (Object)this.defaultMeasure, _indent + 1);
            displayAttribute(_out, "cache", (Object)this.cache, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElement(_out, "fact", (ElementDef)this.fact, _indent + 1);
            displayElementArray(_out, "dimensions", (NodeDef[])this.dimensions, _indent + 1);
            displayElementArray(_out, "measures", (NodeDef[])this.measures, _indent + 1);
            displayElementArray(_out, "calculatedMembers", (NodeDef[])this.calculatedMembers, _indent + 1);
            displayElementArray(_out, "namedSets", (NodeDef[])this.namedSets, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Cube", new XMLAttrVector().add("name", (Object)this.name).add("caption", (Object)this.caption).add("visible", (Object)this.visible).add("description", (Object)this.description).add("defaultMeasure", (Object)this.defaultMeasure).add("cache", (Object)this.cache).add("enabled", (Object)this.enabled));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElement(_out, (ElementDef)this.fact);
            displayXMLElementArray(_out, (NodeDef[])this.dimensions);
            displayXMLElementArray(_out, (NodeDef[])this.measures);
            displayXMLElementArray(_out, (NodeDef[])this.calculatedMembers);
            displayXMLElementArray(_out, (NodeDef[])this.namedSets);
            _out.endTag("Cube");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Cube _cother = (Cube)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("visible", (Object)this.visible, (Object)_cother.visible, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("defaultMeasure", (Object)this.defaultMeasure, (Object)_cother.defaultMeasure, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("cache", (Object)this.cache, (Object)_cother.cache, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("enabled", (Object)this.enabled, (Object)_cother.enabled, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("fact", (NodeDef)this.fact, (NodeDef)_cother.fact, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("dimensions", (NodeDef[])this.dimensions, (NodeDef[])_cother.dimensions, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measures", (NodeDef[])this.measures, (NodeDef[])_cother.measures, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("calculatedMembers", (NodeDef[])this.calculatedMembers, (NodeDef[])_cother.calculatedMembers, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("namedSets", (NodeDef[])this.namedSets, (NodeDef[])_cother.namedSets, _out, _indent + 1));
            return _diff;
        }
        
        public boolean isEnabled() {
            return this.enabled;
        }
        
        Dimension getDimension(final Schema xmlSchema, final String dimensionName) {
            for (int i = 0; i < this.dimensions.length; ++i) {
                if (this.dimensions[i].name.equals(dimensionName)) {
                    return this.dimensions[i].getDimension(xmlSchema);
                }
            }
            throw Util.newInternal("Cannot find dimension '" + dimensionName + "' in cube '" + this.name + "'");
        }
    }
    
    public static class VirtualCube extends ElementDef
    {
        public Boolean enabled;
        public String name;
        public String defaultMeasure;
        public String caption;
        public Boolean visible;
        public String description;
        public Annotations annotations;
        public CubeUsages cubeUsage;
        public VirtualCubeDimension[] dimensions;
        public VirtualCubeMeasure[] measures;
        public CalculatedMember[] calculatedMembers;
        public NamedSet[] namedSets;
        
        public VirtualCube() {
        }
        
        public VirtualCube(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, false);
                this.defaultMeasure = (String)_parser.getAttribute("defaultMeasure", "String", (String)null, (String[])null, false);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", "true", (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                this.cubeUsage = (CubeUsages)_parser.getElement((Class)CubeUsages.class, false);
                NodeDef[] _tempArray = _parser.getArray((Class)VirtualCubeDimension.class, 0, 0);
                this.dimensions = new VirtualCubeDimension[_tempArray.length];
                for (int _i = 0; _i < this.dimensions.length; ++_i) {
                    this.dimensions[_i] = (VirtualCubeDimension)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)VirtualCubeMeasure.class, 0, 0);
                this.measures = new VirtualCubeMeasure[_tempArray.length];
                for (int _i = 0; _i < this.measures.length; ++_i) {
                    this.measures[_i] = (VirtualCubeMeasure)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)CalculatedMember.class, 0, 0);
                this.calculatedMembers = new CalculatedMember[_tempArray.length];
                for (int _i = 0; _i < this.calculatedMembers.length; ++_i) {
                    this.calculatedMembers[_i] = (CalculatedMember)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)NamedSet.class, 0, 0);
                this.namedSets = new NamedSet[_tempArray.length];
                for (int _i = 0; _i < this.namedSets.length; ++_i) {
                    this.namedSets[_i] = (NamedSet)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "VirtualCube";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "defaultMeasure", (Object)this.defaultMeasure, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElement(_out, "cubeUsage", (ElementDef)this.cubeUsage, _indent + 1);
            displayElementArray(_out, "dimensions", (NodeDef[])this.dimensions, _indent + 1);
            displayElementArray(_out, "measures", (NodeDef[])this.measures, _indent + 1);
            displayElementArray(_out, "calculatedMembers", (NodeDef[])this.calculatedMembers, _indent + 1);
            displayElementArray(_out, "namedSets", (NodeDef[])this.namedSets, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("VirtualCube", new XMLAttrVector().add("enabled", (Object)this.enabled).add("name", (Object)this.name).add("defaultMeasure", (Object)this.defaultMeasure).add("caption", (Object)this.caption).add("visible", (Object)this.visible).add("description", (Object)this.description));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElement(_out, (ElementDef)this.cubeUsage);
            displayXMLElementArray(_out, (NodeDef[])this.dimensions);
            displayXMLElementArray(_out, (NodeDef[])this.measures);
            displayXMLElementArray(_out, (NodeDef[])this.calculatedMembers);
            displayXMLElementArray(_out, (NodeDef[])this.namedSets);
            _out.endTag("VirtualCube");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final VirtualCube _cother = (VirtualCube)_other;
            boolean _diff = displayAttributeDiff("enabled", (Object)this.enabled, (Object)_cother.enabled, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("defaultMeasure", (Object)this.defaultMeasure, (Object)_cother.defaultMeasure, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("visible", (Object)this.visible, (Object)_cother.visible, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("cubeUsage", (NodeDef)this.cubeUsage, (NodeDef)_cother.cubeUsage, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("dimensions", (NodeDef[])this.dimensions, (NodeDef[])_cother.dimensions, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measures", (NodeDef[])this.measures, (NodeDef[])_cother.measures, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("calculatedMembers", (NodeDef[])this.calculatedMembers, (NodeDef[])_cother.calculatedMembers, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("namedSets", (NodeDef[])this.namedSets, (NodeDef[])_cother.namedSets, _out, _indent + 1));
            return _diff;
        }
        
        public boolean isEnabled() {
            return this.enabled;
        }
    }
    
    public static class CubeUsages extends ElementDef
    {
        public CubeUsage[] cubeUsages;
        
        public CubeUsages() {
        }
        
        public CubeUsages(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)CubeUsage.class, 1, 0);
                this.cubeUsages = new CubeUsage[_tempArray.length];
                for (int _i = 0; _i < this.cubeUsages.length; ++_i) {
                    this.cubeUsages[_i] = (CubeUsage)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "CubeUsages";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "cubeUsages", (NodeDef[])this.cubeUsages, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CubeUsages", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.cubeUsages);
            _out.endTag("CubeUsages");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CubeUsages _cother = (CubeUsages)_other;
            final boolean _diff = displayElementArrayDiff("cubeUsages", (NodeDef[])this.cubeUsages, (NodeDef[])_cother.cubeUsages, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class CubeUsage extends ElementDef
    {
        public String cubeName;
        public Boolean ignoreUnrelatedDimensions;
        
        public CubeUsage() {
        }
        
        public CubeUsage(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.cubeName = (String)_parser.getAttribute("cubeName", "String", (String)null, (String[])null, true);
                this.ignoreUnrelatedDimensions = (Boolean)_parser.getAttribute("ignoreUnrelatedDimensions", "Boolean", "false", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "CubeUsage";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "cubeName", (Object)this.cubeName, _indent + 1);
            displayAttribute(_out, "ignoreUnrelatedDimensions", (Object)this.ignoreUnrelatedDimensions, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CubeUsage", new XMLAttrVector().add("cubeName", (Object)this.cubeName).add("ignoreUnrelatedDimensions", (Object)this.ignoreUnrelatedDimensions));
            _out.endTag("CubeUsage");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CubeUsage _cother = (CubeUsage)_other;
            boolean _diff = displayAttributeDiff("cubeName", (Object)this.cubeName, (Object)_cother.cubeName, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("ignoreUnrelatedDimensions", (Object)this.ignoreUnrelatedDimensions, (Object)_cother.ignoreUnrelatedDimensions, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class VirtualCubeDimension extends CubeDimension
    {
        public String cubeName;
        
        public VirtualCubeDimension() {
        }
        
        public VirtualCubeDimension(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.cubeName = (String)_parser.getAttribute("cubeName", "String", (String)null, (String[])null, false);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", "true", (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.foreignKey = (String)_parser.getAttribute("foreignKey", "String", (String)null, (String[])null, false);
                this.highCardinality = (Boolean)_parser.getAttribute("highCardinality", "Boolean", "false", (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "VirtualCubeDimension";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "cubeName", (Object)this.cubeName, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "foreignKey", (Object)this.foreignKey, _indent + 1);
            displayAttribute(_out, "highCardinality", (Object)this.highCardinality, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("VirtualCubeDimension", new XMLAttrVector().add("cubeName", (Object)this.cubeName).add("caption", (Object)this.caption).add("visible", (Object)this.visible).add("description", (Object)this.description).add("foreignKey", (Object)this.foreignKey).add("highCardinality", (Object)this.highCardinality).add("name", (Object)this.name));
            displayXMLElement(_out, (ElementDef)this.annotations);
            _out.endTag("VirtualCubeDimension");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final VirtualCubeDimension _cother = (VirtualCubeDimension)_other;
            boolean _diff = displayAttributeDiff("cubeName", (Object)this.cubeName, (Object)_cother.cubeName, _out, _indent + 1);
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            return _diff;
        }
        
        @Override
        public Dimension getDimension(final Schema schema) {
            Util.assertPrecondition(schema != null, "schema != null");
            if (this.cubeName == null) {
                return schema.getPublicDimension(this.name);
            }
            final Cube cube = schema.getCube(this.cubeName);
            return cube.getDimension(schema, this.name);
        }
    }
    
    public static class VirtualCubeMeasure extends ElementDef
    {
        public String cubeName;
        public String name;
        public Boolean visible;
        public Annotations annotations;
        
        public VirtualCubeMeasure() {
        }
        
        public VirtualCubeMeasure(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.cubeName = (String)_parser.getAttribute("cubeName", "String", (String)null, (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "VirtualCubeMeasure";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "cubeName", (Object)this.cubeName, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("VirtualCubeMeasure", new XMLAttrVector().add("cubeName", (Object)this.cubeName).add("name", (Object)this.name).add("visible", (Object)this.visible));
            displayXMLElement(_out, (ElementDef)this.annotations);
            _out.endTag("VirtualCubeMeasure");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final VirtualCubeMeasure _cother = (VirtualCubeMeasure)_other;
            boolean _diff = displayAttributeDiff("cubeName", (Object)this.cubeName, (Object)_cother.cubeName, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("visible", (Object)this.visible, (Object)_cother.visible, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class DimensionUsage extends CubeDimension
    {
        public String source;
        public String level;
        public String usagePrefix;
        
        public DimensionUsage() {
        }
        
        public DimensionUsage(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.source = (String)_parser.getAttribute("source", "String", (String)null, (String[])null, true);
                this.level = (String)_parser.getAttribute("level", "String", (String)null, (String[])null, false);
                this.usagePrefix = (String)_parser.getAttribute("usagePrefix", "String", (String)null, (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", "true", (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.foreignKey = (String)_parser.getAttribute("foreignKey", "String", (String)null, (String[])null, false);
                this.highCardinality = (Boolean)_parser.getAttribute("highCardinality", "Boolean", "false", (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "DimensionUsage";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "source", (Object)this.source, _indent + 1);
            displayAttribute(_out, "level", (Object)this.level, _indent + 1);
            displayAttribute(_out, "usagePrefix", (Object)this.usagePrefix, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "foreignKey", (Object)this.foreignKey, _indent + 1);
            displayAttribute(_out, "highCardinality", (Object)this.highCardinality, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("DimensionUsage", new XMLAttrVector().add("source", (Object)this.source).add("level", (Object)this.level).add("usagePrefix", (Object)this.usagePrefix).add("name", (Object)this.name).add("caption", (Object)this.caption).add("visible", (Object)this.visible).add("description", (Object)this.description).add("foreignKey", (Object)this.foreignKey).add("highCardinality", (Object)this.highCardinality));
            displayXMLElement(_out, (ElementDef)this.annotations);
            _out.endTag("DimensionUsage");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final DimensionUsage _cother = (DimensionUsage)_other;
            boolean _diff = displayAttributeDiff("source", (Object)this.source, (Object)_cother.source, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("level", (Object)this.level, (Object)_cother.level, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("usagePrefix", (Object)this.usagePrefix, (Object)_cother.usagePrefix, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            return _diff;
        }
        
        @Override
        public Dimension getDimension(final Schema schema) {
            Util.assertPrecondition(schema != null, "schema != null");
            for (int i = 0; i < schema.dimensions.length; ++i) {
                if (schema.dimensions[i].name.equals(this.source)) {
                    return schema.dimensions[i];
                }
            }
            throw Util.newInternal("Cannot find shared dimension '" + this.source + "'");
        }
    }
    
    public static class Dimension extends CubeDimension
    {
        public static final String[] _type_values;
        public String type;
        public String usagePrefix;
        public Hierarchy[] hierarchies;
        
        public Dimension() {
        }
        
        public Dimension(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.type = (String)_parser.getAttribute("type", "String", (String)null, Dimension._type_values, false);
                this.usagePrefix = (String)_parser.getAttribute("usagePrefix", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", "true", (String[])null, false);
                this.foreignKey = (String)_parser.getAttribute("foreignKey", "String", (String)null, (String[])null, false);
                this.highCardinality = (Boolean)_parser.getAttribute("highCardinality", "Boolean", "false", (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)Hierarchy.class, 0, 0);
                this.hierarchies = new Hierarchy[_tempArray.length];
                for (int _i = 0; _i < this.hierarchies.length; ++_i) {
                    this.hierarchies[_i] = (Hierarchy)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "Dimension";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "type", (Object)this.type, _indent + 1);
            displayAttribute(_out, "usagePrefix", (Object)this.usagePrefix, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayAttribute(_out, "foreignKey", (Object)this.foreignKey, _indent + 1);
            displayAttribute(_out, "highCardinality", (Object)this.highCardinality, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElementArray(_out, "hierarchies", (NodeDef[])this.hierarchies, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Dimension", new XMLAttrVector().add("type", (Object)this.type).add("usagePrefix", (Object)this.usagePrefix).add("visible", (Object)this.visible).add("foreignKey", (Object)this.foreignKey).add("highCardinality", (Object)this.highCardinality).add("name", (Object)this.name).add("caption", (Object)this.caption).add("description", (Object)this.description));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElementArray(_out, (NodeDef[])this.hierarchies);
            _out.endTag("Dimension");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Dimension _cother = (Dimension)_other;
            boolean _diff = displayAttributeDiff("type", (Object)this.type, (Object)_cother.type, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("usagePrefix", (Object)this.usagePrefix, (Object)_cother.usagePrefix, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("hierarchies", (NodeDef[])this.hierarchies, (NodeDef[])_cother.hierarchies, _out, _indent + 1));
            return _diff;
        }
        
        @Override
        public Dimension getDimension(final Schema schema) {
            Util.assertPrecondition(schema != null, "schema != null");
            return this;
        }
        
        public DimensionType getDimensionType() {
            if (this.type == null) {
                return null;
            }
            return DimensionType.valueOf(this.type);
        }
        
        static {
            _type_values = new String[] { "StandardDimension", "TimeDimension" };
        }
    }
    
    public static class Hierarchy extends ElementDef
    {
        public String name;
        public Boolean visible;
        public Boolean hasAll;
        public String allMemberName;
        public String allMemberCaption;
        public String allLevelName;
        public String primaryKey;
        public String primaryKeyTable;
        public String defaultMember;
        public String memberReaderClass;
        public String caption;
        public String description;
        public String uniqueKeyLevelName;
        public Annotations annotations;
        public RelationOrJoin relation;
        public Level[] levels;
        public MemberReaderParameter[] memberReaderParameters;
        
        public Hierarchy() {
        }
        
        public Hierarchy(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", "true", (String[])null, false);
                this.hasAll = (Boolean)_parser.getAttribute("hasAll", "Boolean", (String)null, (String[])null, true);
                this.allMemberName = (String)_parser.getAttribute("allMemberName", "String", (String)null, (String[])null, false);
                this.allMemberCaption = (String)_parser.getAttribute("allMemberCaption", "String", (String)null, (String[])null, false);
                this.allLevelName = (String)_parser.getAttribute("allLevelName", "String", (String)null, (String[])null, false);
                this.primaryKey = (String)_parser.getAttribute("primaryKey", "String", (String)null, (String[])null, false);
                this.primaryKeyTable = (String)_parser.getAttribute("primaryKeyTable", "String", (String)null, (String[])null, false);
                this.defaultMember = (String)_parser.getAttribute("defaultMember", "String", (String)null, (String[])null, false);
                this.memberReaderClass = (String)_parser.getAttribute("memberReaderClass", "String", (String)null, (String[])null, false);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.uniqueKeyLevelName = (String)_parser.getAttribute("uniqueKeyLevelName", "String", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                this.relation = (RelationOrJoin)_parser.getElement((Class)RelationOrJoin.class, false);
                NodeDef[] _tempArray = _parser.getArray((Class)Level.class, 0, 0);
                this.levels = new Level[_tempArray.length];
                for (int _i = 0; _i < this.levels.length; ++_i) {
                    this.levels[_i] = (Level)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)MemberReaderParameter.class, 0, 0);
                this.memberReaderParameters = new MemberReaderParameter[_tempArray.length];
                for (int _i = 0; _i < this.memberReaderParameters.length; ++_i) {
                    this.memberReaderParameters[_i] = (MemberReaderParameter)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Hierarchy";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayAttribute(_out, "hasAll", (Object)this.hasAll, _indent + 1);
            displayAttribute(_out, "allMemberName", (Object)this.allMemberName, _indent + 1);
            displayAttribute(_out, "allMemberCaption", (Object)this.allMemberCaption, _indent + 1);
            displayAttribute(_out, "allLevelName", (Object)this.allLevelName, _indent + 1);
            displayAttribute(_out, "primaryKey", (Object)this.primaryKey, _indent + 1);
            displayAttribute(_out, "primaryKeyTable", (Object)this.primaryKeyTable, _indent + 1);
            displayAttribute(_out, "defaultMember", (Object)this.defaultMember, _indent + 1);
            displayAttribute(_out, "memberReaderClass", (Object)this.memberReaderClass, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "uniqueKeyLevelName", (Object)this.uniqueKeyLevelName, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElement(_out, "relation", (ElementDef)this.relation, _indent + 1);
            displayElementArray(_out, "levels", (NodeDef[])this.levels, _indent + 1);
            displayElementArray(_out, "memberReaderParameters", (NodeDef[])this.memberReaderParameters, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Hierarchy", new XMLAttrVector().add("name", (Object)this.name).add("visible", (Object)this.visible).add("hasAll", (Object)this.hasAll).add("allMemberName", (Object)this.allMemberName).add("allMemberCaption", (Object)this.allMemberCaption).add("allLevelName", (Object)this.allLevelName).add("primaryKey", (Object)this.primaryKey).add("primaryKeyTable", (Object)this.primaryKeyTable).add("defaultMember", (Object)this.defaultMember).add("memberReaderClass", (Object)this.memberReaderClass).add("caption", (Object)this.caption).add("description", (Object)this.description).add("uniqueKeyLevelName", (Object)this.uniqueKeyLevelName));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElement(_out, (ElementDef)this.relation);
            displayXMLElementArray(_out, (NodeDef[])this.levels);
            displayXMLElementArray(_out, (NodeDef[])this.memberReaderParameters);
            _out.endTag("Hierarchy");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Hierarchy _cother = (Hierarchy)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("visible", (Object)this.visible, (Object)_cother.visible, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("hasAll", (Object)this.hasAll, (Object)_cother.hasAll, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("allMemberName", (Object)this.allMemberName, (Object)_cother.allMemberName, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("allMemberCaption", (Object)this.allMemberCaption, (Object)_cother.allMemberCaption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("allLevelName", (Object)this.allLevelName, (Object)_cother.allLevelName, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("primaryKey", (Object)this.primaryKey, (Object)_cother.primaryKey, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("primaryKeyTable", (Object)this.primaryKeyTable, (Object)_cother.primaryKeyTable, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("defaultMember", (Object)this.defaultMember, (Object)_cother.defaultMember, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("memberReaderClass", (Object)this.memberReaderClass, (Object)_cother.memberReaderClass, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("uniqueKeyLevelName", (Object)this.uniqueKeyLevelName, (Object)_cother.uniqueKeyLevelName, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("relation", (NodeDef)this.relation, (NodeDef)_cother.relation, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("levels", (NodeDef[])this.levels, (NodeDef[])_cother.levels, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("memberReaderParameters", (NodeDef[])this.memberReaderParameters, (NodeDef[])_cother.memberReaderParameters, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class Level extends ElementDef
    {
        public String approxRowCount;
        public String name;
        public Boolean visible;
        public String table;
        public String column;
        public String nameColumn;
        public String ordinalColumn;
        public String parentColumn;
        public String nullParentValue;
        public static final String[] _type_values;
        public String type;
        public static final String[] _internalType_values;
        public String internalType;
        public Boolean uniqueMembers;
        public static final String[] _levelType_values;
        public String levelType;
        public static final String[] _hideMemberIf_values;
        public String hideMemberIf;
        public String formatter;
        public String caption;
        public String description;
        public String captionColumn;
        public Annotations annotations;
        public KeyExpression keyExp;
        public NameExpression nameExp;
        public CaptionExpression captionExp;
        public OrdinalExpression ordinalExp;
        public ParentExpression parentExp;
        public MemberFormatter memberFormatter;
        public Closure closure;
        public Property[] properties;
        
        public Level() {
        }
        
        public Level(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.approxRowCount = (String)_parser.getAttribute("approxRowCount", "String", (String)null, (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", "true", (String[])null, false);
                this.table = (String)_parser.getAttribute("table", "String", (String)null, (String[])null, false);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, false);
                this.nameColumn = (String)_parser.getAttribute("nameColumn", "String", (String)null, (String[])null, false);
                this.ordinalColumn = (String)_parser.getAttribute("ordinalColumn", "String", (String)null, (String[])null, false);
                this.parentColumn = (String)_parser.getAttribute("parentColumn", "String", (String)null, (String[])null, false);
                this.nullParentValue = (String)_parser.getAttribute("nullParentValue", "String", (String)null, (String[])null, false);
                this.type = (String)_parser.getAttribute("type", "String", "String", Level._type_values, false);
                this.internalType = (String)_parser.getAttribute("internalType", "String", (String)null, Level._internalType_values, false);
                this.uniqueMembers = (Boolean)_parser.getAttribute("uniqueMembers", "Boolean", "false", (String[])null, false);
                this.levelType = (String)_parser.getAttribute("levelType", "String", "Regular", Level._levelType_values, false);
                this.hideMemberIf = (String)_parser.getAttribute("hideMemberIf", "String", "Never", Level._hideMemberIf_values, false);
                this.formatter = (String)_parser.getAttribute("formatter", "String", (String)null, (String[])null, false);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.captionColumn = (String)_parser.getAttribute("captionColumn", "String", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                this.keyExp = (KeyExpression)_parser.getElement((Class)KeyExpression.class, false);
                this.nameExp = (NameExpression)_parser.getElement((Class)NameExpression.class, false);
                this.captionExp = (CaptionExpression)_parser.getElement((Class)CaptionExpression.class, false);
                this.ordinalExp = (OrdinalExpression)_parser.getElement((Class)OrdinalExpression.class, false);
                this.parentExp = (ParentExpression)_parser.getElement((Class)ParentExpression.class, false);
                this.memberFormatter = (MemberFormatter)_parser.getElement((Class)MemberFormatter.class, false);
                this.closure = (Closure)_parser.getElement((Class)Closure.class, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)Property.class, 0, 0);
                this.properties = new Property[_tempArray.length];
                for (int _i = 0; _i < this.properties.length; ++_i) {
                    this.properties[_i] = (Property)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Level";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "approxRowCount", (Object)this.approxRowCount, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayAttribute(_out, "table", (Object)this.table, _indent + 1);
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
            displayAttribute(_out, "nameColumn", (Object)this.nameColumn, _indent + 1);
            displayAttribute(_out, "ordinalColumn", (Object)this.ordinalColumn, _indent + 1);
            displayAttribute(_out, "parentColumn", (Object)this.parentColumn, _indent + 1);
            displayAttribute(_out, "nullParentValue", (Object)this.nullParentValue, _indent + 1);
            displayAttribute(_out, "type", (Object)this.type, _indent + 1);
            displayAttribute(_out, "internalType", (Object)this.internalType, _indent + 1);
            displayAttribute(_out, "uniqueMembers", (Object)this.uniqueMembers, _indent + 1);
            displayAttribute(_out, "levelType", (Object)this.levelType, _indent + 1);
            displayAttribute(_out, "hideMemberIf", (Object)this.hideMemberIf, _indent + 1);
            displayAttribute(_out, "formatter", (Object)this.formatter, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "captionColumn", (Object)this.captionColumn, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElement(_out, "keyExp", (ElementDef)this.keyExp, _indent + 1);
            displayElement(_out, "nameExp", (ElementDef)this.nameExp, _indent + 1);
            displayElement(_out, "captionExp", (ElementDef)this.captionExp, _indent + 1);
            displayElement(_out, "ordinalExp", (ElementDef)this.ordinalExp, _indent + 1);
            displayElement(_out, "parentExp", (ElementDef)this.parentExp, _indent + 1);
            displayElement(_out, "memberFormatter", (ElementDef)this.memberFormatter, _indent + 1);
            displayElement(_out, "closure", (ElementDef)this.closure, _indent + 1);
            displayElementArray(_out, "properties", (NodeDef[])this.properties, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Level", new XMLAttrVector().add("approxRowCount", (Object)this.approxRowCount).add("name", (Object)this.name).add("visible", (Object)this.visible).add("table", (Object)this.table).add("column", (Object)this.column).add("nameColumn", (Object)this.nameColumn).add("ordinalColumn", (Object)this.ordinalColumn).add("parentColumn", (Object)this.parentColumn).add("nullParentValue", (Object)this.nullParentValue).add("type", (Object)this.type).add("internalType", (Object)this.internalType).add("uniqueMembers", (Object)this.uniqueMembers).add("levelType", (Object)this.levelType).add("hideMemberIf", (Object)this.hideMemberIf).add("formatter", (Object)this.formatter).add("caption", (Object)this.caption).add("description", (Object)this.description).add("captionColumn", (Object)this.captionColumn));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElement(_out, (ElementDef)this.keyExp);
            displayXMLElement(_out, (ElementDef)this.nameExp);
            displayXMLElement(_out, (ElementDef)this.captionExp);
            displayXMLElement(_out, (ElementDef)this.ordinalExp);
            displayXMLElement(_out, (ElementDef)this.parentExp);
            displayXMLElement(_out, (ElementDef)this.memberFormatter);
            displayXMLElement(_out, (ElementDef)this.closure);
            displayXMLElementArray(_out, (NodeDef[])this.properties);
            _out.endTag("Level");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Level _cother = (Level)_other;
            boolean _diff = displayAttributeDiff("approxRowCount", (Object)this.approxRowCount, (Object)_cother.approxRowCount, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("visible", (Object)this.visible, (Object)_cother.visible, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("table", (Object)this.table, (Object)_cother.table, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("column", (Object)this.column, (Object)_cother.column, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("nameColumn", (Object)this.nameColumn, (Object)_cother.nameColumn, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("ordinalColumn", (Object)this.ordinalColumn, (Object)_cother.ordinalColumn, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("parentColumn", (Object)this.parentColumn, (Object)_cother.parentColumn, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("nullParentValue", (Object)this.nullParentValue, (Object)_cother.nullParentValue, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("type", (Object)this.type, (Object)_cother.type, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("internalType", (Object)this.internalType, (Object)_cother.internalType, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("uniqueMembers", (Object)this.uniqueMembers, (Object)_cother.uniqueMembers, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("levelType", (Object)this.levelType, (Object)_cother.levelType, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("hideMemberIf", (Object)this.hideMemberIf, (Object)_cother.hideMemberIf, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("formatter", (Object)this.formatter, (Object)_cother.formatter, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("captionColumn", (Object)this.captionColumn, (Object)_cother.captionColumn, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("keyExp", (NodeDef)this.keyExp, (NodeDef)_cother.keyExp, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("nameExp", (NodeDef)this.nameExp, (NodeDef)_cother.nameExp, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("captionExp", (NodeDef)this.captionExp, (NodeDef)_cother.captionExp, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("ordinalExp", (NodeDef)this.ordinalExp, (NodeDef)_cother.ordinalExp, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("parentExp", (NodeDef)this.parentExp, (NodeDef)_cother.parentExp, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("memberFormatter", (NodeDef)this.memberFormatter, (NodeDef)_cother.memberFormatter, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("closure", (NodeDef)this.closure, (NodeDef)_cother.closure, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("properties", (NodeDef[])this.properties, (NodeDef[])_cother.properties, _out, _indent + 1));
            return _diff;
        }
        
        public Expression getKeyExp() {
            if (this.keyExp != null) {
                return this.keyExp;
            }
            if (this.column != null) {
                return new Column(this.table, this.column);
            }
            return null;
        }
        
        public Expression getNameExp() {
            if (this.nameExp != null) {
                return this.nameExp;
            }
            if (this.nameColumn != null && !Util.equals(this.nameColumn, this.column)) {
                return new Column(this.table, this.nameColumn);
            }
            return null;
        }
        
        public Expression getCaptionExp() {
            if (this.captionExp != null) {
                return this.captionExp;
            }
            if (this.captionColumn != null) {
                return new Column(this.table, this.captionColumn);
            }
            return null;
        }
        
        public Expression getOrdinalExp() {
            if (this.ordinalExp != null) {
                return this.ordinalExp;
            }
            if (this.ordinalColumn != null) {
                return new Column(this.table, this.ordinalColumn);
            }
            return null;
        }
        
        public Expression getParentExp() {
            if (this.parentExp != null) {
                return this.parentExp;
            }
            if (this.parentColumn != null) {
                return new Column(this.table, this.parentColumn);
            }
            return null;
        }
        
        public Expression getPropertyExp(final int i) {
            return new Column(this.table, this.properties[i].column);
        }
        
        public Dialect.Datatype getDatatype() {
            return Dialect.Datatype.valueOf(this.type);
        }
        
        static {
            _type_values = new String[] { "String", "Numeric", "Integer", "Boolean", "Date", "Time", "Timestamp" };
            _internalType_values = new String[] { "int", "long", "Object", "String" };
            _levelType_values = new String[] { "Regular", "TimeYears", "TimeHalfYears", "TimeHalfYear", "TimeQuarters", "TimeMonths", "TimeWeeks", "TimeDays", "TimeHours", "TimeMinutes", "TimeSeconds", "TimeUndefined" };
            _hideMemberIf_values = new String[] { "Never", "IfBlankName", "IfParentsName" };
        }
    }
    
    public static class Closure extends ElementDef
    {
        public String parentColumn;
        public String childColumn;
        public Table table;
        
        public Closure() {
        }
        
        public Closure(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.parentColumn = (String)_parser.getAttribute("parentColumn", "String", (String)null, (String[])null, true);
                this.childColumn = (String)_parser.getAttribute("childColumn", "String", (String)null, (String[])null, true);
                this.table = (Table)_parser.getElement((Class)Table.class, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Closure";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "parentColumn", (Object)this.parentColumn, _indent + 1);
            displayAttribute(_out, "childColumn", (Object)this.childColumn, _indent + 1);
            displayElement(_out, "table", (ElementDef)this.table, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Closure", new XMLAttrVector().add("parentColumn", (Object)this.parentColumn).add("childColumn", (Object)this.childColumn));
            displayXMLElement(_out, (ElementDef)this.table);
            _out.endTag("Closure");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Closure _cother = (Closure)_other;
            boolean _diff = displayAttributeDiff("parentColumn", (Object)this.parentColumn, (Object)_cother.parentColumn, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("childColumn", (Object)this.childColumn, (Object)_cother.childColumn, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("table", (NodeDef)this.table, (NodeDef)_cother.table, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class Property extends ElementDef
    {
        public String name;
        public String column;
        public static final String[] _type_values;
        public String type;
        public String formatter;
        public String caption;
        public String description;
        public Boolean dependsOnLevelValue;
        public PropertyFormatter propertyFormatter;
        
        public Property() {
        }
        
        public Property(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, false);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, false);
                this.type = (String)_parser.getAttribute("type", "String", "String", Property._type_values, false);
                this.formatter = (String)_parser.getAttribute("formatter", "String", (String)null, (String[])null, false);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.dependsOnLevelValue = (Boolean)_parser.getAttribute("dependsOnLevelValue", "Boolean", (String)null, (String[])null, false);
                this.propertyFormatter = (PropertyFormatter)_parser.getElement((Class)PropertyFormatter.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Property";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
            displayAttribute(_out, "type", (Object)this.type, _indent + 1);
            displayAttribute(_out, "formatter", (Object)this.formatter, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "dependsOnLevelValue", (Object)this.dependsOnLevelValue, _indent + 1);
            displayElement(_out, "propertyFormatter", (ElementDef)this.propertyFormatter, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Property", new XMLAttrVector().add("name", (Object)this.name).add("column", (Object)this.column).add("type", (Object)this.type).add("formatter", (Object)this.formatter).add("caption", (Object)this.caption).add("description", (Object)this.description).add("dependsOnLevelValue", (Object)this.dependsOnLevelValue));
            displayXMLElement(_out, (ElementDef)this.propertyFormatter);
            _out.endTag("Property");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Property _cother = (Property)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("column", (Object)this.column, (Object)_cother.column, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("type", (Object)this.type, (Object)_cother.type, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("formatter", (Object)this.formatter, (Object)_cother.formatter, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("dependsOnLevelValue", (Object)this.dependsOnLevelValue, (Object)_cother.dependsOnLevelValue, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("propertyFormatter", (NodeDef)this.propertyFormatter, (NodeDef)_cother.propertyFormatter, _out, _indent + 1));
            return _diff;
        }
        
        static {
            _type_values = new String[] { "String", "Numeric", "Integer", "Long", "Boolean", "Date", "Time", "Timestamp" };
        }
    }
    
    public static class Measure extends ElementDef
    {
        public String name;
        public String column;
        public static final String[] _datatype_values;
        public String datatype;
        public String formatString;
        public String aggregator;
        public String formatter;
        public String caption;
        public String description;
        public Boolean visible;
        public Annotations annotations;
        public MeasureExpression measureExp;
        public CellFormatter cellFormatter;
        public CalculatedMemberProperty[] memberProperties;
        
        public Measure() {
        }
        
        public Measure(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, false);
                this.datatype = (String)_parser.getAttribute("datatype", "String", (String)null, Measure._datatype_values, false);
                this.formatString = (String)_parser.getAttribute("formatString", "String", (String)null, (String[])null, false);
                this.aggregator = (String)_parser.getAttribute("aggregator", "String", (String)null, (String[])null, true);
                this.formatter = (String)_parser.getAttribute("formatter", "String", (String)null, (String[])null, false);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                this.measureExp = (MeasureExpression)_parser.getElement((Class)MeasureExpression.class, false);
                this.cellFormatter = (CellFormatter)_parser.getElement((Class)CellFormatter.class, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)CalculatedMemberProperty.class, 0, 0);
                this.memberProperties = new CalculatedMemberProperty[_tempArray.length];
                for (int _i = 0; _i < this.memberProperties.length; ++_i) {
                    this.memberProperties[_i] = (CalculatedMemberProperty)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Measure";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
            displayAttribute(_out, "datatype", (Object)this.datatype, _indent + 1);
            displayAttribute(_out, "formatString", (Object)this.formatString, _indent + 1);
            displayAttribute(_out, "aggregator", (Object)this.aggregator, _indent + 1);
            displayAttribute(_out, "formatter", (Object)this.formatter, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElement(_out, "measureExp", (ElementDef)this.measureExp, _indent + 1);
            displayElement(_out, "cellFormatter", (ElementDef)this.cellFormatter, _indent + 1);
            displayElementArray(_out, "memberProperties", (NodeDef[])this.memberProperties, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Measure", new XMLAttrVector().add("name", (Object)this.name).add("column", (Object)this.column).add("datatype", (Object)this.datatype).add("formatString", (Object)this.formatString).add("aggregator", (Object)this.aggregator).add("formatter", (Object)this.formatter).add("caption", (Object)this.caption).add("description", (Object)this.description).add("visible", (Object)this.visible));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElement(_out, (ElementDef)this.measureExp);
            displayXMLElement(_out, (ElementDef)this.cellFormatter);
            displayXMLElementArray(_out, (NodeDef[])this.memberProperties);
            _out.endTag("Measure");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Measure _cother = (Measure)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("column", (Object)this.column, (Object)_cother.column, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("datatype", (Object)this.datatype, (Object)_cother.datatype, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("formatString", (Object)this.formatString, (Object)_cother.formatString, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("aggregator", (Object)this.aggregator, (Object)_cother.aggregator, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("formatter", (Object)this.formatter, (Object)_cother.formatter, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("visible", (Object)this.visible, (Object)_cother.visible, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("measureExp", (NodeDef)this.measureExp, (NodeDef)_cother.measureExp, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("cellFormatter", (NodeDef)this.cellFormatter, (NodeDef)_cother.cellFormatter, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("memberProperties", (NodeDef[])this.memberProperties, (NodeDef[])_cother.memberProperties, _out, _indent + 1));
            return _diff;
        }
        
        static {
            _datatype_values = new String[] { "String", "Numeric", "Integer" };
        }
    }
    
    public static class CalculatedMember extends ElementDef
    {
        public String name;
        public String formatString;
        public String caption;
        public String description;
        public String formula;
        public String dimension;
        public String hierarchy;
        public String parent;
        public Boolean visible;
        public Annotations annotations;
        public Formula formulaElement;
        public CellFormatter cellFormatter;
        public CalculatedMemberProperty[] memberProperties;
        
        public CalculatedMember() {
        }
        
        public CalculatedMember(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.formatString = (String)_parser.getAttribute("formatString", "String", (String)null, (String[])null, false);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.formula = (String)_parser.getAttribute("formula", "String", (String)null, (String[])null, false);
                this.dimension = (String)_parser.getAttribute("dimension", "String", (String)null, (String[])null, false);
                this.hierarchy = (String)_parser.getAttribute("hierarchy", "String", (String)null, (String[])null, false);
                this.parent = (String)_parser.getAttribute("parent", "String", (String)null, (String[])null, false);
                this.visible = (Boolean)_parser.getAttribute("visible", "Boolean", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                this.formulaElement = (Formula)_parser.getElement((Class)Formula.class, false);
                this.cellFormatter = (CellFormatter)_parser.getElement((Class)CellFormatter.class, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)CalculatedMemberProperty.class, 0, 0);
                this.memberProperties = new CalculatedMemberProperty[_tempArray.length];
                for (int _i = 0; _i < this.memberProperties.length; ++_i) {
                    this.memberProperties[_i] = (CalculatedMemberProperty)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "CalculatedMember";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "formatString", (Object)this.formatString, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "formula", (Object)this.formula, _indent + 1);
            displayAttribute(_out, "dimension", (Object)this.dimension, _indent + 1);
            displayAttribute(_out, "hierarchy", (Object)this.hierarchy, _indent + 1);
            displayAttribute(_out, "parent", (Object)this.parent, _indent + 1);
            displayAttribute(_out, "visible", (Object)this.visible, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElement(_out, "formulaElement", (ElementDef)this.formulaElement, _indent + 1);
            displayElement(_out, "cellFormatter", (ElementDef)this.cellFormatter, _indent + 1);
            displayElementArray(_out, "memberProperties", (NodeDef[])this.memberProperties, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CalculatedMember", new XMLAttrVector().add("name", (Object)this.name).add("formatString", (Object)this.formatString).add("caption", (Object)this.caption).add("description", (Object)this.description).add("formula", (Object)this.formula).add("dimension", (Object)this.dimension).add("hierarchy", (Object)this.hierarchy).add("parent", (Object)this.parent).add("visible", (Object)this.visible));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElement(_out, (ElementDef)this.formulaElement);
            displayXMLElement(_out, (ElementDef)this.cellFormatter);
            displayXMLElementArray(_out, (NodeDef[])this.memberProperties);
            _out.endTag("CalculatedMember");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CalculatedMember _cother = (CalculatedMember)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("formatString", (Object)this.formatString, (Object)_cother.formatString, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("formula", (Object)this.formula, (Object)_cother.formula, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("dimension", (Object)this.dimension, (Object)_cother.dimension, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("hierarchy", (Object)this.hierarchy, (Object)_cother.hierarchy, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("parent", (Object)this.parent, (Object)_cother.parent, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("visible", (Object)this.visible, (Object)_cother.visible, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("formulaElement", (NodeDef)this.formulaElement, (NodeDef)_cother.formulaElement, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("cellFormatter", (NodeDef)this.cellFormatter, (NodeDef)_cother.cellFormatter, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("memberProperties", (NodeDef[])this.memberProperties, (NodeDef[])_cother.memberProperties, _out, _indent + 1));
            return _diff;
        }
        
        public String getFormula() {
            if (this.formulaElement != null) {
                return this.formulaElement.cdata;
            }
            return this.formula;
        }
        
        public String getFormatString() {
            for (final CalculatedMemberProperty prop : this.memberProperties) {
                if (prop.name.equals(mondrian8.olap.Property.FORMAT_STRING.name)) {
                    return prop.value;
                }
            }
            return this.formatString;
        }
    }
    
    public static class CalculatedMemberProperty extends ElementDef
    {
        public String name;
        public String caption;
        public String description;
        public String expression;
        public String value;
        
        public CalculatedMemberProperty() {
        }
        
        public CalculatedMemberProperty(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.expression = (String)_parser.getAttribute("expression", "String", (String)null, (String[])null, false);
                this.value = (String)_parser.getAttribute("value", "String", (String)null, (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "CalculatedMemberProperty";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "expression", (Object)this.expression, _indent + 1);
            displayAttribute(_out, "value", (Object)this.value, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CalculatedMemberProperty", new XMLAttrVector().add("name", (Object)this.name).add("caption", (Object)this.caption).add("description", (Object)this.description).add("expression", (Object)this.expression).add("value", (Object)this.value));
            _out.endTag("CalculatedMemberProperty");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CalculatedMemberProperty _cother = (CalculatedMemberProperty)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("expression", (Object)this.expression, (Object)_cother.expression, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("value", (Object)this.value, (Object)_cother.value, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class NamedSet extends ElementDef
    {
        public String name;
        public String caption;
        public String description;
        public String formula;
        public Annotations annotations;
        public Formula formulaElement;
        
        public NamedSet() {
        }
        
        public NamedSet(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.caption = (String)_parser.getAttribute("caption", "String", (String)null, (String[])null, false);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.formula = (String)_parser.getAttribute("formula", "String", (String)null, (String[])null, false);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                this.formulaElement = (Formula)_parser.getElement((Class)Formula.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "NamedSet";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "caption", (Object)this.caption, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "formula", (Object)this.formula, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElement(_out, "formulaElement", (ElementDef)this.formulaElement, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("NamedSet", new XMLAttrVector().add("name", (Object)this.name).add("caption", (Object)this.caption).add("description", (Object)this.description).add("formula", (Object)this.formula));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElement(_out, (ElementDef)this.formulaElement);
            _out.endTag("NamedSet");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final NamedSet _cother = (NamedSet)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("caption", (Object)this.caption, (Object)_cother.caption, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("formula", (Object)this.formula, (Object)_cother.formula, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("formulaElement", (NodeDef)this.formulaElement, (NodeDef)_cother.formulaElement, _out, _indent + 1));
            return _diff;
        }
        
        public String getFormula() {
            if (this.formulaElement != null) {
                return this.formulaElement.cdata;
            }
            return this.formula;
        }
    }
    
    public static class Formula extends ElementDef
    {
        public String cdata;
        
        public Formula() {
        }
        
        public Formula(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.cdata = _parser.getText();
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Formula";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayString(_out, "cdata", this.cdata, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Formula", new XMLAttrVector());
            _out.cdata(this.cdata);
            _out.endTag("Formula");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Formula _cother = (Formula)_other;
            final boolean _diff = displayStringDiff("cdata", this.cdata, _cother.cdata, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class MemberReaderParameter extends ElementDef
    {
        public String name;
        public String value;
        
        public MemberReaderParameter() {
        }
        
        public MemberReaderParameter(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, false);
                this.value = (String)_parser.getAttribute("value", "String", (String)null, (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "MemberReaderParameter";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "value", (Object)this.value, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("MemberReaderParameter", new XMLAttrVector().add("name", (Object)this.name).add("value", (Object)this.value));
            _out.endTag("MemberReaderParameter");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final MemberReaderParameter _cother = (MemberReaderParameter)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("value", (Object)this.value, (Object)_cother.value, _out, _indent + 1));
            return _diff;
        }
    }
    
    public abstract static class RelationOrJoin extends ElementDef
    {
        public RelationOrJoin() {
        }
        
        public RelationOrJoin(final DOMWrapper _def) throws XOMException {
        }
        
        public String getName() {
            return "RelationOrJoin";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("RelationOrJoin", new XMLAttrVector());
            _out.endTag("RelationOrJoin");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            return true;
        }
        
        public abstract Relation find(final String p0);
        
        public boolean equals(final Object o) {
            return this == o;
        }
        
        public int hashCode() {
            return System.identityHashCode(this);
        }
    }
    
    public abstract static class Relation extends RelationOrJoin
    {
        public Relation() {
        }
        
        public Relation(final DOMWrapper _def) throws XOMException {
        }
        
        @Override
        public String getName() {
            return "Relation";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Relation", new XMLAttrVector());
            _out.endTag("Relation");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            return true;
        }
        
        public abstract String getAlias();
    }
    
    public static class View extends Relation
    {
        public String alias;
        public SQL[] selects;
        
        public View() {
        }
        
        public View(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.alias = (String)_parser.getAttribute("alias", "String", (String)null, (String[])null, true);
                final NodeDef[] _tempArray = _parser.getArray((Class)SQL.class, 1, 0);
                this.selects = new SQL[_tempArray.length];
                for (int _i = 0; _i < this.selects.length; ++_i) {
                    this.selects[_i] = (SQL)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "View";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "alias", (Object)this.alias, _indent + 1);
            displayElementArray(_out, "selects", (NodeDef[])this.selects, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("View", new XMLAttrVector().add("alias", (Object)this.alias));
            displayXMLElementArray(_out, (NodeDef[])this.selects);
            _out.endTag("View");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final View _cother = (View)_other;
            boolean _diff = displayAttributeDiff("alias", (Object)this.alias, (Object)_cother.alias, _out, _indent + 1);
            _diff = (_diff && displayElementArrayDiff("selects", (NodeDef[])this.selects, (NodeDef[])_cother.selects, _out, _indent + 1));
            return _diff;
        }
        
        public View(final View view) {
            this.alias = view.alias;
            this.selects = view.selects.clone();
        }
        
        public String toString() {
            return this.selects[0].cdata;
        }
        
        @Override
        public View find(final String seekAlias) {
            if (seekAlias.equals(this.alias)) {
                return this;
            }
            return null;
        }
        
        @Override
        public String getAlias() {
            return this.alias;
        }
        
        public SqlQuery.CodeSet getCodeSet() {
            return SQL.toCodeSet(this.selects);
        }
        
        public void addCode(final String dialect, final String code) {
            if (this.selects == null) {
                this.selects = new SQL[1];
            }
            else {
                final SQL[] olds = this.selects;
                System.arraycopy(olds, 0, this.selects = new SQL[olds.length + 1], 0, olds.length);
            }
            final SQL sql = new SQL();
            sql.dialect = dialect;
            sql.cdata = code;
            this.selects[this.selects.length - 1] = sql;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof View)) {
                return false;
            }
            final View that = (View)o;
            if (!Util.equals(this.alias, that.alias)) {
                return false;
            }
            if (this.selects == null || that.selects == null || this.selects.length != that.selects.length) {
                return false;
            }
            for (int i = 0; i < this.selects.length; ++i) {
                if (!Util.equals(this.selects[i].dialect, that.selects[i].dialect) || !Util.equals(this.selects[i].cdata, that.selects[i].cdata)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    public static class SQL extends ElementDef
    {
        public String dialect;
        public String cdata;
        
        public SQL() {
        }
        
        public SQL(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.dialect = (String)_parser.getAttribute("dialect", "String", "generic", (String[])null, true);
                this.cdata = _parser.getText();
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "SQL";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "dialect", (Object)this.dialect, _indent + 1);
            displayString(_out, "cdata", this.cdata, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("SQL", new XMLAttrVector().add("dialect", (Object)this.dialect));
            _out.cdata(this.cdata);
            _out.endTag("SQL");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final SQL _cother = (SQL)_other;
            boolean _diff = displayAttributeDiff("dialect", (Object)this.dialect, (Object)_cother.dialect, _out, _indent + 1);
            _diff = (_diff && displayStringDiff("cdata", this.cdata, _cother.cdata, _out, _indent + 1));
            return _diff;
        }
        
        public int hashCode() {
            return this.dialect.hashCode();
        }
        
        public boolean equals(final Object obj) {
            if (!(obj instanceof SQL)) {
                return false;
            }
            final SQL that = (SQL)obj;
            return this.dialect.equals(that.dialect) && Util.equals(this.cdata, that.cdata);
        }
        
        public static SqlQuery.CodeSet toCodeSet(final SQL[] sqls) {
            final SqlQuery.CodeSet codeSet = new SqlQuery.CodeSet();
            for (final SQL sql : sqls) {
                codeSet.put(sql.dialect, sql.cdata);
            }
            return codeSet;
        }
    }
    
    public static class Join extends RelationOrJoin
    {
        public String leftAlias;
        public String leftKey;
        public String rightAlias;
        public String rightKey;
        public RelationOrJoin left;
        public RelationOrJoin right;
        
        public Join() {
        }
        
        public Join(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.leftAlias = (String)_parser.getAttribute("leftAlias", "String", (String)null, (String[])null, false);
                this.leftKey = (String)_parser.getAttribute("leftKey", "String", (String)null, (String[])null, true);
                this.rightAlias = (String)_parser.getAttribute("rightAlias", "String", (String)null, (String[])null, false);
                this.rightKey = (String)_parser.getAttribute("rightKey", "String", (String)null, (String[])null, true);
                this.left = (RelationOrJoin)_parser.getElement((Class)RelationOrJoin.class, true);
                this.right = (RelationOrJoin)_parser.getElement((Class)RelationOrJoin.class, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "Join";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "leftAlias", (Object)this.leftAlias, _indent + 1);
            displayAttribute(_out, "leftKey", (Object)this.leftKey, _indent + 1);
            displayAttribute(_out, "rightAlias", (Object)this.rightAlias, _indent + 1);
            displayAttribute(_out, "rightKey", (Object)this.rightKey, _indent + 1);
            displayElement(_out, "left", (ElementDef)this.left, _indent + 1);
            displayElement(_out, "right", (ElementDef)this.right, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Join", new XMLAttrVector().add("leftAlias", (Object)this.leftAlias).add("leftKey", (Object)this.leftKey).add("rightAlias", (Object)this.rightAlias).add("rightKey", (Object)this.rightKey));
            displayXMLElement(_out, (ElementDef)this.left);
            displayXMLElement(_out, (ElementDef)this.right);
            _out.endTag("Join");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Join _cother = (Join)_other;
            boolean _diff = displayAttributeDiff("leftAlias", (Object)this.leftAlias, (Object)_cother.leftAlias, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("leftKey", (Object)this.leftKey, (Object)_cother.leftKey, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("rightAlias", (Object)this.rightAlias, (Object)_cother.rightAlias, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("rightKey", (Object)this.rightKey, (Object)_cother.rightKey, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("left", (NodeDef)this.left, (NodeDef)_cother.left, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("right", (NodeDef)this.right, (NodeDef)_cother.right, _out, _indent + 1));
            return _diff;
        }
        
        public Join(final String leftAlias, final String leftKey, final RelationOrJoin left, final String rightAlias, final String rightKey, final RelationOrJoin right) {
            this.leftAlias = leftAlias;
            this.leftKey = leftKey;
            this.left = left;
            this.rightAlias = rightAlias;
            this.rightKey = rightKey;
            this.right = right;
        }
        
        public String getLeftAlias() {
            if (this.leftAlias != null) {
                return this.leftAlias;
            }
            if (this.left instanceof Relation) {
                return ((Relation)this.left).getAlias();
            }
            throw Util.newInternal("alias is required because " + this.left + " is not a table");
        }
        
        public String getRightAlias() {
            if (this.rightAlias != null) {
                return this.rightAlias;
            }
            if (this.right instanceof Relation) {
                return ((Relation)this.right).getAlias();
            }
            if (this.right instanceof Join) {
                return ((Join)this.right).getLeftAlias();
            }
            throw Util.newInternal("alias is required because " + this.right + " is not a table");
        }
        
        public String toString() {
            return "(" + this.left + ") join (" + this.right + ") on " + this.leftAlias + "." + this.leftKey + " = " + this.rightAlias + "." + this.rightKey;
        }
        
        @Override
        public Relation find(final String seekAlias) {
            Relation relation = this.left.find(seekAlias);
            if (relation == null) {
                relation = this.right.find(seekAlias);
            }
            return relation;
        }
    }
    
    public static class Table extends Relation
    {
        public String name;
        public String schema;
        public String alias;
        public SQL filter;
        public AggExclude[] aggExcludes;
        public AggTable[] aggTables;
        public Hint[] tableHints;
        private Map<String, String> hintMap;
        
        public Table() {
        }
        
        public Table(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.schema = (String)_parser.getAttribute("schema", "String", (String)null, (String[])null, false);
                this.alias = (String)_parser.getAttribute("alias", "String", (String)null, (String[])null, false);
                this.filter = (SQL)_parser.getElement((Class)SQL.class, false);
                NodeDef[] _tempArray = _parser.getArray((Class)AggExclude.class, 0, 0);
                this.aggExcludes = new AggExclude[_tempArray.length];
                for (int _i = 0; _i < this.aggExcludes.length; ++_i) {
                    this.aggExcludes[_i] = (AggExclude)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggTable.class, 0, 0);
                this.aggTables = new AggTable[_tempArray.length];
                for (int _i = 0; _i < this.aggTables.length; ++_i) {
                    this.aggTables[_i] = (AggTable)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)Hint.class, 0, 0);
                this.tableHints = new Hint[_tempArray.length];
                for (int _i = 0; _i < this.tableHints.length; ++_i) {
                    this.tableHints[_i] = (Hint)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "Table";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "schema", (Object)this.schema, _indent + 1);
            displayAttribute(_out, "alias", (Object)this.alias, _indent + 1);
            displayElement(_out, "filter", (ElementDef)this.filter, _indent + 1);
            displayElementArray(_out, "aggExcludes", (NodeDef[])this.aggExcludes, _indent + 1);
            displayElementArray(_out, "aggTables", (NodeDef[])this.aggTables, _indent + 1);
            displayElementArray(_out, "tableHints", (NodeDef[])this.tableHints, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Table", new XMLAttrVector().add("name", (Object)this.name).add("schema", (Object)this.schema).add("alias", (Object)this.alias));
            displayXMLElement(_out, (ElementDef)this.filter);
            displayXMLElementArray(_out, (NodeDef[])this.aggExcludes);
            displayXMLElementArray(_out, (NodeDef[])this.aggTables);
            displayXMLElementArray(_out, (NodeDef[])this.tableHints);
            _out.endTag("Table");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Table _cother = (Table)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("schema", (Object)this.schema, (Object)_cother.schema, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("alias", (Object)this.alias, (Object)_cother.alias, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("filter", (NodeDef)this.filter, (NodeDef)_cother.filter, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("aggExcludes", (NodeDef[])this.aggExcludes, (NodeDef[])_cother.aggExcludes, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("aggTables", (NodeDef[])this.aggTables, (NodeDef[])_cother.aggTables, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("tableHints", (NodeDef[])this.tableHints, (NodeDef[])_cother.tableHints, _out, _indent + 1));
            return _diff;
        }
        
        public Table(final Table table) {
            this(table.schema, table.name, table.alias, table.tableHints);
        }
        
        public Table(final String schema, final String name, final String alias, final Hint[] tablehints) {
            this();
            this.schema = schema;
            this.name = name;
            this.alias = alias;
            this.hintMap = this.buildHintMap(tablehints);
        }
        
        public Table(final Table tbl, final String possibleName) {
            this(tbl.schema, tbl.name, possibleName, tbl.tableHints);
            if (tbl.filter != null) {
                this.filter = new SQL();
                this.filter.dialect = tbl.filter.dialect;
                if (tbl.filter.cdata != null) {
                    this.filter.cdata = tbl.filter.cdata.replace((tbl.alias == null) ? tbl.name : tbl.alias, possibleName);
                }
            }
        }
        
        private Map<String, String> buildHintMap(final Hint[] th) {
            final Map<String, String> h = new HashMap<String, String>();
            if (th != null) {
                for (int i = 0; i < th.length; ++i) {
                    h.put(th[i].type, th[i].cdata);
                }
            }
            return h;
        }
        
        @Override
        public String getAlias() {
            return (this.alias != null) ? this.alias : this.name;
        }
        
        public String toString() {
            return (this.schema == null) ? this.name : (this.schema + "." + this.name);
        }
        
        @Override
        public Table find(final String seekAlias) {
            return seekAlias.equals(this.name) ? this : ((this.alias != null && seekAlias.equals(this.alias)) ? this : null);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof Table) {
                final Table that = (Table)o;
                return this.name.equals(that.name) && Util.equals(this.alias, that.alias) && Util.equals(this.schema, that.schema);
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }
        
        public String getFilter() {
            return (this.filter == null) ? null : this.filter.cdata;
        }
        
        public AggExclude[] getAggExcludes() {
            return this.aggExcludes;
        }
        
        public AggTable[] getAggTables() {
            return this.aggTables;
        }
        
        public Map<String, String> getHintMap() {
            if (this.hintMap == null) {
                this.hintMap = this.buildHintMap(this.tableHints);
            }
            return this.hintMap;
        }
    }
    
    public static class Hint extends ElementDef
    {
        public String type;
        public String cdata;
        
        public Hint() {
        }
        
        public Hint(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.type = (String)_parser.getAttribute("type", "String", (String)null, (String[])null, true);
                this.cdata = _parser.getText();
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Hint";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "type", (Object)this.type, _indent + 1);
            displayString(_out, "cdata", this.cdata, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Hint", new XMLAttrVector().add("type", (Object)this.type));
            _out.cdata(this.cdata);
            _out.endTag("Hint");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Hint _cother = (Hint)_other;
            boolean _diff = displayAttributeDiff("type", (Object)this.type, (Object)_cother.type, _out, _indent + 1);
            _diff = (_diff && displayStringDiff("cdata", this.cdata, _cother.cdata, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class InlineTable extends Relation
    {
        public String alias;
        public ColumnDefs columnDefs;
        public Rows rows;
        
        public InlineTable() {
        }
        
        public InlineTable(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.alias = (String)_parser.getAttribute("alias", "String", (String)null, (String[])null, false);
                this.columnDefs = (ColumnDefs)_parser.getElement((Class)ColumnDefs.class, true);
                this.rows = (Rows)_parser.getElement((Class)Rows.class, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "InlineTable";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "alias", (Object)this.alias, _indent + 1);
            displayElement(_out, "columnDefs", (ElementDef)this.columnDefs, _indent + 1);
            displayElement(_out, "rows", (ElementDef)this.rows, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("InlineTable", new XMLAttrVector().add("alias", (Object)this.alias));
            displayXMLElement(_out, (ElementDef)this.columnDefs);
            displayXMLElement(_out, (ElementDef)this.rows);
            _out.endTag("InlineTable");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final InlineTable _cother = (InlineTable)_other;
            boolean _diff = displayAttributeDiff("alias", (Object)this.alias, (Object)_cother.alias, _out, _indent + 1);
            _diff = (_diff && displayElementDiff("columnDefs", (NodeDef)this.columnDefs, (NodeDef)_cother.columnDefs, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("rows", (NodeDef)this.rows, (NodeDef)_cother.rows, _out, _indent + 1));
            return _diff;
        }
        
        public InlineTable(final InlineTable inlineTable) {
            this.alias = inlineTable.alias;
            this.columnDefs = new ColumnDefs();
            this.columnDefs.array = inlineTable.columnDefs.array.clone();
            this.rows = new Rows();
            this.rows.array = inlineTable.rows.array.clone();
        }
        
        @Override
        public String getAlias() {
            return this.alias;
        }
        
        public String toString() {
            return "<inline data>";
        }
        
        @Override
        public InlineTable find(final String seekAlias) {
            return seekAlias.equals(this.alias) ? this : null;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof InlineTable) {
                final InlineTable that = (InlineTable)o;
                return this.alias.equals(that.alias);
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }
    }
    
    public static class ColumnDefs extends ElementDef
    {
        public ColumnDef[] array;
        
        public ColumnDefs() {
        }
        
        public ColumnDefs(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)ColumnDef.class, 0, 0);
                this.array = new ColumnDef[_tempArray.length];
                for (int _i = 0; _i < this.array.length; ++_i) {
                    this.array[_i] = (ColumnDef)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "ColumnDefs";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "array", (NodeDef[])this.array, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("ColumnDefs", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.array);
            _out.endTag("ColumnDefs");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final ColumnDefs _cother = (ColumnDefs)_other;
            final boolean _diff = displayElementArrayDiff("array", (NodeDef[])this.array, (NodeDef[])_cother.array, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class ColumnDef extends ElementDef
    {
        public String name;
        public static final String[] _type_values;
        public String type;
        
        public ColumnDef() {
        }
        
        public ColumnDef(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.type = (String)_parser.getAttribute("type", "String", (String)null, ColumnDef._type_values, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "ColumnDef";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "type", (Object)this.type, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("ColumnDef", new XMLAttrVector().add("name", (Object)this.name).add("type", (Object)this.type));
            _out.endTag("ColumnDef");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final ColumnDef _cother = (ColumnDef)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("type", (Object)this.type, (Object)_cother.type, _out, _indent + 1));
            return _diff;
        }
        
        static {
            _type_values = new String[] { "String", "Numeric", "Integer", "Boolean", "Date", "Time", "Timestamp" };
        }
    }
    
    public static class Rows extends ElementDef
    {
        public Row[] array;
        
        public Rows() {
        }
        
        public Rows(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)Row.class, 0, 0);
                this.array = new Row[_tempArray.length];
                for (int _i = 0; _i < this.array.length; ++_i) {
                    this.array[_i] = (Row)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Rows";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "array", (NodeDef[])this.array, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Rows", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.array);
            _out.endTag("Rows");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Rows _cother = (Rows)_other;
            final boolean _diff = displayElementArrayDiff("array", (NodeDef[])this.array, (NodeDef[])_cother.array, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class Row extends ElementDef
    {
        public Value[] values;
        
        public Row() {
        }
        
        public Row(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)Value.class, 0, 0);
                this.values = new Value[_tempArray.length];
                for (int _i = 0; _i < this.values.length; ++_i) {
                    this.values[_i] = (Value)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Row";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "values", (NodeDef[])this.values, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Row", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.values);
            _out.endTag("Row");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Row _cother = (Row)_other;
            final boolean _diff = displayElementArrayDiff("values", (NodeDef[])this.values, (NodeDef[])_cother.values, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class Value extends ElementDef
    {
        public String column;
        public String cdata;
        
        public Value() {
        }
        
        public Value(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, true);
                this.cdata = _parser.getText();
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Value";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
            displayString(_out, "cdata", this.cdata, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Value", new XMLAttrVector().add("column", (Object)this.column));
            _out.cdata(this.cdata);
            _out.endTag("Value");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Value _cother = (Value)_other;
            boolean _diff = displayAttributeDiff("column", (Object)this.column, (Object)_cother.column, _out, _indent + 1);
            _diff = (_diff && displayStringDiff("cdata", this.cdata, _cother.cdata, _out, _indent + 1));
            return _diff;
        }
    }
    
    public abstract static class AggTable extends ElementDef
    {
        public Boolean ignorecase;
        public AggFactCount factcount;
        public AggMeasureFactCount[] measuresfactcount;
        public AggIgnoreColumn[] ignoreColumns;
        public AggForeignKey[] foreignKeys;
        public AggMeasure[] measures;
        public AggLevel[] levels;
        
        public AggTable() {
        }
        
        public AggTable(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.ignorecase = (Boolean)_parser.getAttribute("ignorecase", "Boolean", "true", (String[])null, false);
                this.factcount = (AggFactCount)_parser.getElement((Class)AggFactCount.class, true);
                NodeDef[] _tempArray = _parser.getArray((Class)AggMeasureFactCount.class, 0, 0);
                this.measuresfactcount = new AggMeasureFactCount[_tempArray.length];
                for (int _i = 0; _i < this.measuresfactcount.length; ++_i) {
                    this.measuresfactcount[_i] = (AggMeasureFactCount)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggIgnoreColumn.class, 0, 0);
                this.ignoreColumns = new AggIgnoreColumn[_tempArray.length];
                for (int _i = 0; _i < this.ignoreColumns.length; ++_i) {
                    this.ignoreColumns[_i] = (AggIgnoreColumn)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggForeignKey.class, 0, 0);
                this.foreignKeys = new AggForeignKey[_tempArray.length];
                for (int _i = 0; _i < this.foreignKeys.length; ++_i) {
                    this.foreignKeys[_i] = (AggForeignKey)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggMeasure.class, 0, 0);
                this.measures = new AggMeasure[_tempArray.length];
                for (int _i = 0; _i < this.measures.length; ++_i) {
                    this.measures[_i] = (AggMeasure)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggLevel.class, 0, 0);
                this.levels = new AggLevel[_tempArray.length];
                for (int _i = 0; _i < this.levels.length; ++_i) {
                    this.levels[_i] = (AggLevel)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "AggTable";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "ignorecase", (Object)this.ignorecase, _indent + 1);
            displayElement(_out, "factcount", (ElementDef)this.factcount, _indent + 1);
            displayElementArray(_out, "measuresfactcount", (NodeDef[])this.measuresfactcount, _indent + 1);
            displayElementArray(_out, "ignoreColumns", (NodeDef[])this.ignoreColumns, _indent + 1);
            displayElementArray(_out, "foreignKeys", (NodeDef[])this.foreignKeys, _indent + 1);
            displayElementArray(_out, "measures", (NodeDef[])this.measures, _indent + 1);
            displayElementArray(_out, "levels", (NodeDef[])this.levels, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggTable", new XMLAttrVector().add("ignorecase", (Object)this.ignorecase));
            displayXMLElement(_out, (ElementDef)this.factcount);
            displayXMLElementArray(_out, (NodeDef[])this.measuresfactcount);
            displayXMLElementArray(_out, (NodeDef[])this.ignoreColumns);
            displayXMLElementArray(_out, (NodeDef[])this.foreignKeys);
            displayXMLElementArray(_out, (NodeDef[])this.measures);
            displayXMLElementArray(_out, (NodeDef[])this.levels);
            _out.endTag("AggTable");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggTable _cother = (AggTable)_other;
            boolean _diff = displayAttributeDiff("ignorecase", (Object)this.ignorecase, (Object)_cother.ignorecase, _out, _indent + 1);
            _diff = (_diff && displayElementDiff("factcount", (NodeDef)this.factcount, (NodeDef)_cother.factcount, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measuresfactcount", (NodeDef[])this.measuresfactcount, (NodeDef[])_cother.measuresfactcount, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("ignoreColumns", (NodeDef[])this.ignoreColumns, (NodeDef[])_cother.ignoreColumns, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("foreignKeys", (NodeDef[])this.foreignKeys, (NodeDef[])_cother.foreignKeys, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measures", (NodeDef[])this.measures, (NodeDef[])_cother.measures, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("levels", (NodeDef[])this.levels, (NodeDef[])_cother.levels, _out, _indent + 1));
            return _diff;
        }
        
        public boolean isIgnoreCase() {
            return this.ignorecase;
        }
        
        public AggFactCount getAggFactCount() {
            return this.factcount;
        }
        
        public AggMeasureFactCount[] getMeasuresFactCount() {
            return this.measuresfactcount;
        }
        
        public AggIgnoreColumn[] getAggIgnoreColumns() {
            return this.ignoreColumns;
        }
        
        public AggForeignKey[] getAggForeignKeys() {
            return this.foreignKeys;
        }
        
        public AggMeasure[] getAggMeasures() {
            return this.measures;
        }
        
        public AggLevel[] getAggLevels() {
            return this.levels;
        }
    }
    
    public static class AggName extends AggTable
    {
        public String name;
        public String approxRowCount;
        
        public AggName() {
        }
        
        public AggName(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.approxRowCount = (String)_parser.getAttribute("approxRowCount", "String", (String)null, (String[])null, false);
                this.ignorecase = (Boolean)_parser.getAttribute("ignorecase", "Boolean", "true", (String[])null, false);
                this.factcount = (AggFactCount)_parser.getElement((Class)AggFactCount.class, true);
                NodeDef[] _tempArray = _parser.getArray((Class)AggMeasureFactCount.class, 0, 0);
                this.measuresfactcount = new AggMeasureFactCount[_tempArray.length];
                for (int _i = 0; _i < this.measuresfactcount.length; ++_i) {
                    this.measuresfactcount[_i] = (AggMeasureFactCount)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggIgnoreColumn.class, 0, 0);
                this.ignoreColumns = new AggIgnoreColumn[_tempArray.length];
                for (int _i = 0; _i < this.ignoreColumns.length; ++_i) {
                    this.ignoreColumns[_i] = (AggIgnoreColumn)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggForeignKey.class, 0, 0);
                this.foreignKeys = new AggForeignKey[_tempArray.length];
                for (int _i = 0; _i < this.foreignKeys.length; ++_i) {
                    this.foreignKeys[_i] = (AggForeignKey)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggMeasure.class, 0, 0);
                this.measures = new AggMeasure[_tempArray.length];
                for (int _i = 0; _i < this.measures.length; ++_i) {
                    this.measures[_i] = (AggMeasure)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggLevel.class, 0, 0);
                this.levels = new AggLevel[_tempArray.length];
                for (int _i = 0; _i < this.levels.length; ++_i) {
                    this.levels[_i] = (AggLevel)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "AggName";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "approxRowCount", (Object)this.approxRowCount, _indent + 1);
            displayAttribute(_out, "ignorecase", (Object)this.ignorecase, _indent + 1);
            displayElement(_out, "factcount", (ElementDef)this.factcount, _indent + 1);
            displayElementArray(_out, "measuresfactcount", (NodeDef[])this.measuresfactcount, _indent + 1);
            displayElementArray(_out, "ignoreColumns", (NodeDef[])this.ignoreColumns, _indent + 1);
            displayElementArray(_out, "foreignKeys", (NodeDef[])this.foreignKeys, _indent + 1);
            displayElementArray(_out, "measures", (NodeDef[])this.measures, _indent + 1);
            displayElementArray(_out, "levels", (NodeDef[])this.levels, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggName", new XMLAttrVector().add("name", (Object)this.name).add("approxRowCount", (Object)this.approxRowCount).add("ignorecase", (Object)this.ignorecase));
            displayXMLElement(_out, (ElementDef)this.factcount);
            displayXMLElementArray(_out, (NodeDef[])this.measuresfactcount);
            displayXMLElementArray(_out, (NodeDef[])this.ignoreColumns);
            displayXMLElementArray(_out, (NodeDef[])this.foreignKeys);
            displayXMLElementArray(_out, (NodeDef[])this.measures);
            displayXMLElementArray(_out, (NodeDef[])this.levels);
            _out.endTag("AggName");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggName _cother = (AggName)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("approxRowCount", (Object)this.approxRowCount, (Object)_cother.approxRowCount, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("factcount", (NodeDef)this.factcount, (NodeDef)_cother.factcount, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measuresfactcount", (NodeDef[])this.measuresfactcount, (NodeDef[])_cother.measuresfactcount, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("ignoreColumns", (NodeDef[])this.ignoreColumns, (NodeDef[])_cother.ignoreColumns, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("foreignKeys", (NodeDef[])this.foreignKeys, (NodeDef[])_cother.foreignKeys, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measures", (NodeDef[])this.measures, (NodeDef[])_cother.measures, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("levels", (NodeDef[])this.levels, (NodeDef[])_cother.levels, _out, _indent + 1));
            return _diff;
        }
        
        public String getNameAttribute() {
            return this.name;
        }
        
        public String getApproxRowCountAttribute() {
            return this.approxRowCount;
        }
    }
    
    public static class AggPattern extends AggTable
    {
        public String pattern;
        public AggExclude[] excludes;
        
        public AggPattern() {
        }
        
        public AggPattern(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.pattern = (String)_parser.getAttribute("pattern", "String", (String)null, (String[])null, true);
                this.ignorecase = (Boolean)_parser.getAttribute("ignorecase", "Boolean", "true", (String[])null, false);
                this.factcount = (AggFactCount)_parser.getElement((Class)AggFactCount.class, true);
                NodeDef[] _tempArray = _parser.getArray((Class)AggMeasureFactCount.class, 0, 0);
                this.measuresfactcount = new AggMeasureFactCount[_tempArray.length];
                for (int _i = 0; _i < this.measuresfactcount.length; ++_i) {
                    this.measuresfactcount[_i] = (AggMeasureFactCount)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggIgnoreColumn.class, 0, 0);
                this.ignoreColumns = new AggIgnoreColumn[_tempArray.length];
                for (int _i = 0; _i < this.ignoreColumns.length; ++_i) {
                    this.ignoreColumns[_i] = (AggIgnoreColumn)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggForeignKey.class, 0, 0);
                this.foreignKeys = new AggForeignKey[_tempArray.length];
                for (int _i = 0; _i < this.foreignKeys.length; ++_i) {
                    this.foreignKeys[_i] = (AggForeignKey)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggMeasure.class, 0, 0);
                this.measures = new AggMeasure[_tempArray.length];
                for (int _i = 0; _i < this.measures.length; ++_i) {
                    this.measures[_i] = (AggMeasure)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggLevel.class, 0, 0);
                this.levels = new AggLevel[_tempArray.length];
                for (int _i = 0; _i < this.levels.length; ++_i) {
                    this.levels[_i] = (AggLevel)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggExclude.class, 0, 0);
                this.excludes = new AggExclude[_tempArray.length];
                for (int _i = 0; _i < this.excludes.length; ++_i) {
                    this.excludes[_i] = (AggExclude)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "AggPattern";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "pattern", (Object)this.pattern, _indent + 1);
            displayAttribute(_out, "ignorecase", (Object)this.ignorecase, _indent + 1);
            displayElement(_out, "factcount", (ElementDef)this.factcount, _indent + 1);
            displayElementArray(_out, "measuresfactcount", (NodeDef[])this.measuresfactcount, _indent + 1);
            displayElementArray(_out, "ignoreColumns", (NodeDef[])this.ignoreColumns, _indent + 1);
            displayElementArray(_out, "foreignKeys", (NodeDef[])this.foreignKeys, _indent + 1);
            displayElementArray(_out, "measures", (NodeDef[])this.measures, _indent + 1);
            displayElementArray(_out, "levels", (NodeDef[])this.levels, _indent + 1);
            displayElementArray(_out, "excludes", (NodeDef[])this.excludes, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggPattern", new XMLAttrVector().add("pattern", (Object)this.pattern).add("ignorecase", (Object)this.ignorecase));
            displayXMLElement(_out, (ElementDef)this.factcount);
            displayXMLElementArray(_out, (NodeDef[])this.measuresfactcount);
            displayXMLElementArray(_out, (NodeDef[])this.ignoreColumns);
            displayXMLElementArray(_out, (NodeDef[])this.foreignKeys);
            displayXMLElementArray(_out, (NodeDef[])this.measures);
            displayXMLElementArray(_out, (NodeDef[])this.levels);
            displayXMLElementArray(_out, (NodeDef[])this.excludes);
            _out.endTag("AggPattern");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggPattern _cother = (AggPattern)_other;
            boolean _diff = displayAttributeDiff("pattern", (Object)this.pattern, (Object)_cother.pattern, _out, _indent + 1);
            _diff = (_diff && displayElementDiff("factcount", (NodeDef)this.factcount, (NodeDef)_cother.factcount, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measuresfactcount", (NodeDef[])this.measuresfactcount, (NodeDef[])_cother.measuresfactcount, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("ignoreColumns", (NodeDef[])this.ignoreColumns, (NodeDef[])_cother.ignoreColumns, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("foreignKeys", (NodeDef[])this.foreignKeys, (NodeDef[])_cother.foreignKeys, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measures", (NodeDef[])this.measures, (NodeDef[])_cother.measures, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("levels", (NodeDef[])this.levels, (NodeDef[])_cother.levels, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("excludes", (NodeDef[])this.excludes, (NodeDef[])_cother.excludes, _out, _indent + 1));
            return _diff;
        }
        
        public String getPattern() {
            return this.pattern;
        }
        
        public AggExclude[] getAggExcludes() {
            return this.excludes;
        }
    }
    
    public static class AggExclude extends ElementDef
    {
        public String pattern;
        public String name;
        public Boolean ignorecase;
        
        public AggExclude() {
        }
        
        public AggExclude(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.pattern = (String)_parser.getAttribute("pattern", "String", (String)null, (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, false);
                this.ignorecase = (Boolean)_parser.getAttribute("ignorecase", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "AggExclude";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "pattern", (Object)this.pattern, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "ignorecase", (Object)this.ignorecase, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggExclude", new XMLAttrVector().add("pattern", (Object)this.pattern).add("name", (Object)this.name).add("ignorecase", (Object)this.ignorecase));
            _out.endTag("AggExclude");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggExclude _cother = (AggExclude)_other;
            boolean _diff = displayAttributeDiff("pattern", (Object)this.pattern, (Object)_cother.pattern, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("ignorecase", (Object)this.ignorecase, (Object)_cother.ignorecase, _out, _indent + 1));
            return _diff;
        }
        
        public String getNameAttribute() {
            return this.name;
        }
        
        public String getPattern() {
            return this.pattern;
        }
        
        public boolean isIgnoreCase() {
            return this.ignorecase;
        }
    }
    
    public abstract static class AggColumnName extends ElementDef
    {
        public String column;
        
        public AggColumnName() {
        }
        
        public AggColumnName(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "AggColumnName";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggColumnName", new XMLAttrVector().add("column", (Object)this.column));
            _out.endTag("AggColumnName");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggColumnName _cother = (AggColumnName)_other;
            final boolean _diff = displayAttributeDiff("column", (Object)this.column, (Object)_cother.column, _out, _indent + 1);
            return _diff;
        }
        
        public String getColumnName() {
            return this.column;
        }
    }
    
    public static class AggFactCount extends AggColumnName
    {
        public AggFactCount() {
        }
        
        public AggFactCount(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "AggFactCount";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggFactCount", new XMLAttrVector().add("column", (Object)this.column));
            _out.endTag("AggFactCount");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggFactCount _cother = (AggFactCount)_other;
            return true;
        }
    }
    
    public static class AggMeasureFactCount extends AggColumnName
    {
        public String factColumn;
        
        public AggMeasureFactCount() {
        }
        
        public AggMeasureFactCount(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.factColumn = (String)_parser.getAttribute("factColumn", "String", (String)null, (String[])null, true);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "AggMeasureFactCount";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "factColumn", (Object)this.factColumn, _indent + 1);
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggMeasureFactCount", new XMLAttrVector().add("factColumn", (Object)this.factColumn).add("column", (Object)this.column));
            _out.endTag("AggMeasureFactCount");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggMeasureFactCount _cother = (AggMeasureFactCount)_other;
            final boolean _diff = displayAttributeDiff("factColumn", (Object)this.factColumn, (Object)_cother.factColumn, _out, _indent + 1);
            return _diff;
        }
        
        public String getFactColumn() {
            return this.factColumn;
        }
    }
    
    public static class AggIgnoreColumn extends AggColumnName
    {
        public AggIgnoreColumn() {
        }
        
        public AggIgnoreColumn(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "AggIgnoreColumn";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggIgnoreColumn", new XMLAttrVector().add("column", (Object)this.column));
            _out.endTag("AggIgnoreColumn");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggIgnoreColumn _cother = (AggIgnoreColumn)_other;
            return true;
        }
    }
    
    public static class AggForeignKey extends ElementDef
    {
        public String factColumn;
        public String aggColumn;
        
        public AggForeignKey() {
        }
        
        public AggForeignKey(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.factColumn = (String)_parser.getAttribute("factColumn", "String", (String)null, (String[])null, true);
                this.aggColumn = (String)_parser.getAttribute("aggColumn", "String", (String)null, (String[])null, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "AggForeignKey";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "factColumn", (Object)this.factColumn, _indent + 1);
            displayAttribute(_out, "aggColumn", (Object)this.aggColumn, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggForeignKey", new XMLAttrVector().add("factColumn", (Object)this.factColumn).add("aggColumn", (Object)this.aggColumn));
            _out.endTag("AggForeignKey");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggForeignKey _cother = (AggForeignKey)_other;
            boolean _diff = displayAttributeDiff("factColumn", (Object)this.factColumn, (Object)_cother.factColumn, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("aggColumn", (Object)this.aggColumn, (Object)_cother.aggColumn, _out, _indent + 1));
            return _diff;
        }
        
        public String getFactFKColumnName() {
            return this.factColumn;
        }
        
        public String getAggregateFKColumnName() {
            return this.aggColumn;
        }
    }
    
    public static class AggLevel extends ElementDef
    {
        public String column;
        public String ordinalColumn;
        public String captionColumn;
        public String name;
        public String nameColumn;
        public Boolean collapsed;
        public AggLevelProperty[] properties;
        
        public AggLevel() {
        }
        
        public AggLevel(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, true);
                this.ordinalColumn = (String)_parser.getAttribute("ordinalColumn", "String", (String)null, (String[])null, false);
                this.captionColumn = (String)_parser.getAttribute("captionColumn", "String", (String)null, (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.nameColumn = (String)_parser.getAttribute("nameColumn", "String", (String)null, (String[])null, false);
                this.collapsed = (Boolean)_parser.getAttribute("collapsed", "Boolean", "true", (String[])null, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)AggLevelProperty.class, 0, 0);
                this.properties = new AggLevelProperty[_tempArray.length];
                for (int _i = 0; _i < this.properties.length; ++_i) {
                    this.properties[_i] = (AggLevelProperty)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "AggLevel";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
            displayAttribute(_out, "ordinalColumn", (Object)this.ordinalColumn, _indent + 1);
            displayAttribute(_out, "captionColumn", (Object)this.captionColumn, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "nameColumn", (Object)this.nameColumn, _indent + 1);
            displayAttribute(_out, "collapsed", (Object)this.collapsed, _indent + 1);
            displayElementArray(_out, "properties", (NodeDef[])this.properties, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggLevel", new XMLAttrVector().add("column", (Object)this.column).add("ordinalColumn", (Object)this.ordinalColumn).add("captionColumn", (Object)this.captionColumn).add("name", (Object)this.name).add("nameColumn", (Object)this.nameColumn).add("collapsed", (Object)this.collapsed));
            displayXMLElementArray(_out, (NodeDef[])this.properties);
            _out.endTag("AggLevel");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggLevel _cother = (AggLevel)_other;
            boolean _diff = displayAttributeDiff("column", (Object)this.column, (Object)_cother.column, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("ordinalColumn", (Object)this.ordinalColumn, (Object)_cother.ordinalColumn, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("captionColumn", (Object)this.captionColumn, (Object)_cother.captionColumn, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("nameColumn", (Object)this.nameColumn, (Object)_cother.nameColumn, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("collapsed", (Object)this.collapsed, (Object)_cother.collapsed, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("properties", (NodeDef[])this.properties, (NodeDef[])_cother.properties, _out, _indent + 1));
            return _diff;
        }
        
        public String getNameAttribute() {
            return this.name;
        }
        
        public String getColumnName() {
            return this.column;
        }
        
        public boolean isCollapsed() {
            return this.collapsed;
        }
    }
    
    public static class AggLevelProperty extends ElementDef
    {
        public String name;
        public String column;
        
        public AggLevelProperty() {
        }
        
        public AggLevelProperty(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, false);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "AggLevelProperty";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggLevelProperty", new XMLAttrVector().add("name", (Object)this.name).add("column", (Object)this.column));
            _out.endTag("AggLevelProperty");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggLevelProperty _cother = (AggLevelProperty)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("column", (Object)this.column, (Object)_cother.column, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class AggMeasure extends ElementDef
    {
        public String column;
        public String name;
        public String rollupType;
        
        public AggMeasure() {
        }
        
        public AggMeasure(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.column = (String)_parser.getAttribute("column", "String", (String)null, (String[])null, true);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.rollupType = (String)_parser.getAttribute("rollupType", "String", (String)null, (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "AggMeasure";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "column", (Object)this.column, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "rollupType", (Object)this.rollupType, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggMeasure", new XMLAttrVector().add("column", (Object)this.column).add("name", (Object)this.name).add("rollupType", (Object)this.rollupType));
            _out.endTag("AggMeasure");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggMeasure _cother = (AggMeasure)_other;
            boolean _diff = displayAttributeDiff("column", (Object)this.column, (Object)_cother.column, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("rollupType", (Object)this.rollupType, (Object)_cother.rollupType, _out, _indent + 1));
            return _diff;
        }
        
        public String getNameAttribute() {
            return this.name;
        }
        
        public String getColumn() {
            return this.column;
        }
        
        public String getRollupType() {
            return this.rollupType;
        }
    }
    
    public static class Column extends ElementDef implements Expression
    {
        public String table;
        public String name;
        private String genericExpression;
        
        public Column() {
        }
        
        public Column(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.table = (String)_parser.getAttribute("table", "String", (String)null, (String[])null, false);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Column";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "table", (Object)this.table, _indent + 1);
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Column", new XMLAttrVector().add("table", (Object)this.table).add("name", (Object)this.name));
            _out.endTag("Column");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Column _cother = (Column)_other;
            boolean _diff = displayAttributeDiff("table", (Object)this.table, (Object)_cother.table, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1));
            return _diff;
        }
        
        public Column(final String table, final String name) {
            this();
            Util.assertTrue(name != null);
            this.table = table;
            this.name = name;
            this.genericExpression = ((table == null) ? name : (table + "." + name));
        }
        
        public String getExpression(final SqlQuery query) {
            return query.getDialect().quoteIdentifier(this.table, this.name);
        }
        
        public String getGenericExpression() {
            return this.genericExpression;
        }
        
        public String getColumnName() {
            return this.name;
        }
        
        public String getTableAlias() {
            return this.table;
        }
        
        public int hashCode() {
            return this.name.hashCode() ^ ((this.table == null) ? 0 : this.table.hashCode());
        }
        
        public boolean equals(final Object obj) {
            if (!(obj instanceof Column)) {
                return false;
            }
            final Column that = (Column)obj;
            return this.name.equals(that.name) && Util.equals(this.table, that.table);
        }
    }
    
    public abstract static class ExpressionView extends ElementDef implements Expression
    {
        public SQL[] expressions;
        
        public ExpressionView() {
        }
        
        public ExpressionView(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)SQL.class, 1, 0);
                this.expressions = new SQL[_tempArray.length];
                for (int _i = 0; _i < this.expressions.length; ++_i) {
                    this.expressions[_i] = (SQL)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "ExpressionView";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "expressions", (NodeDef[])this.expressions, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("ExpressionView", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.expressions);
            _out.endTag("ExpressionView");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final ExpressionView _cother = (ExpressionView)_other;
            final boolean _diff = displayElementArrayDiff("expressions", (NodeDef[])this.expressions, (NodeDef[])_cother.expressions, _out, _indent + 1);
            return _diff;
        }
        
        public String toString() {
            return this.expressions[0].cdata;
        }
        
        public String getExpression(final SqlQuery query) {
            return SQL.toCodeSet(this.expressions).chooseQuery(query.getDialect());
        }
        
        public String getGenericExpression() {
            for (int i = 0; i < this.expressions.length; ++i) {
                if (this.expressions[i].dialect.equals("generic")) {
                    return this.expressions[i].cdata;
                }
            }
            return this.expressions[0].cdata;
        }
        
        public String getTableAlias() {
            return null;
        }
        
        public int hashCode() {
            int h = 17;
            for (int i = 0; i < this.expressions.length; ++i) {
                h = 37 * h + this.expressions[i].hashCode();
            }
            return h;
        }
        
        public boolean equals(final Object obj) {
            if (!(obj instanceof ExpressionView)) {
                return false;
            }
            final ExpressionView that = (ExpressionView)obj;
            if (this.expressions.length != that.expressions.length) {
                return false;
            }
            for (int i = 0; i < this.expressions.length; ++i) {
                if (!this.expressions[i].equals(that.expressions[i])) {
                    return false;
                }
            }
            return true;
        }
    }
    
    public static class KeyExpression extends ExpressionView
    {
        public KeyExpression() {
        }
        
        public KeyExpression(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)SQL.class, 1, 0);
                this.expressions = new SQL[_tempArray.length];
                for (int _i = 0; _i < this.expressions.length; ++_i) {
                    this.expressions[_i] = (SQL)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "KeyExpression";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "expressions", (NodeDef[])this.expressions, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("KeyExpression", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.expressions);
            _out.endTag("KeyExpression");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final KeyExpression _cother = (KeyExpression)_other;
            final boolean _diff = displayElementArrayDiff("expressions", (NodeDef[])this.expressions, (NodeDef[])_cother.expressions, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class ParentExpression extends ExpressionView
    {
        public ParentExpression() {
        }
        
        public ParentExpression(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)SQL.class, 1, 0);
                this.expressions = new SQL[_tempArray.length];
                for (int _i = 0; _i < this.expressions.length; ++_i) {
                    this.expressions[_i] = (SQL)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "ParentExpression";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "expressions", (NodeDef[])this.expressions, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("ParentExpression", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.expressions);
            _out.endTag("ParentExpression");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final ParentExpression _cother = (ParentExpression)_other;
            final boolean _diff = displayElementArrayDiff("expressions", (NodeDef[])this.expressions, (NodeDef[])_cother.expressions, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class OrdinalExpression extends ExpressionView
    {
        public OrdinalExpression() {
        }
        
        public OrdinalExpression(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)SQL.class, 1, 0);
                this.expressions = new SQL[_tempArray.length];
                for (int _i = 0; _i < this.expressions.length; ++_i) {
                    this.expressions[_i] = (SQL)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "OrdinalExpression";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "expressions", (NodeDef[])this.expressions, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("OrdinalExpression", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.expressions);
            _out.endTag("OrdinalExpression");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final OrdinalExpression _cother = (OrdinalExpression)_other;
            final boolean _diff = displayElementArrayDiff("expressions", (NodeDef[])this.expressions, (NodeDef[])_cother.expressions, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class NameExpression extends ExpressionView
    {
        public NameExpression() {
        }
        
        public NameExpression(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)SQL.class, 1, 0);
                this.expressions = new SQL[_tempArray.length];
                for (int _i = 0; _i < this.expressions.length; ++_i) {
                    this.expressions[_i] = (SQL)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "NameExpression";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "expressions", (NodeDef[])this.expressions, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("NameExpression", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.expressions);
            _out.endTag("NameExpression");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final NameExpression _cother = (NameExpression)_other;
            final boolean _diff = displayElementArrayDiff("expressions", (NodeDef[])this.expressions, (NodeDef[])_cother.expressions, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class CaptionExpression extends ExpressionView
    {
        public CaptionExpression() {
        }
        
        public CaptionExpression(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)SQL.class, 1, 0);
                this.expressions = new SQL[_tempArray.length];
                for (int _i = 0; _i < this.expressions.length; ++_i) {
                    this.expressions[_i] = (SQL)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "CaptionExpression";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "expressions", (NodeDef[])this.expressions, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CaptionExpression", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.expressions);
            _out.endTag("CaptionExpression");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CaptionExpression _cother = (CaptionExpression)_other;
            final boolean _diff = displayElementArrayDiff("expressions", (NodeDef[])this.expressions, (NodeDef[])_cother.expressions, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class MeasureExpression extends ExpressionView
    {
        public MeasureExpression() {
        }
        
        public MeasureExpression(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)SQL.class, 1, 0);
                this.expressions = new SQL[_tempArray.length];
                for (int _i = 0; _i < this.expressions.length; ++_i) {
                    this.expressions[_i] = (SQL)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "MeasureExpression";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "expressions", (NodeDef[])this.expressions, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("MeasureExpression", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.expressions);
            _out.endTag("MeasureExpression");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final MeasureExpression _cother = (MeasureExpression)_other;
            final boolean _diff = displayElementArrayDiff("expressions", (NodeDef[])this.expressions, (NodeDef[])_cother.expressions, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class Role extends ElementDef
    {
        public String name;
        public Annotations annotations;
        public SchemaGrant[] schemaGrants;
        public Union union;
        
        public Role() {
        }
        
        public Role(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.annotations = (Annotations)_parser.getElement((Class)Annotations.class, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)SchemaGrant.class, 0, 0);
                this.schemaGrants = new SchemaGrant[_tempArray.length];
                for (int _i = 0; _i < this.schemaGrants.length; ++_i) {
                    this.schemaGrants[_i] = (SchemaGrant)_tempArray[_i];
                }
                this.union = (Union)_parser.getElement((Class)Union.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Role";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayElement(_out, "annotations", (ElementDef)this.annotations, _indent + 1);
            displayElementArray(_out, "schemaGrants", (NodeDef[])this.schemaGrants, _indent + 1);
            displayElement(_out, "union", (ElementDef)this.union, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Role", new XMLAttrVector().add("name", (Object)this.name));
            displayXMLElement(_out, (ElementDef)this.annotations);
            displayXMLElementArray(_out, (NodeDef[])this.schemaGrants);
            displayXMLElement(_out, (ElementDef)this.union);
            _out.endTag("Role");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Role _cother = (Role)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayElementDiff("annotations", (NodeDef)this.annotations, (NodeDef)_cother.annotations, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("schemaGrants", (NodeDef[])this.schemaGrants, (NodeDef[])_cother.schemaGrants, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("union", (NodeDef)this.union, (NodeDef)_cother.union, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class SchemaGrant extends ElementDef implements Grant
    {
        public static final String[] _access_values;
        public String access;
        public CubeGrant[] cubeGrants;
        
        public SchemaGrant() {
        }
        
        public SchemaGrant(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.access = (String)_parser.getAttribute("access", "String", (String)null, SchemaGrant._access_values, true);
                final NodeDef[] _tempArray = _parser.getArray((Class)CubeGrant.class, 0, 0);
                this.cubeGrants = new CubeGrant[_tempArray.length];
                for (int _i = 0; _i < this.cubeGrants.length; ++_i) {
                    this.cubeGrants[_i] = (CubeGrant)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "SchemaGrant";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "access", (Object)this.access, _indent + 1);
            displayElementArray(_out, "cubeGrants", (NodeDef[])this.cubeGrants, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("SchemaGrant", new XMLAttrVector().add("access", (Object)this.access));
            displayXMLElementArray(_out, (NodeDef[])this.cubeGrants);
            _out.endTag("SchemaGrant");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final SchemaGrant _cother = (SchemaGrant)_other;
            final boolean _diff = displayElementArrayDiff("cubeGrants", (NodeDef[])this.cubeGrants, (NodeDef[])_cother.cubeGrants, _out, _indent + 1);
            return _diff;
        }
        
        static {
            _access_values = new String[] { "all", "custom", "none", "all_dimensions" };
        }
    }
    
    public static class CubeGrant extends ElementDef implements Grant
    {
        public static final String[] _access_values;
        public String access;
        public String cube;
        public DimensionGrant[] dimensionGrants;
        public HierarchyGrant[] hierarchyGrants;
        
        public CubeGrant() {
        }
        
        public CubeGrant(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.cube = (String)_parser.getAttribute("cube", "String", (String)null, (String[])null, true);
                this.access = (String)_parser.getAttribute("access", "String", (String)null, CubeGrant._access_values, true);
                NodeDef[] _tempArray = _parser.getArray((Class)DimensionGrant.class, 0, 0);
                this.dimensionGrants = new DimensionGrant[_tempArray.length];
                for (int _i = 0; _i < this.dimensionGrants.length; ++_i) {
                    this.dimensionGrants[_i] = (DimensionGrant)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)HierarchyGrant.class, 0, 0);
                this.hierarchyGrants = new HierarchyGrant[_tempArray.length];
                for (int _i = 0; _i < this.hierarchyGrants.length; ++_i) {
                    this.hierarchyGrants[_i] = (HierarchyGrant)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "CubeGrant";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "cube", (Object)this.cube, _indent + 1);
            displayAttribute(_out, "access", (Object)this.access, _indent + 1);
            displayElementArray(_out, "dimensionGrants", (NodeDef[])this.dimensionGrants, _indent + 1);
            displayElementArray(_out, "hierarchyGrants", (NodeDef[])this.hierarchyGrants, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CubeGrant", new XMLAttrVector().add("cube", (Object)this.cube).add("access", (Object)this.access));
            displayXMLElementArray(_out, (NodeDef[])this.dimensionGrants);
            displayXMLElementArray(_out, (NodeDef[])this.hierarchyGrants);
            _out.endTag("CubeGrant");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CubeGrant _cother = (CubeGrant)_other;
            boolean _diff = displayAttributeDiff("cube", (Object)this.cube, (Object)_cother.cube, _out, _indent + 1);
            _diff = (_diff && displayElementArrayDiff("dimensionGrants", (NodeDef[])this.dimensionGrants, (NodeDef[])_cother.dimensionGrants, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("hierarchyGrants", (NodeDef[])this.hierarchyGrants, (NodeDef[])_cother.hierarchyGrants, _out, _indent + 1));
            return _diff;
        }
        
        static {
            _access_values = new String[] { "all", "custom", "none", "all_dimensions" };
        }
    }
    
    public static class DimensionGrant extends ElementDef implements Grant
    {
        public static final String[] _access_values;
        public String access;
        public String dimension;
        
        public DimensionGrant() {
        }
        
        public DimensionGrant(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.dimension = (String)_parser.getAttribute("dimension", "String", (String)null, (String[])null, true);
                this.access = (String)_parser.getAttribute("access", "String", (String)null, DimensionGrant._access_values, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "DimensionGrant";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "dimension", (Object)this.dimension, _indent + 1);
            displayAttribute(_out, "access", (Object)this.access, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("DimensionGrant", new XMLAttrVector().add("dimension", (Object)this.dimension).add("access", (Object)this.access));
            _out.endTag("DimensionGrant");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final DimensionGrant _cother = (DimensionGrant)_other;
            final boolean _diff = displayAttributeDiff("dimension", (Object)this.dimension, (Object)_cother.dimension, _out, _indent + 1);
            return _diff;
        }
        
        static {
            _access_values = new String[] { "all", "custom", "none", "all_dimensions" };
        }
    }
    
    public static class HierarchyGrant extends ElementDef implements Grant
    {
        public static final String[] _access_values;
        public String access;
        public String hierarchy;
        public String topLevel;
        public String bottomLevel;
        public String rollupPolicy;
        public MemberGrant[] memberGrants;
        
        public HierarchyGrant() {
        }
        
        public HierarchyGrant(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.hierarchy = (String)_parser.getAttribute("hierarchy", "String", (String)null, (String[])null, true);
                this.topLevel = (String)_parser.getAttribute("topLevel", "String", (String)null, (String[])null, false);
                this.bottomLevel = (String)_parser.getAttribute("bottomLevel", "String", (String)null, (String[])null, false);
                this.rollupPolicy = (String)_parser.getAttribute("rollupPolicy", "String", (String)null, (String[])null, false);
                this.access = (String)_parser.getAttribute("access", "String", (String)null, HierarchyGrant._access_values, true);
                final NodeDef[] _tempArray = _parser.getArray((Class)MemberGrant.class, 0, 0);
                this.memberGrants = new MemberGrant[_tempArray.length];
                for (int _i = 0; _i < this.memberGrants.length; ++_i) {
                    this.memberGrants[_i] = (MemberGrant)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "HierarchyGrant";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "hierarchy", (Object)this.hierarchy, _indent + 1);
            displayAttribute(_out, "topLevel", (Object)this.topLevel, _indent + 1);
            displayAttribute(_out, "bottomLevel", (Object)this.bottomLevel, _indent + 1);
            displayAttribute(_out, "rollupPolicy", (Object)this.rollupPolicy, _indent + 1);
            displayAttribute(_out, "access", (Object)this.access, _indent + 1);
            displayElementArray(_out, "memberGrants", (NodeDef[])this.memberGrants, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("HierarchyGrant", new XMLAttrVector().add("hierarchy", (Object)this.hierarchy).add("topLevel", (Object)this.topLevel).add("bottomLevel", (Object)this.bottomLevel).add("rollupPolicy", (Object)this.rollupPolicy).add("access", (Object)this.access));
            displayXMLElementArray(_out, (NodeDef[])this.memberGrants);
            _out.endTag("HierarchyGrant");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final HierarchyGrant _cother = (HierarchyGrant)_other;
            boolean _diff = displayAttributeDiff("hierarchy", (Object)this.hierarchy, (Object)_cother.hierarchy, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("topLevel", (Object)this.topLevel, (Object)_cother.topLevel, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("bottomLevel", (Object)this.bottomLevel, (Object)_cother.bottomLevel, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("rollupPolicy", (Object)this.rollupPolicy, (Object)_cother.rollupPolicy, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("memberGrants", (NodeDef[])this.memberGrants, (NodeDef[])_cother.memberGrants, _out, _indent + 1));
            return _diff;
        }
        
        static {
            _access_values = new String[] { "all", "custom", "none", "all_dimensions" };
        }
    }
    
    public static class MemberGrant extends ElementDef
    {
        public String member;
        public static final String[] _access_values;
        public String access;
        
        public MemberGrant() {
        }
        
        public MemberGrant(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.member = (String)_parser.getAttribute("member", "String", (String)null, (String[])null, true);
                this.access = (String)_parser.getAttribute("access", "String", (String)null, MemberGrant._access_values, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "MemberGrant";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "member", (Object)this.member, _indent + 1);
            displayAttribute(_out, "access", (Object)this.access, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("MemberGrant", new XMLAttrVector().add("member", (Object)this.member).add("access", (Object)this.access));
            _out.endTag("MemberGrant");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final MemberGrant _cother = (MemberGrant)_other;
            boolean _diff = displayAttributeDiff("member", (Object)this.member, (Object)_cother.member, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("access", (Object)this.access, (Object)_cother.access, _out, _indent + 1));
            return _diff;
        }
        
        static {
            _access_values = new String[] { "all", "none" };
        }
    }
    
    public static class Union extends ElementDef
    {
        public RoleUsage[] roleUsages;
        
        public Union() {
        }
        
        public Union(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)RoleUsage.class, 0, 0);
                this.roleUsages = new RoleUsage[_tempArray.length];
                for (int _i = 0; _i < this.roleUsages.length; ++_i) {
                    this.roleUsages[_i] = (RoleUsage)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Union";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "roleUsages", (NodeDef[])this.roleUsages, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Union", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.roleUsages);
            _out.endTag("Union");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Union _cother = (Union)_other;
            final boolean _diff = displayElementArrayDiff("roleUsages", (NodeDef[])this.roleUsages, (NodeDef[])_cother.roleUsages, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class RoleUsage extends ElementDef
    {
        public String roleName;
        
        public RoleUsage() {
        }
        
        public RoleUsage(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.roleName = (String)_parser.getAttribute("roleName", "String", (String)null, (String[])null, true);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "RoleUsage";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "roleName", (Object)this.roleName, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("RoleUsage", new XMLAttrVector().add("roleName", (Object)this.roleName));
            _out.endTag("RoleUsage");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final RoleUsage _cother = (RoleUsage)_other;
            final boolean _diff = displayAttributeDiff("roleName", (Object)this.roleName, (Object)_cother.roleName, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class UserDefinedFunction extends ElementDef
    {
        public String name;
        public String className;
        public Script script;
        
        public UserDefinedFunction() {
        }
        
        public UserDefinedFunction(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.className = (String)_parser.getAttribute("className", "String", (String)null, (String[])null, false);
                this.script = (Script)_parser.getElement((Class)Script.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "UserDefinedFunction";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "className", (Object)this.className, _indent + 1);
            displayElement(_out, "script", (ElementDef)this.script, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("UserDefinedFunction", new XMLAttrVector().add("name", (Object)this.name).add("className", (Object)this.className));
            displayXMLElement(_out, (ElementDef)this.script);
            _out.endTag("UserDefinedFunction");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final UserDefinedFunction _cother = (UserDefinedFunction)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("className", (Object)this.className, (Object)_cother.className, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("script", (NodeDef)this.script, (NodeDef)_cother.script, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class Parameter extends ElementDef
    {
        public String name;
        public String description;
        public static final String[] _type_values;
        public String type;
        public Boolean modifiable;
        public String defaultValue;
        
        public Parameter() {
        }
        
        public Parameter(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.description = (String)_parser.getAttribute("description", "String", (String)null, (String[])null, false);
                this.type = (String)_parser.getAttribute("type", "String", "String", Parameter._type_values, true);
                this.modifiable = (Boolean)_parser.getAttribute("modifiable", "Boolean", "true", (String[])null, false);
                this.defaultValue = (String)_parser.getAttribute("defaultValue", "String", (String)null, (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Parameter";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayAttribute(_out, "description", (Object)this.description, _indent + 1);
            displayAttribute(_out, "type", (Object)this.type, _indent + 1);
            displayAttribute(_out, "modifiable", (Object)this.modifiable, _indent + 1);
            displayAttribute(_out, "defaultValue", (Object)this.defaultValue, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Parameter", new XMLAttrVector().add("name", (Object)this.name).add("description", (Object)this.description).add("type", (Object)this.type).add("modifiable", (Object)this.modifiable).add("defaultValue", (Object)this.defaultValue));
            _out.endTag("Parameter");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Parameter _cother = (Parameter)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("description", (Object)this.description, (Object)_cother.description, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("type", (Object)this.type, (Object)_cother.type, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("modifiable", (Object)this.modifiable, (Object)_cother.modifiable, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("defaultValue", (Object)this.defaultValue, (Object)_cother.defaultValue, _out, _indent + 1));
            return _diff;
        }
        
        static {
            _type_values = new String[] { "String", "Numeric", "Integer", "Boolean", "Date", "Time", "Timestamp", "Member" };
        }
    }
    
    public static class Annotations extends ElementDef
    {
        public Annotation[] array;
        
        public Annotations() {
        }
        
        public Annotations(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                final NodeDef[] _tempArray = _parser.getArray((Class)Annotation.class, 0, 0);
                this.array = new Annotation[_tempArray.length];
                for (int _i = 0; _i < this.array.length; ++_i) {
                    this.array[_i] = (Annotation)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Annotations";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayElementArray(_out, "array", (NodeDef[])this.array, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Annotations", new XMLAttrVector());
            displayXMLElementArray(_out, (NodeDef[])this.array);
            _out.endTag("Annotations");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Annotations _cother = (Annotations)_other;
            final boolean _diff = displayElementArrayDiff("array", (NodeDef[])this.array, (NodeDef[])_cother.array, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class Annotation extends ElementDef
    {
        public String name;
        public String cdata;
        
        public Annotation() {
        }
        
        public Annotation(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.name = (String)_parser.getAttribute("name", "String", (String)null, (String[])null, true);
                this.cdata = _parser.getText();
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Annotation";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "name", (Object)this.name, _indent + 1);
            displayString(_out, "cdata", this.cdata, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Annotation", new XMLAttrVector().add("name", (Object)this.name));
            _out.cdata(this.cdata);
            _out.endTag("Annotation");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Annotation _cother = (Annotation)_other;
            boolean _diff = displayAttributeDiff("name", (Object)this.name, (Object)_cother.name, _out, _indent + 1);
            _diff = (_diff && displayStringDiff("cdata", this.cdata, _cother.cdata, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class Script extends ElementDef
    {
        public String language;
        public String cdata;
        
        public Script() {
        }
        
        public Script(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.language = (String)_parser.getAttribute("language", "String", "JavaScript", (String[])null, false);
                this.cdata = _parser.getText();
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Script";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "language", (Object)this.language, _indent + 1);
            displayString(_out, "cdata", this.cdata, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Script", new XMLAttrVector().add("language", (Object)this.language));
            _out.cdata(this.cdata);
            _out.endTag("Script");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Script _cother = (Script)_other;
            boolean _diff = displayAttributeDiff("language", (Object)this.language, (Object)_cother.language, _out, _indent + 1);
            _diff = (_diff && displayStringDiff("cdata", this.cdata, _cother.cdata, _out, _indent + 1));
            return _diff;
        }
    }
    
    public abstract static class ElementFormatter extends ElementDef
    {
        public String className;
        public Script script;
        
        public ElementFormatter() {
        }
        
        public ElementFormatter(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.className = (String)_parser.getAttribute("className", "String", (String)null, (String[])null, false);
                this.script = (Script)_parser.getElement((Class)Script.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "ElementFormatter";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "className", (Object)this.className, _indent + 1);
            displayElement(_out, "script", (ElementDef)this.script, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("ElementFormatter", new XMLAttrVector().add("className", (Object)this.className));
            displayXMLElement(_out, (ElementDef)this.script);
            _out.endTag("ElementFormatter");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final ElementFormatter _cother = (ElementFormatter)_other;
            boolean _diff = displayAttributeDiff("className", (Object)this.className, (Object)_cother.className, _out, _indent + 1);
            _diff = (_diff && displayElementDiff("script", (NodeDef)this.script, (NodeDef)_cother.script, _out, _indent + 1));
            return _diff;
        }
    }
    
    public static class CellFormatter extends ElementFormatter
    {
        public CellFormatter() {
        }
        
        public CellFormatter(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.className = (String)_parser.getAttribute("className", "String", (String)null, (String[])null, false);
                this.script = (Script)_parser.getElement((Class)Script.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "CellFormatter";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "className", (Object)this.className, _indent + 1);
            displayElement(_out, "script", (ElementDef)this.script, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CellFormatter", new XMLAttrVector().add("className", (Object)this.className));
            displayXMLElement(_out, (ElementDef)this.script);
            _out.endTag("CellFormatter");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CellFormatter _cother = (CellFormatter)_other;
            final boolean _diff = displayElementDiff("script", (NodeDef)this.script, (NodeDef)_cother.script, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class MemberFormatter extends ElementFormatter
    {
        public MemberFormatter() {
        }
        
        public MemberFormatter(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.className = (String)_parser.getAttribute("className", "String", (String)null, (String[])null, false);
                this.script = (Script)_parser.getElement((Class)Script.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "MemberFormatter";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "className", (Object)this.className, _indent + 1);
            displayElement(_out, "script", (ElementDef)this.script, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("MemberFormatter", new XMLAttrVector().add("className", (Object)this.className));
            displayXMLElement(_out, (ElementDef)this.script);
            _out.endTag("MemberFormatter");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final MemberFormatter _cother = (MemberFormatter)_other;
            final boolean _diff = displayElementDiff("script", (NodeDef)this.script, (NodeDef)_cother.script, _out, _indent + 1);
            return _diff;
        }
    }
    
    public static class PropertyFormatter extends ElementFormatter
    {
        public PropertyFormatter() {
        }
        
        public PropertyFormatter(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)MondrianDef.class);
                this.className = (String)_parser.getAttribute("className", "String", (String)null, (String[])null, false);
                this.script = (Script)_parser.getElement((Class)Script.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "PropertyFormatter";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "className", (Object)this.className, _indent + 1);
            displayElement(_out, "script", (ElementDef)this.script, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("PropertyFormatter", new XMLAttrVector().add("className", (Object)this.className));
            displayXMLElement(_out, (ElementDef)this.script);
            _out.endTag("PropertyFormatter");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final PropertyFormatter _cother = (PropertyFormatter)_other;
            final boolean _diff = displayElementDiff("script", (NodeDef)this.script, (NodeDef)_cother.script, _out, _indent + 1);
            return _diff;
        }
    }
    
    public interface Grant extends NodeDef
    {
    }
    
    public interface Expression extends NodeDef
    {
        String getExpression(final SqlQuery p0);
        
        String getGenericExpression();
        
        String getTableAlias();
    }
}
