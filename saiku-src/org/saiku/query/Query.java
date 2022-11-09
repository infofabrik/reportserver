// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import org.saiku.query.util.QueryUtil;
import org.olap4j.OlapStatement;
import org.olap4j.CellSet;
import mondrian.olap.Annotation;
import mondrian.olap4j.MondrianOlap4jLevel;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.saiku.query.metadata.CalculatedMember;
import org.olap4j.metadata.Property;
import java.util.Collection;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.olap4j.metadata.Level;
import java.io.Writer;
import org.olap4j.mdx.ParseTreeWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.olap4j.OlapException;
import org.olap4j.mdx.SelectNode;
import java.sql.SQLException;
import java.util.Iterator;
import org.olap4j.metadata.Catalog;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.impl.NamedListImpl;
import java.util.HashMap;
import java.util.List;
import org.olap4j.OlapConnection;
import org.saiku.query.metadata.CalculatedMeasure;
import org.olap4j.metadata.NamedList;
import org.olap4j.metadata.Cube;
import org.olap4j.Axis;
import java.util.Map;

public class Query
{
    protected final String name;
    protected Map<Axis, QueryAxis> axes;
    protected QueryAxis across;
    protected QueryAxis down;
    protected QueryAxis filter;
    protected QueryAxis unused;
    protected final Cube cube;
    protected Map<String, QueryHierarchy> hierarchyMap;
    protected NamedList<CalculatedMeasure> calculatedMeasures;
    protected QueryDetails details;
    protected boolean selectDefaultMembers;
    private final OlapConnection connection;
    private ISortableQuerySet.HierarchizeMode defaultHierarchizeMode;
    private boolean visualTotals;
    private String visualTotalsPattern;
    private boolean lowestLevelsOnly;
    private Map<String, String> parameters;
    private Map<String, List<String>> aggregators;
    
    public Query(final String name, final Cube cube) throws SQLException {
        this.axes = new HashMap<Axis, QueryAxis>();
        this.hierarchyMap = new HashMap<String, QueryHierarchy>();
        this.calculatedMeasures = (NamedList<CalculatedMeasure>)new NamedListImpl();
        this.selectDefaultMembers = true;
        this.defaultHierarchizeMode = ISortableQuerySet.HierarchizeMode.PRE;
        this.visualTotals = false;
        this.lowestLevelsOnly = false;
        this.parameters = new HashMap<String, String>();
        this.aggregators = new HashMap<String, List<String>>();
        this.name = name;
        this.cube = cube;
        final Catalog catalog = cube.getSchema().getCatalog();
        (this.connection = (OlapConnection)catalog.getMetaData().getConnection().unwrap((Class)OlapConnection.class)).setCatalog(catalog.getName());
        this.unused = new QueryAxis(this, null);
        for (final Hierarchy hierarchy : cube.getHierarchies()) {
            final QueryHierarchy queryHierarchy = new QueryHierarchy(this, hierarchy);
            this.unused.getQueryHierarchies().add(queryHierarchy);
            this.hierarchyMap.put(queryHierarchy.getUniqueName(), queryHierarchy);
        }
        this.across = new QueryAxis(this, (Axis)Axis.COLUMNS);
        this.down = new QueryAxis(this, (Axis)Axis.ROWS);
        this.filter = new QueryAxis(this, (Axis)Axis.FILTER);
        this.axes.put(null, this.unused);
        this.axes.put((Axis)Axis.COLUMNS, this.across);
        this.axes.put((Axis)Axis.ROWS, this.down);
        this.axes.put((Axis)Axis.FILTER, this.filter);
        this.details = new QueryDetails(this, (Axis)Axis.COLUMNS);
    }
    
    public SelectNode getSelect() throws OlapException {
        try {
            return Olap4jNodeConverter.toQuery(this);
        }
        catch (Exception e) {
            throw new OlapException("Error creating Select", (Throwable)e);
        }
    }
    
    public String getMdx() throws OlapException {
        final Writer writer = new StringWriter();
        this.getSelect().unparse(new ParseTreeWriter(new PrintWriter(writer)));
        return writer.toString();
    }
    
    public Cube getCube() {
        return this.cube;
    }
    
    public OlapConnection getConnection() {
        return this.connection;
    }
    
