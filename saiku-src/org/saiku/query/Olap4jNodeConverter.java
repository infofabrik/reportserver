// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import org.saiku.query.mdx.IFilterFunction;
import org.olap4j.mdx.parser.MdxParser;
import org.olap4j.mdx.parser.impl.DefaultMdxParserImpl;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.impl.IdentifierParser;
import org.olap4j.metadata.Cube;
import org.olap4j.mdx.MemberNode;
import org.olap4j.mdx.LevelNode;
import org.apache.commons.lang3.StringUtils;
import java.util.Collection;
import org.olap4j.metadata.Member;
import org.olap4j.OlapException;
import org.olap4j.mdx.LiteralNode;
import org.olap4j.metadata.Level;
import org.olap4j.mdx.CallNode;
import org.olap4j.mdx.Syntax;
import org.saiku.query.metadata.CalculatedMeasure;
import org.olap4j.metadata.Measure;
import org.olap4j.mdx.WithSetNode;
import org.olap4j.mdx.AxisNode;
import org.olap4j.mdx.WithMemberNode;
import java.util.Iterator;
import org.olap4j.mdx.IdentifierNode;
import java.util.List;
import org.olap4j.mdx.ParseRegion;
import org.olap4j.mdx.CubeNode;
import org.olap4j.Axis;
import org.saiku.query.metadata.Calculated;
import org.saiku.query.metadata.CalculatedMember;
import org.olap4j.mdx.ParseTreeNode;
import java.util.ArrayList;
import java.util.Collections;
import org.olap4j.mdx.SelectNode;

public class Olap4jNodeConverter extends NodeConverter
{
    public static SelectNode toQuery(final Query query) throws Exception {
        final List<IdentifierNode> cellpropertyList = Collections.emptyList();
        final List<ParseTreeNode> withList = new ArrayList<ParseTreeNode>();
        final List<QueryAxis> axisList = new ArrayList<QueryAxis>();
        for (final CalculatedMember c : query.getCalculatedMembers()) {
            final WithMemberNode wm = NodeConverter.toOlap4jCalculatedMember(c);
            withList.add((ParseTreeNode)wm);
        }
        axisList.add(query.getAxes().get(Axis.COLUMNS));
        axisList.add(query.getAxes().get(Axis.ROWS));
        AxisNode filterAxis = null;
        if (query.getAxes().containsKey(Axis.FILTER)) {
            final QueryAxis axis = query.getAxes().get(Axis.FILTER);
            if (!axis.hierarchies.isEmpty()) {
                filterAxis = toAxis(withList, axis);
            }
        }
        return new SelectNode((ParseRegion)null, (List)withList, (List)toAxisList(withList, axisList), (ParseTreeNode)new CubeNode((ParseRegion)null, query.getCube()), filterAxis, (List)cellpropertyList);
    }
    
    private static List<AxisNode> toAxisList(final List<ParseTreeNode> withList, final List<QueryAxis> axes) throws Exception {
        final ArrayList<AxisNode> axisList = new ArrayList<AxisNode>();
        for (final QueryAxis axis : axes) {
            final AxisNode axisNode = toAxis(withList, axis);
            if (axisNode != null) {
                axisList.add(axisNode);
            }
        }
        return axisList;
    }
    
