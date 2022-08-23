// 
// Decompiled by Procyon v0.5.36
// 

package mondrian8.rolap.aggmatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import mondrian8.recorder.MessageRecorder;
import org.eigenbase.xom.XMLAttrVector;
import org.eigenbase.xom.XMLOutput;
import java.io.PrintWriter;
import org.eigenbase.xom.NodeDef;
import org.eigenbase.xom.XOMException;
import org.eigenbase.xom.DOMElementParser;
import org.eigenbase.xom.DOMWrapper;
import org.apache.logging.log4j.Logger;
import org.eigenbase.xom.ElementDef;

public class DefaultDef
{
    public static String[] _elements;
    
    public static Class getXMLDefClass() {
        return DefaultDef.class;
    }
    
    static {
        DefaultDef._elements = new String[] { "AggRules", "Base", "CaseMatcher", "NameMatcher", "FactCountMatch", "ForeignKeyMatch", "TableMatch", "Mapper", "Regex", "RegexMapper", "Ref", "LevelMapRef", "MeasureMapRef", "IgnoreMapRef", "FactCountMatchRef", "ForeignKeyMatchRef", "TableMatchRef", "LevelMap", "MeasureMap", "IgnoreMap", "AggRule" };
    }
    
    public static class AggRules extends ElementDef
    {
        public String tag;
        public TableMatch[] tableMatches;
        public FactCountMatch[] factCountMatches;
        public ForeignKeyMatch[] foreignKeyMatches;
        public LevelMap[] levelMaps;
        public MeasureMap[] measureMaps;
        public IgnoreMap[] ignoreMaps;
        public AggRule[] aggRules;
        private static final Logger LOGGER;
        
        public AggRules() {
        }
        