    public Catalog getCatalog() {
        return this.cube.getSchema().getCatalog();
    }
    
    public QueryHierarchy getHierarchy(final String name) {
        if (this.hierarchyMap.containsKey(name)) {
            return this.hierarchyMap.get(name);
        }
        for (final QueryHierarchy qh : this.hierarchyMap.values()) {
            if (qh.getName().equals(name)) {
                return qh;
            }
        }
        return null;
    }
    
    public QueryHierarchy getHierarchy(final Hierarchy hierarchy) {
        if (hierarchy == null) {
            return null;
        }
        return this.hierarchyMap.get(hierarchy.getUniqueName());
    }
    
    public QueryLevel getLevel(final Hierarchy hierarchy, final String name) {
        final QueryHierarchy h = this.hierarchyMap.get(hierarchy.getUniqueName());
        return h.getActiveLevel(name);
    }
    
    public QueryLevel getLevel(final Level level) {
        return this.getLevel(level.getHierarchy(), level.getName());
    }
    
    public QueryLevel getLevel(final String uniqueLevelName) {
        if (StringUtils.isNotBlank(uniqueLevelName)) {
            for (final QueryHierarchy qh : this.hierarchyMap.values()) {
                for (final QueryLevel ql : qh.getActiveQueryLevels()) {
                    if (ql.getUniqueName().equals(uniqueLevelName)) {
                        return ql;
                    }
                }
            }
        }
        return null;
    }
    
    public void swapAxes() {
        if (this.axes.size() != 4) {
            throw new IllegalArgumentException();
        }
        final List<QueryHierarchy> tmpAcross = new ArrayList<QueryHierarchy>();
        tmpAcross.addAll(this.across.getQueryHierarchies());
        final List<QueryHierarchy> tmpDown = new ArrayList<QueryHierarchy>();
        tmpDown.addAll(this.down.getQueryHierarchies());
        this.across.getQueryHierarchies().clear();
        final Map<Integer, QueryHierarchy> acrossChildList = new HashMap<Integer, QueryHierarchy>();
        for (int cpt = 0; cpt < tmpAcross.size(); ++cpt) {
            acrossChildList.put(cpt, tmpAcross.get(cpt));
        }
        this.down.getQueryHierarchies().clear();
        final Map<Integer, QueryHierarchy> downChildList = new HashMap<Integer, QueryHierarchy>();
        for (int cpt2 = 0; cpt2 < tmpDown.size(); ++cpt2) {
            downChildList.put(cpt2, tmpDown.get(cpt2));
        }
        this.across.getQueryHierarchies().addAll(tmpDown);
        this.down.getQueryHierarchies().addAll(tmpAcross);
    }
    
    public QueryAxis getAxis(final Axis axis) {
        return this.axes.get(axis);
    }
    
    public Map<Axis, QueryAxis> getAxes() {
        return this.axes;
    }
    
    public CalculatedMember createCalculatedMember(final QueryHierarchy hierarchy, final String name, final String formula, final Map<Property, Object> properties, final boolean mondrian3) {
        final Hierarchy h = hierarchy.getHierarchy();
        final CalculatedMember cm = new CalculatedMember(h.getDimension(), h, name, name, null, Member.Type.FORMULA, formula, null, mondrian3);
        this.addCalculatedMember(hierarchy, cm);
        return cm;
    }
    
    public CalculatedMember createCalculatedMember(final QueryHierarchy hierarchy, final Member parentMember, final String name, final String formula, final Map<Property, Object> properties, final boolean mondrian3) {
        final Hierarchy h = hierarchy.getHierarchy();
        final CalculatedMember cm = new CalculatedMember(h.getDimension(), h, name, name, parentMember, Member.Type.FORMULA, formula, null, mondrian3);
        this.addCalculatedMember(hierarchy, cm);
        return cm;
    }
    
    public void addCalculatedMember(final QueryHierarchy hierarchy, final CalculatedMember cm) {
        hierarchy.addCalculatedMember(cm);
    }
    
    public NamedList<CalculatedMember> getCalculatedMembers(final QueryHierarchy hierarchy) {
        return hierarchy.getCalculatedMembers();
    }
    