    private static AxisNode toAxis(final List<ParseTreeNode> withList, final QueryAxis axis) throws Exception {
        final Query.BackendFlavor flavor = axis.getQuery().getFlavor();
        ParseTreeNode axisExpression = null;
        boolean axisAsSet = false;
        final boolean isFilter = Axis.FILTER.equals((Object)axis.getLocation());
        if (!axis.isMdxSetExpression()) {
            final List<ParseTreeNode> hierarchies = new ArrayList<ParseTreeNode>();
            final int hierarchyCount = axis.getQueryHierarchies().size();
            if (hierarchyCount == 1) {
                axisExpression = toHierarchy(withList, axis.getQueryHierarchies().get(0));
                axisAsSet = true;
            }
            else if (hierarchyCount > 1) {
                for (final QueryHierarchy h : axis.getQueryHierarchies()) {
                    final ParseTreeNode hierarchyNode = toHierarchy(withList, h);
                    if (!isFilter) {
                        final WithSetNode withNode = new WithSetNode((ParseRegion)null, NodeConverter.getIdentifier(axis.getName(), h.getHierarchy().getDimension().getName(), h.getName()), hierarchyNode);
                        withList.add((ParseTreeNode)withNode);
                        hierarchies.add((ParseTreeNode)withNode.getIdentifier());
                    }
                    else {
                        hierarchies.add(hierarchyNode);
                    }
                }
                if (System.getProperty("saiku.plugin") != null && System.getProperty("saiku.plugin").equals("true")) {
                    axisExpression = (ParseTreeNode)NodeConverter.generateCrossJoin(hierarchies, axis.isNonEmpty(), false);
                }
                else {
                    axisExpression = (ParseTreeNode)NodeConverter.generateCrossJoin(hierarchies, axis.isNonEmpty(), isFilter);
                }
            }
        }
        axisExpression = toSortedQuerySet(axisExpression, axis);
        ParseTreeNode axisNode = null;
        if (flavor != null && !Query.BackendFlavor.SSAS.equals(flavor) && axisExpression != null && axisAsSet) {
            final WithSetNode withNode2 = new WithSetNode((ParseRegion)null, NodeConverter.getIdentifier(axis.getName()), axisExpression);
            withList.add((ParseTreeNode)withNode2);
            axisNode = (ParseTreeNode)withNode2.getIdentifier();
        }
        else {
            axisNode = axisExpression;
        }
        final QueryDetails details = axis.getQuery().getDetails();
        if (details.getMeasures().size() > 0 && axis.getLocation().equals(details.getAxis())) {
            for (final Measure m : details.getMeasures()) {
                if (m.isCalculatedInQuery()) {
                    final WithMemberNode wm = NodeConverter.toOlap4jCalculatedMember((Calculated)m);
                    withList.add((ParseTreeNode)wm);
                }
            }
            final ParseTreeNode measuresNode = NodeConverter.toOlap4jMeasureSet(details.getMeasures());
            if (axisNode == null) {
                axisNode = measuresNode;
            }
            else {
                final List<ParseTreeNode> axisNodes = new ArrayList<ParseTreeNode>();
                if (details.getLocation().equals(QueryDetails.Location.TOP)) {
                    axisNodes.add(measuresNode);
                    axisNodes.add(axisNode);
                }
                else {
                    axisNodes.add(axisNode);
                    axisNodes.add(measuresNode);
                }
                axisNode = (ParseTreeNode)NodeConverter.generateCrossJoin(axisNodes);
            }
        }
        if (axisNode == null) {
            return null;
        }
        return new AxisNode((ParseRegion)null, axis.isNonEmpty(), axis.getLocation(), (List)new ArrayList(), axisNode);
    }
    