        public AggRules(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.tag = (String)_parser.getAttribute("tag", "String", (String)null, (String[])null, true);
                NodeDef[] _tempArray = _parser.getArray((Class)TableMatch.class, 0, 0);
                this.tableMatches = new TableMatch[_tempArray.length];
                for (int _i = 0; _i < this.tableMatches.length; ++_i) {
                    this.tableMatches[_i] = (TableMatch)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)FactCountMatch.class, 0, 0);
                this.factCountMatches = new FactCountMatch[_tempArray.length];
                for (int _i = 0; _i < this.factCountMatches.length; ++_i) {
                    this.factCountMatches[_i] = (FactCountMatch)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)ForeignKeyMatch.class, 0, 0);
                this.foreignKeyMatches = new ForeignKeyMatch[_tempArray.length];
                for (int _i = 0; _i < this.foreignKeyMatches.length; ++_i) {
                    this.foreignKeyMatches[_i] = (ForeignKeyMatch)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)LevelMap.class, 0, 0);
                this.levelMaps = new LevelMap[_tempArray.length];
                for (int _i = 0; _i < this.levelMaps.length; ++_i) {
                    this.levelMaps[_i] = (LevelMap)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)MeasureMap.class, 0, 0);
                this.measureMaps = new MeasureMap[_tempArray.length];
                for (int _i = 0; _i < this.measureMaps.length; ++_i) {
                    this.measureMaps[_i] = (MeasureMap)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)IgnoreMap.class, 0, 0);
                this.ignoreMaps = new IgnoreMap[_tempArray.length];
                for (int _i = 0; _i < this.ignoreMaps.length; ++_i) {
                    this.ignoreMaps[_i] = (IgnoreMap)_tempArray[_i];
                }
                _tempArray = _parser.getArray((Class)AggRule.class, 1, 0);
                this.aggRules = new AggRule[_tempArray.length];
                for (int _i = 0; _i < this.aggRules.length; ++_i) {
                    this.aggRules[_i] = (AggRule)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "AggRules";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "tag", (Object)this.tag, _indent + 1);
            displayElementArray(_out, "tableMatches", (NodeDef[])this.tableMatches, _indent + 1);
            displayElementArray(_out, "factCountMatches", (NodeDef[])this.factCountMatches, _indent + 1);
            displayElementArray(_out, "foreignKeyMatches", (NodeDef[])this.foreignKeyMatches, _indent + 1);
            displayElementArray(_out, "levelMaps", (NodeDef[])this.levelMaps, _indent + 1);
            displayElementArray(_out, "measureMaps", (NodeDef[])this.measureMaps, _indent + 1);
            displayElementArray(_out, "ignoreMaps", (NodeDef[])this.ignoreMaps, _indent + 1);
            displayElementArray(_out, "aggRules", (NodeDef[])this.aggRules, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggRules", new XMLAttrVector().add("tag", (Object)this.tag));
            displayXMLElementArray(_out, (NodeDef[])this.tableMatches);
            displayXMLElementArray(_out, (NodeDef[])this.factCountMatches);
            displayXMLElementArray(_out, (NodeDef[])this.foreignKeyMatches);
            displayXMLElementArray(_out, (NodeDef[])this.levelMaps);
            displayXMLElementArray(_out, (NodeDef[])this.measureMaps);
            displayXMLElementArray(_out, (NodeDef[])this.ignoreMaps);
            displayXMLElementArray(_out, (NodeDef[])this.aggRules);
            _out.endTag("AggRules");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggRules _cother = (AggRules)_other;
            boolean _diff = displayAttributeDiff("tag", (Object)this.tag, (Object)_cother.tag, _out, _indent + 1);
            _diff = (_diff && displayElementArrayDiff("tableMatches", (NodeDef[])this.tableMatches, (NodeDef[])_cother.tableMatches, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("factCountMatches", (NodeDef[])this.factCountMatches, (NodeDef[])_cother.factCountMatches, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("foreignKeyMatches", (NodeDef[])this.foreignKeyMatches, (NodeDef[])_cother.foreignKeyMatches, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("levelMaps", (NodeDef[])this.levelMaps, (NodeDef[])_cother.levelMaps, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("measureMaps", (NodeDef[])this.measureMaps, (NodeDef[])_cother.measureMaps, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("ignoreMaps", (NodeDef[])this.ignoreMaps, (NodeDef[])_cother.ignoreMaps, _out, _indent + 1));
            _diff = (_diff && displayElementArrayDiff("aggRules", (NodeDef[])this.aggRules, (NodeDef[])_cother.aggRules, _out, _indent + 1));
            return _diff;
        }
        
        protected static Logger getLogger() {
            return AggRules.LOGGER;
        }
        
        public String getTag() {
            return this.tag;
        }
        
        public AggRule getAggRule(final String tag) {
            for (int i = 0; i < this.aggRules.length; ++i) {
                final AggRule aggRule = this.aggRules[i];
                if (aggRule.isEnabled() && aggRule.getTag().equals(tag)) {
                    return aggRule;
                }
            }
            return null;
        }
        
        public void validate(final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                this.validate(this.factCountMatches, msgRecorder);
                this.validate(this.tableMatches, msgRecorder);
                this.validate(this.levelMaps, msgRecorder);
                this.validate(this.measureMaps, msgRecorder);
                this.validate(this.ignoreMaps, msgRecorder);
                this.validate(this.aggRules, msgRecorder);
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        private void validate(final Base[] bases, final MessageRecorder msgRecorder) {
            for (int i = 0; i < bases.length; ++i) {
                final Base base = bases[i];
                if (base.isEnabled()) {
                    base.validate(this, msgRecorder);
                }
            }
        }
        
        public boolean hasIgnoreMap(final String id) {
            return this.lookupIgnoreMap(id) != null;
        }
        
        public IgnoreMap lookupIgnoreMap(final String id) {
            return (IgnoreMap)this.lookupBase(id, this.ignoreMaps);
        }
        
        public boolean hasFactCountMatch(final String id) {
            return this.lookupFactCountMatch(id) != null;
        }
        
        public FactCountMatch lookupFactCountMatch(final String id) {
            return (FactCountMatch)this.lookupBase(id, this.factCountMatches);
        }
        
        public boolean hasForeignKeyMatch(final String id) {
            return this.lookupForeignKeyMatch(id) != null;
        }
        
        public ForeignKeyMatch lookupForeignKeyMatch(final String id) {
            return (ForeignKeyMatch)this.lookupBase(id, this.foreignKeyMatches);
        }
        
        public boolean hasTableMatch(final String id) {
            return this.lookupTableMatch(id) != null;
        }
        
        public TableMatch lookupTableMatch(final String id) {
            return (TableMatch)this.lookupBase(id, this.tableMatches);
        }
        
        public boolean hasLevelMap(final String id) {
            return this.lookupLevelMap(id) != null;
        }
        
        public LevelMap lookupLevelMap(final String id) {
            return (LevelMap)this.lookupBase(id, this.levelMaps);
        }
        
        public boolean hasMeasureMap(final String id) {
            return this.lookupMeasureMap(id) != null;
        }
        
        public MeasureMap lookupMeasureMap(final String id) {
            return (MeasureMap)this.lookupBase(id, this.measureMaps);
        }
        
        public boolean hasAggRule(final String id) {
            return this.lookupAggRule(id) != null;
        }
        
        public AggRule lookupAggRule(final String id) {
            return (AggRule)this.lookupBase(id, this.aggRules);
        }
        
        private Base lookupBase(final String tag, final Base[] bases) {
            for (int i = 0; i < bases.length; ++i) {
                final Base base = bases[i];
                if (base.isEnabled() && base.getTag().equals(tag)) {
                    return base;
                }
            }
            return null;
        }
        
        public IgnoreMap[] getIgnoreMaps() {
            return this.ignoreMaps;
        }
        
        public FactCountMatch[] getFactCountMatches() {
            return this.factCountMatches;
        }
        
        public ForeignKeyMatch[] getForeignKeyMatches() {
            return this.foreignKeyMatches;
        }
        
        public TableMatch[] getTableMatches() {
            return this.tableMatches;
        }
        
        public LevelMap[] getLevelMaps() {
            return this.levelMaps;
        }
        
        public MeasureMap[] getMeasureMaps() {
            return this.measureMaps;
        }
        
        public AggRule[] getAggRules() {
            return this.aggRules;
        }
        
        static {
            LOGGER = LogManager.getLogger((Class)DefaultDef.class);
        }
    }
    
    public abstract static class Base extends ElementDef
    {
        public Boolean enabled;
        
        public Base() {
        }
        
        public Base(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        public String getName() {
            return "Base";
        }
        
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Base", new XMLAttrVector().add("enabled", (Object)this.enabled));
            _out.endTag("Base");
        }
        
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Base _cother = (Base)_other;
            final boolean _diff = displayAttributeDiff("enabled", (Object)this.enabled, (Object)_cother.enabled, _out, _indent + 1);
            return _diff;
        }
        
        public boolean isEnabled() {
            return this.enabled;
        }
        
        protected abstract String getTag();
        
        public abstract void validate(final AggRules p0, final MessageRecorder p1);
    }
    
    public abstract static class CaseMatcher extends Base
    {
        public String id;
        public static final String[] _charcase_values;
        public String charcase;
        
        public CaseMatcher() {
        }
        
        public CaseMatcher(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.charcase = (String)_parser.getAttribute("charcase", "String", "ignore", CaseMatcher._charcase_values, false);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "CaseMatcher";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "charcase", (Object)this.charcase, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("CaseMatcher", new XMLAttrVector().add("id", (Object)this.id).add("charcase", (Object)this.charcase).add("enabled", (Object)this.enabled));
            _out.endTag("CaseMatcher");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final CaseMatcher _cother = (CaseMatcher)_other;
            boolean _diff = displayAttributeDiff("id", (Object)this.id, (Object)_cother.id, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("charcase", (Object)this.charcase, (Object)_cother.charcase, _out, _indent + 1));
            return _diff;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
        }
        
        @Override
        protected String getTag() {
            return this.getId();
        }
        
        public String getId() {
            return this.id;
        }
        
        public String getCharCase() {
            return this.charcase;
        }
        
        static {
            _charcase_values = new String[] { "ignore", "exact", "upper", "lower" };
        }
    }
    
    public abstract static class NameMatcher extends CaseMatcher
    {
        public String pretemplate;
        public String posttemplate;
        public String basename;
        Pattern baseNamePattern;
        
        public NameMatcher() {
            this.baseNamePattern = null;
        }
        
        public NameMatcher(final DOMWrapper _def) throws XOMException {
            this.baseNamePattern = null;
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.pretemplate = (String)_parser.getAttribute("pretemplate", "String", (String)null, (String[])null, false);
                this.posttemplate = (String)_parser.getAttribute("posttemplate", "String", (String)null, (String[])null, false);
                this.basename = (String)_parser.getAttribute("basename", "String", (String)null, (String[])null, false);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.charcase = (String)_parser.getAttribute("charcase", "String", "ignore", NameMatcher._charcase_values, false);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "NameMatcher";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "pretemplate", (Object)this.pretemplate, _indent + 1);
            displayAttribute(_out, "posttemplate", (Object)this.posttemplate, _indent + 1);
            displayAttribute(_out, "basename", (Object)this.basename, _indent + 1);
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "charcase", (Object)this.charcase, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("NameMatcher", new XMLAttrVector().add("pretemplate", (Object)this.pretemplate).add("posttemplate", (Object)this.posttemplate).add("basename", (Object)this.basename).add("id", (Object)this.id).add("charcase", (Object)this.charcase).add("enabled", (Object)this.enabled));
            _out.endTag("NameMatcher");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final NameMatcher _cother = (NameMatcher)_other;
            boolean _diff = displayAttributeDiff("pretemplate", (Object)this.pretemplate, (Object)_cother.pretemplate, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("posttemplate", (Object)this.posttemplate, (Object)_cother.posttemplate, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("basename", (Object)this.basename, (Object)_cother.basename, _out, _indent + 1));
            return _diff;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                super.validate(rules, msgRecorder);
                if (this.basename != null) {
                    this.baseNamePattern = Pattern.compile(this.basename);
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        public String getRegex(final String name) {
            final StringBuilder buf = new StringBuilder();
            if (this.pretemplate != null) {
                buf.append(this.pretemplate);
            }
            if (name != null) {
                String n = name;
                if (this.baseNamePattern != null) {
                    final Matcher matcher = this.baseNamePattern.matcher(name);
                    if (!matcher.matches() || matcher.groupCount() <= 0) {
                        if (AggRules.getLogger().isDebugEnabled()) {
                            final StringBuilder bf = new StringBuilder(64);
                            bf.append(this.getName());
                            bf.append(".getRegex: for name \"");
                            bf.append(name);
                            bf.append("\" regex is null because basename \"");
                            bf.append(this.basename);
                            bf.append("\" is not matched.");
                            final String msg = bf.toString();
                            AggRules.getLogger().debug(msg);
                        }
                        return null;
                    }
                    n = matcher.group(1);
                }
                buf.append(n);
            }
            if (this.posttemplate != null) {
                buf.append(this.posttemplate);
            }
            final String regex = buf.toString();
            if (AggRules.getLogger().isDebugEnabled()) {
                final StringBuilder bf2 = new StringBuilder(64);
                bf2.append(this.getName());
                bf2.append(".getRegex: for name \"");
                bf2.append(name);
                bf2.append("\" regex is \"");
                bf2.append(regex);
                bf2.append('\"');
                final String msg2 = bf2.toString();
                AggRules.getLogger().debug(msg2);
            }
            return regex;
        }
        
        protected Recognizer.Matcher getMatcher(final String name) {
            final String charcase = this.getCharCase();
            int flag = 0;
            String regex;
            if (charcase.equals("ignore")) {
                regex = this.getRegex(name);
                flag = 2;
            }
            else if (charcase.equals("exact")) {
                regex = this.getRegex(name);
            }
            else if (charcase.equals("upper")) {
                regex = this.getRegex(name.toUpperCase());
            }
            else {
                regex = this.getRegex(name.toLowerCase());
            }
            if (regex == null) {
                return new Recognizer.Matcher() {
                    @Override
                    public boolean matches(final String name) {
                        return false;
                    }
                };
            }
            final Pattern pattern = Pattern.compile(regex, flag);
            return new Recognizer.Matcher() {
                @Override
                public boolean matches(final String name) {
                    final boolean b = pattern.matcher(name).matches();
                    if (AggRules.getLogger().isDebugEnabled()) {
                        this.debug(name);
                    }
                    return b;
                }
                
                private void debug(final String name) {
                    final StringBuilder bf = new StringBuilder(64);
                    bf.append(NameMatcher.this.getName());
                    bf.append(".Matcher.matches:");
                    bf.append(" name \"");
                    bf.append(name);
                    bf.append("\" pattern \"");
                    bf.append(pattern.pattern());
                    bf.append("\"");
                    if ((pattern.flags() & 0x2) != 0x0) {
                        bf.append(" case_insensitive");
                    }
                    final String msg = bf.toString();
                    AggRules.getLogger().debug(msg);
                }
            };
        }
    }
    
    public static class FactCountMatch extends NameMatcher
    {
        public String factCountName;
        
        public FactCountMatch() {
        }
        
        public FactCountMatch(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.factCountName = (String)_parser.getAttribute("factCountName", "String", "fact_count", (String[])null, true);
                this.pretemplate = (String)_parser.getAttribute("pretemplate", "String", (String)null, (String[])null, false);
                this.posttemplate = (String)_parser.getAttribute("posttemplate", "String", (String)null, (String[])null, false);
                this.basename = (String)_parser.getAttribute("basename", "String", (String)null, (String[])null, false);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.charcase = (String)_parser.getAttribute("charcase", "String", "ignore", FactCountMatch._charcase_values, false);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "FactCountMatch";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "factCountName", (Object)this.factCountName, _indent + 1);
            displayAttribute(_out, "pretemplate", (Object)this.pretemplate, _indent + 1);
            displayAttribute(_out, "posttemplate", (Object)this.posttemplate, _indent + 1);
            displayAttribute(_out, "basename", (Object)this.basename, _indent + 1);
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "charcase", (Object)this.charcase, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("FactCountMatch", new XMLAttrVector().add("factCountName", (Object)this.factCountName).add("pretemplate", (Object)this.pretemplate).add("posttemplate", (Object)this.posttemplate).add("basename", (Object)this.basename).add("id", (Object)this.id).add("charcase", (Object)this.charcase).add("enabled", (Object)this.enabled));
            _out.endTag("FactCountMatch");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final FactCountMatch _cother = (FactCountMatch)_other;
            final boolean _diff = displayAttributeDiff("factCountName", (Object)this.factCountName, (Object)_cother.factCountName, _out, _indent + 1);
            return _diff;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                super.validate(rules, msgRecorder);
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        public Recognizer.Matcher getMatcher() {
            return super.getMatcher(this.factCountName);
        }
    }
    
    public static class ForeignKeyMatch extends NameMatcher
    {
        public ForeignKeyMatch() {
        }
        
        public ForeignKeyMatch(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.pretemplate = (String)_parser.getAttribute("pretemplate", "String", (String)null, (String[])null, false);
                this.posttemplate = (String)_parser.getAttribute("posttemplate", "String", (String)null, (String[])null, false);
                this.basename = (String)_parser.getAttribute("basename", "String", (String)null, (String[])null, false);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.charcase = (String)_parser.getAttribute("charcase", "String", "ignore", ForeignKeyMatch._charcase_values, false);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "ForeignKeyMatch";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "pretemplate", (Object)this.pretemplate, _indent + 1);
            displayAttribute(_out, "posttemplate", (Object)this.posttemplate, _indent + 1);
            displayAttribute(_out, "basename", (Object)this.basename, _indent + 1);
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "charcase", (Object)this.charcase, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("ForeignKeyMatch", new XMLAttrVector().add("pretemplate", (Object)this.pretemplate).add("posttemplate", (Object)this.posttemplate).add("basename", (Object)this.basename).add("id", (Object)this.id).add("charcase", (Object)this.charcase).add("enabled", (Object)this.enabled));
            _out.endTag("ForeignKeyMatch");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final ForeignKeyMatch _cother = (ForeignKeyMatch)_other;
            return true;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                super.validate(rules, msgRecorder);
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        public Recognizer.Matcher getMatcher(final String foreignKeyName) {
            return super.getMatcher(foreignKeyName);
        }
    }
    
    public static class TableMatch extends NameMatcher
    {
        public TableMatch() {
        }
        
        public TableMatch(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.pretemplate = (String)_parser.getAttribute("pretemplate", "String", (String)null, (String[])null, false);
                this.posttemplate = (String)_parser.getAttribute("posttemplate", "String", (String)null, (String[])null, false);
                this.basename = (String)_parser.getAttribute("basename", "String", (String)null, (String[])null, false);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.charcase = (String)_parser.getAttribute("charcase", "String", "ignore", TableMatch._charcase_values, false);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "TableMatch";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "pretemplate", (Object)this.pretemplate, _indent + 1);
            displayAttribute(_out, "posttemplate", (Object)this.posttemplate, _indent + 1);
            displayAttribute(_out, "basename", (Object)this.basename, _indent + 1);
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "charcase", (Object)this.charcase, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("TableMatch", new XMLAttrVector().add("pretemplate", (Object)this.pretemplate).add("posttemplate", (Object)this.posttemplate).add("basename", (Object)this.basename).add("id", (Object)this.id).add("charcase", (Object)this.charcase).add("enabled", (Object)this.enabled));
            _out.endTag("TableMatch");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final TableMatch _cother = (TableMatch)_other;
            return true;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                if (this.pretemplate == null && this.posttemplate == null) {
                    final String msg = "Must have at least one template non-null";
                    msgRecorder.reportError(msg);
                }
                super.validate(rules, msgRecorder);
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        public Recognizer.Matcher getMatcher(final String name) {
            return super.getMatcher(name);
        }
    }
    
    public abstract static class Mapper extends CaseMatcher
    {
        public String template;
        public String space;
        public String dot;
        protected static final int BAD_ID = -1;
        protected String[] templateParts;
        protected int[] templateNamePos;
        private static final int MAX_SIZE = 50;
        
        public Mapper() {
        }
        
        public Mapper(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.template = (String)_parser.getAttribute("template", "String", (String)null, (String[])null, true);
                this.space = (String)_parser.getAttribute("space", "String", "_", (String[])null, false);
                this.dot = (String)_parser.getAttribute("dot", "String", "_", (String[])null, false);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.charcase = (String)_parser.getAttribute("charcase", "String", "ignore", Mapper._charcase_values, false);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "Mapper";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "template", (Object)this.template, _indent + 1);
            displayAttribute(_out, "space", (Object)this.space, _indent + 1);
            displayAttribute(_out, "dot", (Object)this.dot, _indent + 1);
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "charcase", (Object)this.charcase, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Mapper", new XMLAttrVector().add("template", (Object)this.template).add("space", (Object)this.space).add("dot", (Object)this.dot).add("id", (Object)this.id).add("charcase", (Object)this.charcase).add("enabled", (Object)this.enabled));
            _out.endTag("Mapper");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Mapper _cother = (Mapper)_other;
            boolean _diff = displayAttributeDiff("template", (Object)this.template, (Object)_cother.template, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("space", (Object)this.space, (Object)_cother.space, _out, _indent + 1));
            _diff = (_diff && displayAttributeDiff("dot", (Object)this.dot, (Object)_cother.dot, _out, _indent + 1));
            return _diff;
        }
        
        public String getTemplate() {
            return this.template;
        }
        
        public String getSpace() {
            return this.space;
        }
        
        public String getDot() {
            return this.dot;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                super.validate(rules, msgRecorder);
                final String[] ss = new String[51];
                final int[] poss = new int[50];
                final String template = this.getTemplate();
                int count = 0;
                int end = 0;
                int previousEnd = 0;
                int start = template.indexOf("${", end);
                while (count < 50) {
                    if (start == -1) {
                        if (count == 0) {
                            final String msg = "Bad template \"" + template + "\", no ${} entries";
                            msgRecorder.reportError(msg);
                            return;
                        }
                        System.arraycopy(poss, 0, this.templateNamePos = new int[count], 0, count);
                        ss[count++] = template.substring(end, template.length());
                        System.arraycopy(ss, 0, this.templateParts = new String[count], 0, count);
                    }
                    else {
                        previousEnd = end;
                        end = template.indexOf(125, start);
                        if (end == -1) {
                            final String msg = "Bad template \"" + template + "\", it had a \"${\", but no '}'";
                            msgRecorder.reportError(msg);
                            return;
                        }
                        final String name = template.substring(start + 2, end);
                        final int pos = this.convertNameToID(name, msgRecorder);
                        if (pos == -1) {
                            return;
                        }
                        poss[count] = pos;
                        ss[count] = template.substring(previousEnd, start);
                        start = template.indexOf("${", end);
                        ++end;
                        ++count;
                    }
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        protected abstract String[] getTemplateNames();
        
        private int convertNameToID(final String name, final MessageRecorder msgRecorder) {
            if (name == null) {
                final String msg = "Template name is null";
                msgRecorder.reportError(msg);
                return -1;
            }
            final String[] names = this.getTemplateNames();
            for (int i = 0; i < names.length; ++i) {
                if (names[i].equals(name)) {
                    return i;
                }
            }
            final String msg2 = "Bad template name \"" + name + "\"";
            msgRecorder.reportError(msg2);
            return -1;
        }
        
        public String getRegex(final String[] names) {
            final String space = this.getSpace();
            final String dot = this.getDot();
            final StringBuilder buf = new StringBuilder();
            buf.append(this.templateParts[0]);
            for (int i = 0; i < this.templateNamePos.length; ++i) {
                String n = names[this.templateNamePos[i]];
                if (space != null) {
                    n = n.replaceAll(" ", space);
                }
                if (dot != null) {
                    n = n.replaceAll("\\.", dot);
                }
                buf.append(n);
                buf.append(this.templateParts[i + 1]);
            }
            final String regex = buf.toString();
            if (AggRules.getLogger().isDebugEnabled()) {
                final StringBuilder bf = new StringBuilder(64);
                bf.append(this.getName());
                bf.append(".getRegex:");
                bf.append(" for names ");
                for (int j = 0; j < names.length; ++j) {
                    bf.append('\"');
                    bf.append(names[j]);
                    bf.append('\"');
                    if (j + 1 < names.length) {
                        bf.append(", ");
                    }
                }
                bf.append(" regex is \"");
                bf.append(regex);
                bf.append('\"');
                final String msg = bf.toString();
                AggRules.getLogger().debug(msg);
            }
            return regex;
        }
        
        protected Recognizer.Matcher getMatcher(final String[] names) {
            final String charcase = this.getCharCase();
            int flag = 0;
            String regex;
            if (charcase.equals("ignore")) {
                regex = this.getRegex(names);
                flag = 2;
            }
            else if (charcase.equals("exact")) {
                regex = this.getRegex(names);
            }
            else if (charcase.equals("upper")) {
                final String[] ucNames = new String[names.length];
                for (int i = 0; i < names.length; ++i) {
                    ucNames[i] = names[i].toUpperCase();
                }
                regex = this.getRegex(ucNames);
            }
            else {
                final String[] lcNames = new String[names.length];
                for (int i = 0; i < names.length; ++i) {
                    lcNames[i] = names[i].toLowerCase();
                }
                regex = this.getRegex(lcNames);
            }
            final Pattern pattern = Pattern.compile(regex, flag);
            return new Recognizer.Matcher() {
                @Override
                public boolean matches(final String name) {
                    final boolean b = pattern.matcher(name).matches();
                    if (AggRules.getLogger().isDebugEnabled()) {
                        this.debug(name);
                    }
                    return b;
                }
                
                private void debug(final String name) {
                    final StringBuilder bf = new StringBuilder(64);
                    bf.append(Mapper.this.getName());
                    bf.append(".Matcher.matches:");
                    bf.append(" name \"");
                    bf.append(name);
                    bf.append("\" pattern \"");
                    bf.append(pattern.pattern());
                    bf.append("\"");
                    if ((pattern.flags() & 0x2) != 0x0) {
                        bf.append(" case_insensitive");
                    }
                    final String msg = bf.toString();
                    AggRules.getLogger().debug(msg);
                }
            };
        }
    }
    
    public static class Regex extends CaseMatcher
    {
        public String space;
        public String dot;
        public String cdata;
        protected static final int BAD_ID = -1;
        protected String[] templateParts;
        protected int[] templateNamePos;
        private static final int MAX_SIZE = 50;
        
        public Regex() {
        }
        
        public Regex(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.space = (String)_parser.getAttribute("space", "String", "_", (String[])null, false);
                this.dot = (String)_parser.getAttribute("dot", "String", "_", (String[])null, false);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.charcase = (String)_parser.getAttribute("charcase", "String", "ignore", Regex._charcase_values, false);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
                this.cdata = _parser.getText();
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "Regex";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "space", (Object)this.space, _indent + 1);
            displayAttribute(_out, "dot", (Object)this.dot, _indent + 1);
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "charcase", (Object)this.charcase, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
            displayString(_out, "cdata", this.cdata, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Regex", new XMLAttrVector().add("space", (Object)this.space).add("dot", (Object)this.dot).add("id", (Object)this.id).add("charcase", (Object)this.charcase).add("enabled", (Object)this.enabled));
            _out.cdata(this.cdata);
            _out.endTag("Regex");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Regex _cother = (Regex)_other;
            boolean _diff = displayAttributeDiff("space", (Object)this.space, (Object)_cother.space, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("dot", (Object)this.dot, (Object)_cother.dot, _out, _indent + 1));
            _diff = (_diff && displayStringDiff("cdata", this.cdata, _cother.cdata, _out, _indent + 1));
            return _diff;
        }
        
        public String getSpace() {
            return this.space;
        }
        
        public String getDot() {
            return this.dot;
        }
        
        public String getTemplate() {
            return this.cdata;
        }
        
        public void validate(final AggRules rules, final String[] templateNames, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                super.validate(rules, msgRecorder);
                final String[] ss = new String[51];
                final int[] poss = new int[50];
                final String template = this.getTemplate();
                int count = 0;
                int end = 0;
                int previousEnd = 0;
                int start = template.indexOf("${", end);
                if (templateNames.length == 0) {
                    if (start == -1) {
                        (this.templateParts = new String[1])[0] = template;
                        this.templateNamePos = new int[0];
                    }
                    else {
                        final String msg = "Bad template \"" + template + "\", no ${} entries but there are template names";
                        msgRecorder.reportError(msg);
                    }
                    return;
                }
                while (count < 50) {
                    if (start == -1) {
                        if (count == 0) {
                            final String msg = "Bad template \"" + template + "\", no ${} entries";
                            msgRecorder.reportError(msg);
                            return;
                        }
                        System.arraycopy(poss, 0, this.templateNamePos = new int[count], 0, count);
                        ss[count++] = template.substring(end, template.length());
                        System.arraycopy(ss, 0, this.templateParts = new String[count], 0, count);
                    }
                    else {
                        previousEnd = end;
                        end = template.indexOf(125, start);
                        if (end == -1) {
                            final String msg = "Bad template \"" + template + "\", it had a \"${\", but no '}'";
                            msgRecorder.reportError(msg);
                            return;
                        }
                        final String name = template.substring(start + 2, end);
                        final int pos = this.convertNameToID(name, templateNames, msgRecorder);
                        if (pos == -1) {
                            return;
                        }
                        poss[count] = pos;
                        ss[count] = template.substring(previousEnd, start);
                        start = template.indexOf("${", end);
                        ++end;
                        ++count;
                    }
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        private int convertNameToID(final String name, final String[] templateNames, final MessageRecorder msgRecorder) {
            if (name == null) {
                final String msg = "Template name is null";
                msgRecorder.reportError(msg);
                return -1;
            }
            for (int i = 0; i < templateNames.length; ++i) {
                if (templateNames[i].equals(name)) {
                    return i;
                }
            }
            final String msg = "Bad template name \"" + name + "\"";
            msgRecorder.reportError(msg);
            return -1;
        }
        
        public String getRegex(final String[] names) {
            final String space = this.getSpace();
            final String dot = this.getDot();
            final StringBuilder buf = new StringBuilder();
            buf.append(this.templateParts[0]);
            for (int i = 0; i < this.templateNamePos.length; ++i) {
                String n = names[this.templateNamePos[i]];
                if (n == null) {
                    return null;
                }
                if (space != null) {
                    n = n.replaceAll(" ", space);
                }
                if (dot != null) {
                    n = n.replaceAll("\\.", dot);
                }
                buf.append(n);
                buf.append(this.templateParts[i + 1]);
            }
            final String regex = buf.toString();
            if (AggRules.getLogger().isDebugEnabled()) {
                final StringBuilder bf = new StringBuilder(64);
                bf.append(this.getName());
                bf.append(".getRegex:");
                bf.append(" for names ");
                for (int j = 0; j < names.length; ++j) {
                    bf.append('\"');
                    bf.append(names[j]);
                    bf.append('\"');
                    if (j + 1 < names.length) {
                        bf.append(", ");
                    }
                }
                bf.append(" regex is \"");
                bf.append(regex);
                bf.append('\"');
                final String msg = bf.toString();
                AggRules.getLogger().debug(msg);
            }
            return regex;
        }
        
        protected Pattern getPattern(final String[] names) {
            final String charcase = this.getCharCase();
            if (charcase.equals("ignore")) {
                final String regex = this.getRegex(names);
                if (regex == null) {
                    return null;
                }
                final Pattern pattern = Pattern.compile(regex, 2);
                return pattern;
            }
            else if (charcase.equals("exact")) {
                final String regex = this.getRegex(names);
                if (regex == null) {
                    return null;
                }
                final Pattern pattern = Pattern.compile(regex);
                return pattern;
            }
            else if (charcase.equals("upper")) {
                final String[] ucNames = new String[names.length];
                for (int i = 0; i < names.length; ++i) {
                    final String name = names[i];
                    ucNames[i] = ((name == null) ? null : name.toUpperCase());
                }
                final String regex2 = this.getRegex(ucNames);
                if (regex2 == null) {
                    return null;
                }
                final Pattern pattern2 = Pattern.compile(regex2);
                return pattern2;
            }
            else {
                final String[] lcNames = new String[names.length];
                for (int i = 0; i < names.length; ++i) {
                    final String name = names[i];
                    lcNames[i] = ((name == null) ? null : name.toLowerCase());
                }
                final String regex2 = this.getRegex(lcNames);
                if (regex2 == null) {
                    return null;
                }
                final Pattern pattern2 = Pattern.compile(regex2);
                return pattern2;
            }
        }
    }
    
    public abstract static class RegexMapper extends Base
    {
        public String id;
        public Regex[] regexs;
        
        public RegexMapper() {
        }
        
        public RegexMapper(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)Regex.class, 0, 0);
                this.regexs = new Regex[_tempArray.length];
                for (int _i = 0; _i < this.regexs.length; ++_i) {
                    this.regexs[_i] = (Regex)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "RegexMapper";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
            displayElementArray(_out, "regexs", (NodeDef[])this.regexs, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("RegexMapper", new XMLAttrVector().add("id", (Object)this.id).add("enabled", (Object)this.enabled));
            displayXMLElementArray(_out, (NodeDef[])this.regexs);
            _out.endTag("RegexMapper");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final RegexMapper _cother = (RegexMapper)_other;
            boolean _diff = displayAttributeDiff("id", (Object)this.id, (Object)_cother.id, _out, _indent + 1);
            _diff = (_diff && displayElementArrayDiff("regexs", (NodeDef[])this.regexs, (NodeDef[])_cother.regexs, _out, _indent + 1));
            return _diff;
        }
        
        @Override
        protected String getTag() {
            return this.id;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                final String[] templateNames = this.getTemplateNames();
                for (int i = 0; i < this.regexs.length; ++i) {
                    final Regex regex = this.regexs[i];
                    regex.validate(rules, templateNames, msgRecorder);
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        protected abstract String[] getTemplateNames();
        
        protected Recognizer.Matcher getMatcher(final String[] names) {
            final Pattern[] patterns = new Pattern[this.regexs.length];
            for (int i = 0; i < this.regexs.length; ++i) {
                final Regex regex = this.regexs[i];
                patterns[i] = regex.getPattern(names);
            }
            return new Recognizer.Matcher() {
                @Override
                public boolean matches(final String name) {
                    for (int i = 0; i < patterns.length; ++i) {
                        final Pattern pattern = patterns[i];
                        if (pattern != null && pattern.matcher(name).matches()) {
                            if (AggRules.getLogger().isDebugEnabled()) {
                                this.debug(name, pattern);
                            }
                            return true;
                        }
                    }
                    return false;
                }
                
                private void debug(final String name, final Pattern p) {
                    final StringBuilder bf = new StringBuilder(64);
                    bf.append("DefaultDef.RegexMapper");
                    bf.append(".Matcher.matches:");
                    bf.append(" name \"");
                    bf.append(name);
                    bf.append("\" matches regex \"");
                    bf.append(p.pattern());
                    bf.append("\"");
                    if ((p.flags() & 0x2) != 0x0) {
                        bf.append(" case_insensitive");
                    }
                    final String msg = bf.toString();
                    AggRules.getLogger().debug(msg);
                }
            };
        }
    }
    
    public abstract static class Ref extends Base
    {
        public String refId;
        
        public Ref() {
        }
        
        public Ref(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.refId = (String)_parser.getAttribute("refId", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "Ref";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "refId", (Object)this.refId, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("Ref", new XMLAttrVector().add("refId", (Object)this.refId).add("enabled", (Object)this.enabled));
            _out.endTag("Ref");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final Ref _cother = (Ref)_other;
            final boolean _diff = displayAttributeDiff("refId", (Object)this.refId, (Object)_cother.refId, _out, _indent + 1);
            return _diff;
        }
        
        @Override
        protected String getTag() {
            return this.getRefId();
        }
        
        public String getRefId() {
            return this.refId;
        }
    }
    
    public static class LevelMapRef extends Ref
    {
        public LevelMapRef() {
        }
        
        public LevelMapRef(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.refId = (String)_parser.getAttribute("refId", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "LevelMapRef";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "refId", (Object)this.refId, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("LevelMapRef", new XMLAttrVector().add("refId", (Object)this.refId).add("enabled", (Object)this.enabled));
            _out.endTag("LevelMapRef");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final LevelMapRef _cother = (LevelMapRef)_other;
            return true;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                if (!rules.hasLevelMap(this.getRefId())) {
                    final String msg = "No LevelMap has id equal to refid \"" + this.getRefId() + "\"";
                    msgRecorder.reportError(msg);
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
    }
    
    public static class MeasureMapRef extends Ref
    {
        public MeasureMapRef() {
        }
        
        public MeasureMapRef(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.refId = (String)_parser.getAttribute("refId", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "MeasureMapRef";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "refId", (Object)this.refId, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("MeasureMapRef", new XMLAttrVector().add("refId", (Object)this.refId).add("enabled", (Object)this.enabled));
            _out.endTag("MeasureMapRef");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final MeasureMapRef _cother = (MeasureMapRef)_other;
            return true;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                if (!rules.hasMeasureMap(this.getRefId())) {
                    final String msg = "No MeasureMap has id equal to refid \"" + this.getRefId() + "\"";
                    msgRecorder.reportError(msg);
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
    }
    
    public static class IgnoreMapRef extends Ref
    {
        public IgnoreMapRef() {
        }
        
        public IgnoreMapRef(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.refId = (String)_parser.getAttribute("refId", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "IgnoreMapRef";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "refId", (Object)this.refId, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("IgnoreMapRef", new XMLAttrVector().add("refId", (Object)this.refId).add("enabled", (Object)this.enabled));
            _out.endTag("IgnoreMapRef");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final IgnoreMapRef _cother = (IgnoreMapRef)_other;
            return true;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                if (!rules.hasIgnoreMap(this.getRefId())) {
                    final String msg = "No IgnoreMap has id equal to refid \"" + this.getRefId() + "\"";
                    msgRecorder.reportError(msg);
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
    }
    
    public static class FactCountMatchRef extends Ref
    {
        public FactCountMatchRef() {
        }
        
        public FactCountMatchRef(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.refId = (String)_parser.getAttribute("refId", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "FactCountMatchRef";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "refId", (Object)this.refId, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("FactCountMatchRef", new XMLAttrVector().add("refId", (Object)this.refId).add("enabled", (Object)this.enabled));
            _out.endTag("FactCountMatchRef");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final FactCountMatchRef _cother = (FactCountMatchRef)_other;
            return true;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                if (!rules.hasFactCountMatch(this.getRefId())) {
                    final String msg = "No FactCountMatch has id equal to refid \"" + this.getRefId() + "\"";
                    msgRecorder.reportError(msg);
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
    }
    
    public static class ForeignKeyMatchRef extends Ref
    {
        public ForeignKeyMatchRef() {
        }
        
        public ForeignKeyMatchRef(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.refId = (String)_parser.getAttribute("refId", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "ForeignKeyMatchRef";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "refId", (Object)this.refId, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("ForeignKeyMatchRef", new XMLAttrVector().add("refId", (Object)this.refId).add("enabled", (Object)this.enabled));
            _out.endTag("ForeignKeyMatchRef");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final ForeignKeyMatchRef _cother = (ForeignKeyMatchRef)_other;
            return true;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                if (!rules.hasForeignKeyMatch(this.getRefId())) {
                    final String msg = "No ForeignKeyMatch has id equal to refid \"" + this.getRefId() + "\"";
                    msgRecorder.reportError(msg);
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
    }
    
    public static class TableMatchRef extends Ref
    {
        public TableMatchRef() {
        }
        
        public TableMatchRef(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.refId = (String)_parser.getAttribute("refId", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "TableMatchRef";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "refId", (Object)this.refId, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("TableMatchRef", new XMLAttrVector().add("refId", (Object)this.refId).add("enabled", (Object)this.enabled));
            _out.endTag("TableMatchRef");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final TableMatchRef _cother = (TableMatchRef)_other;
            return true;
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                if (!rules.hasTableMatch(this.getRefId())) {
                    final String msg = "No TableMatch has id equal to refid \"" + this.getRefId() + "\"";
                    msgRecorder.reportError(msg);
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
    }
    
    public static class LevelMap extends RegexMapper
    {
        private static final String[] TEMPLATE_NAMES;
        
        public LevelMap() {
        }
        
        public LevelMap(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)Regex.class, 0, 0);
                this.regexs = new Regex[_tempArray.length];
                for (int _i = 0; _i < this.regexs.length; ++_i) {
                    this.regexs[_i] = (Regex)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "LevelMap";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
            displayElementArray(_out, "regexs", (NodeDef[])this.regexs, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("LevelMap", new XMLAttrVector().add("id", (Object)this.id).add("enabled", (Object)this.enabled));
            displayXMLElementArray(_out, (NodeDef[])this.regexs);
            _out.endTag("LevelMap");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final LevelMap _cother = (LevelMap)_other;
            final boolean _diff = displayElementArrayDiff("regexs", (NodeDef[])this.regexs, (NodeDef[])_cother.regexs, _out, _indent + 1);
            return _diff;
        }
        
        @Override
        protected String[] getTemplateNames() {
            return LevelMap.TEMPLATE_NAMES;
        }
        
        protected Recognizer.Matcher getMatcher(final String usagePrefix, final String hierarchyName, final String levelName, final String levelColumnName) {
            return this.getMatcher(new String[] { usagePrefix, hierarchyName, levelName, levelColumnName });
        }
        
        static {
            TEMPLATE_NAMES = new String[] { "usage_prefix", "hierarchy_name", "level_name", "level_column_name" };
        }
    }
    
    public static class MeasureMap extends RegexMapper
    {
        private static final String[] TEMPLATE_NAMES;
        
        public MeasureMap() {
        }
        
        public MeasureMap(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)Regex.class, 0, 0);
                this.regexs = new Regex[_tempArray.length];
                for (int _i = 0; _i < this.regexs.length; ++_i) {
                    this.regexs[_i] = (Regex)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "MeasureMap";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
            displayElementArray(_out, "regexs", (NodeDef[])this.regexs, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("MeasureMap", new XMLAttrVector().add("id", (Object)this.id).add("enabled", (Object)this.enabled));
            displayXMLElementArray(_out, (NodeDef[])this.regexs);
            _out.endTag("MeasureMap");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final MeasureMap _cother = (MeasureMap)_other;
            final boolean _diff = displayElementArrayDiff("regexs", (NodeDef[])this.regexs, (NodeDef[])_cother.regexs, _out, _indent + 1);
            return _diff;
        }
        
        @Override
        protected String[] getTemplateNames() {
            return MeasureMap.TEMPLATE_NAMES;
        }
        
        protected Recognizer.Matcher getMatcher(final String measureName, final String measuerColumnName, final String aggregateName) {
            return this.getMatcher(new String[] { measureName, measuerColumnName, aggregateName });
        }
        
        static {
            TEMPLATE_NAMES = new String[] { "measure_name", "measure_column_name", "aggregate_name" };
        }
    }
    
    public static class IgnoreMap extends RegexMapper
    {
        private static final String[] TEMPLATE_NAMES;
        
        public IgnoreMap() {
        }
        
        public IgnoreMap(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.id = (String)_parser.getAttribute("id", "String", (String)null, (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
                final NodeDef[] _tempArray = _parser.getArray((Class)Regex.class, 0, 0);
                this.regexs = new Regex[_tempArray.length];
                for (int _i = 0; _i < this.regexs.length; ++_i) {
                    this.regexs[_i] = (Regex)_tempArray[_i];
                }
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "IgnoreMap";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "id", (Object)this.id, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
            displayElementArray(_out, "regexs", (NodeDef[])this.regexs, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("IgnoreMap", new XMLAttrVector().add("id", (Object)this.id).add("enabled", (Object)this.enabled));
            displayXMLElementArray(_out, (NodeDef[])this.regexs);
            _out.endTag("IgnoreMap");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final IgnoreMap _cother = (IgnoreMap)_other;
            final boolean _diff = displayElementArrayDiff("regexs", (NodeDef[])this.regexs, (NodeDef[])_cother.regexs, _out, _indent + 1);
            return _diff;
        }
        
        @Override
        protected String[] getTemplateNames() {
            return IgnoreMap.TEMPLATE_NAMES;
        }
        
        protected Recognizer.Matcher getMatcher() {
            return this.getMatcher(new String[0]);
        }
        
        static {
            TEMPLATE_NAMES = new String[0];
        }
    }
    
    public static class AggRule extends Base
    {
        public String tag;
        public String countColumn;
        public IgnoreMap ignoreMap;
        public IgnoreMapRef ignoreMapRef;
        public FactCountMatch factCountMatch;
        public FactCountMatchRef factCountMatchRef;
        public ForeignKeyMatch foreignKeyMatch;
        public ForeignKeyMatchRef foreignKeyMatchRef;
        public TableMatch tableMatch;
        public TableMatchRef tableMatchRef;
        public LevelMap levelMap;
        public LevelMapRef levelMapRef;
        public MeasureMap measureMap;
        public MeasureMapRef measureMapRef;
        
        public AggRule() {
        }
        
        public AggRule(final DOMWrapper _def) throws XOMException {
            try {
                final DOMElementParser _parser = new DOMElementParser(_def, "", (Class)DefaultDef.class);
                this.tag = (String)_parser.getAttribute("tag", "String", (String)null, (String[])null, true);
                this.countColumn = (String)_parser.getAttribute("countColumn", "String", "fact_count", (String[])null, true);
                this.enabled = (Boolean)_parser.getAttribute("enabled", "Boolean", "true", (String[])null, false);
                this.ignoreMap = (IgnoreMap)_parser.getElement((Class)IgnoreMap.class, false);
                this.ignoreMapRef = (IgnoreMapRef)_parser.getElement((Class)IgnoreMapRef.class, false);
                this.factCountMatch = (FactCountMatch)_parser.getElement((Class)FactCountMatch.class, false);
                this.factCountMatchRef = (FactCountMatchRef)_parser.getElement((Class)FactCountMatchRef.class, false);
                this.foreignKeyMatch = (ForeignKeyMatch)_parser.getElement((Class)ForeignKeyMatch.class, false);
                this.foreignKeyMatchRef = (ForeignKeyMatchRef)_parser.getElement((Class)ForeignKeyMatchRef.class, false);
                this.tableMatch = (TableMatch)_parser.getElement((Class)TableMatch.class, false);
                this.tableMatchRef = (TableMatchRef)_parser.getElement((Class)TableMatchRef.class, false);
                this.levelMap = (LevelMap)_parser.getElement((Class)LevelMap.class, false);
                this.levelMapRef = (LevelMapRef)_parser.getElement((Class)LevelMapRef.class, false);
                this.measureMap = (MeasureMap)_parser.getElement((Class)MeasureMap.class, false);
                this.measureMapRef = (MeasureMapRef)_parser.getElement((Class)MeasureMapRef.class, false);
            }
            catch (XOMException _ex) {
                throw new XOMException("In " + this.getName() + ": " + _ex.getMessage());
            }
        }
        
        @Override
        public String getName() {
            return "AggRule";
        }
        
        @Override
        public void display(final PrintWriter _out, final int _indent) {
            _out.println(this.getName());
            displayAttribute(_out, "tag", (Object)this.tag, _indent + 1);
            displayAttribute(_out, "countColumn", (Object)this.countColumn, _indent + 1);
            displayAttribute(_out, "enabled", (Object)this.enabled, _indent + 1);
            displayElement(_out, "ignoreMap", (ElementDef)this.ignoreMap, _indent + 1);
            displayElement(_out, "ignoreMapRef", (ElementDef)this.ignoreMapRef, _indent + 1);
            displayElement(_out, "factCountMatch", (ElementDef)this.factCountMatch, _indent + 1);
            displayElement(_out, "factCountMatchRef", (ElementDef)this.factCountMatchRef, _indent + 1);
            displayElement(_out, "foreignKeyMatch", (ElementDef)this.foreignKeyMatch, _indent + 1);
            displayElement(_out, "foreignKeyMatchRef", (ElementDef)this.foreignKeyMatchRef, _indent + 1);
            displayElement(_out, "tableMatch", (ElementDef)this.tableMatch, _indent + 1);
            displayElement(_out, "tableMatchRef", (ElementDef)this.tableMatchRef, _indent + 1);
            displayElement(_out, "levelMap", (ElementDef)this.levelMap, _indent + 1);
            displayElement(_out, "levelMapRef", (ElementDef)this.levelMapRef, _indent + 1);
            displayElement(_out, "measureMap", (ElementDef)this.measureMap, _indent + 1);
            displayElement(_out, "measureMapRef", (ElementDef)this.measureMapRef, _indent + 1);
        }
        
        @Override
        public void displayXML(final XMLOutput _out, final int _indent) {
            _out.beginTag("AggRule", new XMLAttrVector().add("tag", (Object)this.tag).add("countColumn", (Object)this.countColumn).add("enabled", (Object)this.enabled));
            displayXMLElement(_out, (ElementDef)this.ignoreMap);
            displayXMLElement(_out, (ElementDef)this.ignoreMapRef);
            displayXMLElement(_out, (ElementDef)this.factCountMatch);
            displayXMLElement(_out, (ElementDef)this.factCountMatchRef);
            displayXMLElement(_out, (ElementDef)this.foreignKeyMatch);
            displayXMLElement(_out, (ElementDef)this.foreignKeyMatchRef);
            displayXMLElement(_out, (ElementDef)this.tableMatch);
            displayXMLElement(_out, (ElementDef)this.tableMatchRef);
            displayXMLElement(_out, (ElementDef)this.levelMap);
            displayXMLElement(_out, (ElementDef)this.levelMapRef);
            displayXMLElement(_out, (ElementDef)this.measureMap);
            displayXMLElement(_out, (ElementDef)this.measureMapRef);
            _out.endTag("AggRule");
        }
        
        @Override
        public boolean displayDiff(final ElementDef _other, final PrintWriter _out, final int _indent) {
            final AggRule _cother = (AggRule)_other;
            boolean _diff = displayAttributeDiff("tag", (Object)this.tag, (Object)_cother.tag, _out, _indent + 1);
            _diff = (_diff && displayAttributeDiff("countColumn", (Object)this.countColumn, (Object)_cother.countColumn, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("ignoreMap", (NodeDef)this.ignoreMap, (NodeDef)_cother.ignoreMap, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("ignoreMapRef", (NodeDef)this.ignoreMapRef, (NodeDef)_cother.ignoreMapRef, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("factCountMatch", (NodeDef)this.factCountMatch, (NodeDef)_cother.factCountMatch, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("factCountMatchRef", (NodeDef)this.factCountMatchRef, (NodeDef)_cother.factCountMatchRef, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("foreignKeyMatch", (NodeDef)this.foreignKeyMatch, (NodeDef)_cother.foreignKeyMatch, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("foreignKeyMatchRef", (NodeDef)this.foreignKeyMatchRef, (NodeDef)_cother.foreignKeyMatchRef, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("tableMatch", (NodeDef)this.tableMatch, (NodeDef)_cother.tableMatch, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("tableMatchRef", (NodeDef)this.tableMatchRef, (NodeDef)_cother.tableMatchRef, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("levelMap", (NodeDef)this.levelMap, (NodeDef)_cother.levelMap, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("levelMapRef", (NodeDef)this.levelMapRef, (NodeDef)_cother.levelMapRef, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("measureMap", (NodeDef)this.measureMap, (NodeDef)_cother.measureMap, _out, _indent + 1));
            _diff = (_diff && displayElementDiff("measureMapRef", (NodeDef)this.measureMapRef, (NodeDef)_cother.measureMapRef, _out, _indent + 1));
            return _diff;
        }
        
        private boolean isOk(final Base base) {
            return base != null && base.isEnabled();
        }
        
        private boolean isRef(final AggRules rules, final MessageRecorder msgRecorder, final Base base, final Base baseRef, final String baseName) {
            if (!this.isOk(base)) {
                if (this.isOk(baseRef)) {
                    baseRef.validate(rules, msgRecorder);
                    return true;
                }
                final String msg = "Neither base " + baseName + " or baseref " + baseName + "Ref is ok";
                msgRecorder.reportError(msg);
                return false;
            }
            else {
                if (this.isOk(baseRef)) {
                    final String msg = "Both base " + base.getName() + " and baseref " + baseRef.getName() + " are ok";
                    msgRecorder.reportError(msg);
                    return false;
                }
                base.validate(rules, msgRecorder);
                return false;
            }
        }
        
        @Override
        public void validate(final AggRules rules, final MessageRecorder msgRecorder) {
            msgRecorder.pushContextName(this.getName());
            try {
                if (this.ignoreMap != null) {
                    this.ignoreMap.validate(rules, msgRecorder);
                }
                else if (this.ignoreMapRef != null) {
                    this.ignoreMapRef.validate(rules, msgRecorder);
                    this.ignoreMap = rules.lookupIgnoreMap(this.ignoreMapRef.getRefId());
                }
                if (this.isRef(rules, msgRecorder, this.factCountMatch, this.factCountMatchRef, "FactCountMatch")) {
                    this.factCountMatch = rules.lookupFactCountMatch(this.factCountMatchRef.getRefId());
                }
                if (this.isRef(rules, msgRecorder, this.foreignKeyMatch, this.foreignKeyMatchRef, "ForeignKeyMatch")) {
                    this.foreignKeyMatch = rules.lookupForeignKeyMatch(this.foreignKeyMatchRef.getRefId());
                }
                if (this.isRef(rules, msgRecorder, this.tableMatch, this.tableMatchRef, "TableMatch")) {
                    this.tableMatch = rules.lookupTableMatch(this.tableMatchRef.getRefId());
                }
                if (this.isRef(rules, msgRecorder, this.levelMap, this.levelMapRef, "LevelMap")) {
                    this.levelMap = rules.lookupLevelMap(this.levelMapRef.getRefId());
                }
                if (this.isRef(rules, msgRecorder, this.measureMap, this.measureMapRef, "MeasureMap")) {
                    this.measureMap = rules.lookupMeasureMap(this.measureMapRef.getRefId());
                }
            }
            finally {
                msgRecorder.popContextName();
            }
        }
        
        public String getTag() {
            return this.tag;
        }
        
        public String getCountColumn() {
            return this.countColumn;
        }
        
        public FactCountMatch getFactCountMatch() {
            return this.factCountMatch;
        }
        
        public ForeignKeyMatch getForeignKeyMatch() {
            return this.foreignKeyMatch;
        }
        
        public TableMatch getTableMatch() {
            return this.tableMatch;
        }
        
        public LevelMap getLevelMap() {
            return this.levelMap;
        }
        
        public MeasureMap getMeasureMap() {
            return this.measureMap;
        }
        
        public IgnoreMap getIgnoreMap() {
            return this.ignoreMap;
        }
    }
}