    public NamedList<CalculatedMember> getCalculatedMembers() {
        final NamedList<CalculatedMember> cm = (NamedList<CalculatedMember>)new NamedListImpl();
        for (final QueryHierarchy h : this.hierarchyMap.values()) {
            cm.addAll((Collection)h.getCalculatedMembers());
        }
        return cm;
    }
    
    public CalculatedMeasure createCalculatedMeasure(final String name, final String formula, final Map<Property, Object> properties) {
        if (this.cube.getMeasures().size() > 0) {
            final Measure first = this.cube.getMeasures().get(0);
            return this.createCalculatedMeasure(first.getHierarchy(), name, formula, properties);
        }
        throw new RuntimeException("There has to be at least one valid measure in the cube to create a calculated measure!");
    }
    
    public CalculatedMeasure createCalculatedMeasure(final Hierarchy measureHierarchy, final String name, final String formula, final Map<Property, Object> properties) {
        final CalculatedMeasure cm = new CalculatedMeasure(measureHierarchy, name, name, formula, null);
        this.addCalculatedMeasure(cm);
        return cm;
    }
    
    public void addCalculatedMeasure(final CalculatedMeasure cm) {
        this.calculatedMeasures.add((CalculatedMeasure)cm);
    }
    
    public NamedList<CalculatedMeasure> getCalculatedMeasures() {
        return this.calculatedMeasures;
    }
    
    public CalculatedMeasure getCalculatedMeasure(final String name) {
        return (CalculatedMeasure)this.calculatedMeasures.get(name);
    }
    
    public QueryHierarchy createTimeLag(final QueryHierarchy hierarchy, final MondrianOlap4jLevel level, final int amount) {
        final Map<String, Annotation> a = (Map<String, Annotation>)level.getAnnotations();
        if (a.containsKey("AnalyzerDateFormat")) {
            final Annotation v = a.get("AnalyzerDateFormat");
            hierarchy.setMdxSetExpression("(CurrentDateMember(" + hierarchy.getUniqueName() + ", \"" + hierarchy.getUniqueName() + "\\." + v.getValue() + "\").Lag(" + String.valueOf(amount) + ") : CurrentDateMember(" + hierarchy.getUniqueName() + ",\"" + hierarchy.getUniqueName() + "\\." + v.getValue() + "\"))");
        }
        else {
            System.out.println("Can't do this");
        }
        return hierarchy;
    }
    
    public Measure getMeasure(final String name) {
        for (final Measure m : this.cube.getMeasures()) {
            if (name != null && name.equals(m.getName())) {
                return m;
            }
            if (name != null && m.getUniqueName().equals(name)) {
                return m;
            }
        }
        return null;
    }
    
    public QueryDetails getDetails() {
        return this.details;
    }
    
    public QueryAxis getUnusedAxis() {
        return this.unused;
    }
    