    private static ParseTreeNode toHierarchy(final List<ParseTreeNode> withList, final QueryHierarchy h) throws OlapException {
        ParseTreeNode hierarchySet = null;
        if (!h.isMdxSetExpression()) {
            final boolean isFilter = Axis.FILTER.equals((Object)h.getAxis().getLocation());
            if (h.getActiveQueryLevels().size() == 0 && h.getActiveCalculatedMembers().size() == 0) {
                return (ParseTreeNode)new CallNode((ParseRegion)null, "{}", Syntax.Braces, (List)new ArrayList());
            }
            final List<ParseTreeNode> levels = new ArrayList<ParseTreeNode>();
            ParseTreeNode existSet = null;
            boolean allSimple = true;
            int firstComplex = -1;
            for (int i = 0; i < h.getActiveQueryLevels().size(); ++i) {
                final QueryLevel l = h.getActiveQueryLevels().get(i);
                allSimple = (allSimple & l != null & l.isSimple());
                if (!allSimple && firstComplex == -1) {
                    firstComplex = i;
                    ParseTreeNode levelNode = toLevel(l);
                    levelNode = toQuerySet(levelNode, l);
                    existSet = (ParseTreeNode)NodeConverter.getIdentifier(h.getHierarchy().getDimension().getName(), h.getName(), l.getName());
                    break;
                }
            }
            for (int i = 0; i < h.getActiveQueryLevels().size(); ++i) {
                final QueryLevel l = h.getActiveQueryLevels().get(i);
                ParseTreeNode levelNode = toLevel(l);
                levelNode = toQuerySet(levelNode, l);
                if (h.isConsistent() && existSet != null && i != firstComplex && !l.getLevel().getLevelType().equals((Object)Level.Type.ALL)) {
                    levelNode = (ParseTreeNode)new CallNode((ParseRegion)null, "Exists", Syntax.Function, new ParseTreeNode[] { levelNode, existSet });
                }
                if (!allSimple && h.getActiveQueryLevels().size() > 1) {
                    final WithSetNode withNode = new WithSetNode((ParseRegion)null, NodeConverter.getIdentifier(h.getHierarchy().getDimension().getName(), h.getName(), l.getName()), levelNode);
                    withList.add((ParseTreeNode)withNode);
                    levelNode = (ParseTreeNode)withNode.getIdentifier();
                    if (!l.isSimple() || (existSet != null && i > firstComplex)) {
                        existSet = levelNode;
                    }
                }
                levels.add(levelNode);
            }
            ParseTreeNode levelSet = null;
            if (h.getAxis().isLowestLevelsOnly()) {
                final ParseTreeNode lowestLevel = levels.remove(levels.size() - 1);
                levels.clear();
                levels.add(lowestLevel);
            }
            if (levels.size() > 1) {
                levelSet = (ParseTreeNode)NodeConverter.generateListSetCall(levels);
            }
            else if (levels.size() == 1) {
                levelSet = levels.get(0);
            }
            if (!isFilter && !h.getAxis().isLowestLevelsOnly() && h.needsHierarchize()) {
                levelSet = (ParseTreeNode)new CallNode((ParseRegion)null, "Hierarchize", Syntax.Function, new ParseTreeNode[] { levelSet });
            }
            final List<ParseTreeNode> cmNodes = new ArrayList<ParseTreeNode>();
            for (final CalculatedMember cm : h.getActiveCalculatedMembers()) {
                final WithMemberNode wm = NodeConverter.toOlap4jCalculatedMember(cm);
                cmNodes.add((ParseTreeNode)wm.getIdentifier());
            }
            if (cmNodes.size() > 0) {
                final ParseTreeNode cmSet = (ParseTreeNode)NodeConverter.generateListSetCall(cmNodes);
                if (levelSet != null) {
                    hierarchySet = (ParseTreeNode)NodeConverter.generateSetCall(cmSet, levelSet);
                }
                else {
                    hierarchySet = cmSet;
                }
            }
            else {
                hierarchySet = levelSet;
            }
        }
        hierarchySet = toSortedQuerySet(hierarchySet, h);
        if (h.isVisualTotals()) {
            if (h.getVisualTotalsPattern() != null) {
                hierarchySet = (ParseTreeNode)new CallNode((ParseRegion)null, "VisualTotals", Syntax.Function, new ParseTreeNode[] { hierarchySet, (ParseTreeNode)LiteralNode.createString((ParseRegion)null, h.getVisualTotalsPattern()) });
            }
            else {
                hierarchySet = (ParseTreeNode)new CallNode((ParseRegion)null, "VisualTotals", Syntax.Function, new ParseTreeNode[] { hierarchySet });
            }
        }
        return hierarchySet;
    }
    
    private static ParseTreeNode toLevel(final QueryLevel level) throws OlapException {
        final List<Member> inclusions = new ArrayList<Member>();
        final List<Member> exclusions = new ArrayList<Member>();
        inclusions.addAll(level.getInclusions());
        exclusions.addAll(level.getExclusions());
        if (level.hasParameter()) {
            final String parameterName = level.getParameterName();
            final String parameterValue = level.getQueryHierarchy().getQuery().getParameter(parameterName);
            if (StringUtils.isNotBlank(parameterValue)) {
                final List<Member> resolvedParameters = resolveParameter(level.getQueryHierarchy().getQuery().getCube(), level.getUniqueName(), parameterValue);
                switch (level.getParameterSelectionType()) {
                    case EXCLUSION: {
                        exclusions.clear();
                        exclusions.addAll(resolvedParameters);
                        break;
                    }
                    case INCLUSION: {
                        inclusions.clear();
                        inclusions.addAll(resolvedParameters);
                        break;
                    }
                }
            }
        }
        ParseTreeNode baseNode = (ParseTreeNode)new CallNode((ParseRegion)null, "Members", Syntax.Property, new ParseTreeNode[] { (ParseTreeNode)new LevelNode((ParseRegion)null, level.getLevel()) });
        if (level.getLevel().getLevelType().equals((Object)Level.Type.ALL)) {
            try {
                baseNode = (ParseTreeNode)new MemberNode((ParseRegion)null, level.getLevel().getHierarchy().getDefaultMember());
            }
            catch (OlapException e) {
                throw new RuntimeException("Cannot include hierarchy default member for " + level.getUniqueName());
            }
        }
        baseNode = (ParseTreeNode)NodeConverter.generateSetCall(baseNode);
        if (level.isRange()) {
            if (level.getRangeStart() != null && level.getRangeEnd() != null) {
                final List<ParseTreeNode> args = new ArrayList<ParseTreeNode>();
                args.add((ParseTreeNode)new MemberNode((ParseRegion)null, level.getRangeStart()));
                args.add((ParseTreeNode)new MemberNode((ParseRegion)null, level.getRangeEnd()));
                baseNode = (ParseTreeNode)new CallNode((ParseRegion)null, ":", Syntax.Infix, (List)args);
            }
            else {
                final String startExpr = level.getRangeStartExpr();
                final String endExpr = level.getRangeEndExpr();
                if (StringUtils.isBlank(endExpr)) {
                    baseNode = (ParseTreeNode)NodeConverter.generateSetCall(toMdxNode(startExpr));
                }
                else {
                    baseNode = (ParseTreeNode)NodeConverter.generateSetCall(toMdxNode(startExpr + " : " + endExpr));
                }
            }
        }
        if (inclusions.size() > 0) {
            baseNode = NodeConverter.toOlap4jMemberSet(inclusions);
        }
        if (exclusions.size() > 0) {
            final ParseTreeNode exceptSet = NodeConverter.toOlap4jMemberSet(exclusions);
            baseNode = (ParseTreeNode)new CallNode((ParseRegion)null, "Except", Syntax.Function, new ParseTreeNode[] { baseNode, exceptSet });
        }
        return baseNode;
    }
    
    @Deprecated
    private static List<Member> resolveParameter(final Cube cube, final String parent, final String value) throws OlapException {
        List<IdentifierSegment> parentParts = null;
        if (StringUtils.isNotBlank(parent)) {
            parentParts = (List<IdentifierSegment>)IdentifierParser.parseIdentifier(parent);
        }
        final List<Member> resolvedList = new ArrayList<Member>();
        if (StringUtils.isNotBlank(value)) {
            final String[] split;
            final String[] vs = split = value.split(",");
            for (String v : split) {
                v = v.trim();
                if (StringUtils.isNotBlank(v)) {
                    final List<IdentifierSegment> nameParts = (List<IdentifierSegment>)IdentifierParser.parseIdentifier(v);
                    final List<IdentifierSegment> combined = new ArrayList<IdentifierSegment>();
                    if (parentParts != null && nameParts.size() == 1) {
                        combined.addAll(parentParts);
                    }
                    combined.addAll(nameParts);
                    final Member m = cube.lookupMember((List)combined);
                    if (m == null) {
                        throw new OlapException("Cannot find member with name parts: " + combined.toString());
                    }
                    resolvedList.add(m);
                }
            }
        }
        return resolvedList;
    }
    
    private static ParseTreeNode toMdxNode(final String mdx) {
        final MdxParser parser = (MdxParser)new DefaultMdxParserImpl();
        final ParseTreeNode expression = parser.parseExpression(mdx);
        return expression;
    }
    
    private static ParseTreeNode toQuerySet(ParseTreeNode expression, final IQuerySet o) {
        final MdxParser parser = (MdxParser)new DefaultMdxParserImpl();
        if (o.isMdxSetExpression()) {
            expression = toMdxNode("{" + o.getMdxSetExpression() + "}");
        }
        if (expression != null && o.getFilters().size() > 0) {
            for (final IFilterFunction filter : o.getFilters()) {
                expression = filter.visit(parser, expression);
            }
        }
        return expression;
    }
    
    private static ParseTreeNode toSortedQuerySet(ParseTreeNode expression, final ISortableQuerySet o) {
        expression = toQuerySet(expression, o);
        if (expression == null) {
            return null;
        }
        if (o.getSortOrder() != null) {
            final LiteralNode evaluatorNode = LiteralNode.createSymbol((ParseRegion)null, o.getSortEvaluationLiteral());
            expression = (ParseTreeNode)new CallNode((ParseRegion)null, "Order", Syntax.Function, new ParseTreeNode[] { expression, (ParseTreeNode)evaluatorNode, (ParseTreeNode)LiteralNode.createSymbol((ParseRegion)null, o.getSortOrder().name()) });
        }
        else if (o.getHierarchizeMode() != null) {
            if (o.getHierarchizeMode().equals(ISortableQuerySet.HierarchizeMode.PRE)) {
                expression = (ParseTreeNode)new CallNode((ParseRegion)null, "Hierarchize", Syntax.Function, new ParseTreeNode[] { expression });
            }
            else {
                if (!o.getHierarchizeMode().equals(ISortableQuerySet.HierarchizeMode.POST)) {
                    throw new RuntimeException("Missing value handler.");
                }
                expression = (ParseTreeNode)new CallNode((ParseRegion)null, "Hierarchize", Syntax.Function, new ParseTreeNode[] { expression, (ParseTreeNode)LiteralNode.createSymbol((ParseRegion)null, o.getHierarchizeMode().name()) });
            }
        }
        return expression;
    }
}