    public CellSet execute() throws OlapException {
        final SelectNode mdx = this.getSelect();
        final Catalog catalog = this.getCatalog();
        try {
            this.connection.setCatalog(catalog.getName());
        }
        catch (SQLException e) {
            throw new OlapException("Error while executing query", (Throwable)e);
        }
        final OlapStatement olapStatement = this.connection.createStatement();
        return olapStatement.executeOlapQuery(mdx);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void moveHierarchy(final QueryHierarchy hierarchy, final Axis axis) {
        this.moveHierarchy(hierarchy, axis, -1);
    }
    
    public void moveHierarchy(final QueryHierarchy hierarchy, final Axis axis, final int position) {
        final QueryAxis oldQueryAxis = this.findAxis(hierarchy);
        final QueryAxis newQueryAxis = this.getAxis(axis);
        if (oldQueryAxis != null && newQueryAxis != null && (position > -1 || oldQueryAxis.getLocation() != newQueryAxis.getLocation())) {
            if (oldQueryAxis.getLocation() != null) {
                oldQueryAxis.removeHierarchy(hierarchy);
            }
            if (newQueryAxis.getLocation() != null) {
                if (position > -1) {
                    newQueryAxis.addHierarchy(position, hierarchy);
                }
                else {
                    newQueryAxis.addHierarchy(hierarchy);
                }
            }
        }
    }
    
    private QueryAxis findAxis(final QueryHierarchy hierarchy) {
        if (this.getUnusedAxis().getQueryHierarchies().contains(hierarchy)) {
            return this.getUnusedAxis();
        }
        final Map<Axis, QueryAxis> axes = this.getAxes();
        for (final Axis axis : axes.keySet()) {
            if (axes.get(axis).getQueryHierarchies().contains(hierarchy)) {
                return axes.get(axis);
            }
        }
        return null;
    }
    
    public void setSelectDefaultMembers(final boolean selectDefaultMembers) {
        this.selectDefaultMembers = selectDefaultMembers;
    }
    
    public void setDefaultHierarchizeMode(final ISortableQuerySet.HierarchizeMode mode) {
        this.defaultHierarchizeMode = mode;
    }
    
    public ISortableQuerySet.HierarchizeMode getDefaultHierarchizeMode() {
        return this.defaultHierarchizeMode;
    }
    
    public void setVisualTotals(final boolean visualTotals) {
        if (!visualTotals) {
            this.visualTotalsPattern = null;
        }
        this.visualTotals = visualTotals;
    }
    
    public boolean isVisualTotals() {
        return this.visualTotals;
    }
    
    public void setVisualTotalsPattern(final String pattern) {
        this.visualTotalsPattern = pattern;
    }
    
    public String getVisualTotalsPattern() {
        return this.visualTotalsPattern;
    }
    
    public void setLowestLevelsOnly(final boolean lowest) {
        this.lowestLevelsOnly = lowest;
    }
    
    public boolean isLowestLevelsOnly() {
        return this.lowestLevelsOnly;
    }
    
    public Map<String, String> getParameters() {
        return this.parameters;
    }
    
    public void retrieveParameters() {
        for (final QueryAxis qa : this.getAxes().values()) {
            for (final QueryHierarchy qh : qa.getQueryHierarchies()) {
                for (final QueryLevel ql : qh.getActiveQueryLevels()) {
                    final String pName = ql.getParameterName();
                    if (StringUtils.isNotBlank(pName)) {
                        this.addOrSetParameter(pName);
                    }
                    final List<String> params = QueryUtil.retrieveSetParameters(ql);
                    this.addOrSetParameters(params);
                }
                final List<String> hparams = QueryUtil.retrieveSortableSetParameters(qh);
                this.addOrSetParameters(hparams);
            }
            final List<String> qparams = QueryUtil.retrieveSortableSetParameters(qa);
            this.addOrSetParameters(qparams);
        }
    }
    
    public void setParameters(final Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
    public void setParameter(final String name, final String value) {
        this.parameters.put(name, value);
    }
    
    public String getParameter(final String parameter) {
        if (this.parameters.containsKey(parameter)) {
            return this.parameters.get(parameter);
        }
        return null;
    }
    
    public void addOrSetParameters(final List<String> parameters) {
        if (parameters != null) {
            for (final String param : parameters) {
                this.addOrSetParameter(param);
            }
        }
    }
    
    public void addOrSetParameter(final String parameter) {
        if (StringUtils.isNotBlank(parameter) && !this.parameters.containsKey(parameter)) {
            this.parameters.put(parameter, null);
        }
    }
    
    public List<String> getAggregators(final String key) {
        if (this.aggregators.containsKey(key)) {
            return this.aggregators.get(key);
        }
        return new ArrayList<String>();
    }
    
    public void setAggregators(final String key, final List<String> aggs) {
        if (StringUtils.isNotBlank(key) && aggs != null) {
            this.aggregators.put(key, aggs);
        }
    }
    
    public BackendFlavor getFlavor() throws OlapException {
        final String dataSourceInfo = this.connection.getOlapDatabase().getDataSourceInfo();
        final String proivder = this.connection.getOlapDatabase().getProviderName();
        for (final BackendFlavor flavor : BackendFlavor.values()) {
            if (proivder.contains(flavor.token) || dataSourceInfo.contains(flavor.token)) {
                return flavor;
            }
        }
        throw new AssertionError((Object)("Can't determine the backend vendor. (" + dataSourceInfo + ")"));
    }
    
    public enum BackendFlavor
    {
        MONDRIAN("Mondrian"), 
        SSAS("Microsoft"), 
        PALO("Palo"), 
        SAP("SAP"), 
        ESSBASE("Essbase"), 
        UNKNOWN("");
        
        private final String token;
        
        private BackendFlavor(final String token) {
            this.token = token;
        }
    }
}
