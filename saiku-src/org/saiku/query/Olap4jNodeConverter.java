/*
/*  
 *   Copyright 2014 Paul Stoellberger
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.saiku.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.olap4j.Axis;
import org.olap4j.OlapException;
import org.olap4j.impl.IdentifierParser;
import org.olap4j.mdx.AxisNode;
import org.olap4j.mdx.CallNode;
import org.olap4j.mdx.CubeNode;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.mdx.LevelNode;
import org.olap4j.mdx.LiteralNode;
import org.olap4j.mdx.MemberNode;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.mdx.SelectNode;
import org.olap4j.mdx.Syntax;
import org.olap4j.mdx.WithMemberNode;
import org.olap4j.mdx.WithSetNode;
import org.olap4j.mdx.parser.MdxParser;
import org.olap4j.mdx.parser.impl.DefaultMdxParserImpl;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Level.Type;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.saiku.query.Query.BackendFlavor;
import org.saiku.query.mdx.IFilterFunction;
import org.saiku.query.metadata.CalculatedMeasure;
import org.saiku.query.metadata.CalculatedMember;

/**
 * Utility class to convert a Query object to a SelectNode.
 */
public class Olap4jNodeConverter extends NodeConverter {

	public static SelectNode toQuery(Query query) throws Exception {
		List<IdentifierNode> cellpropertyList = Collections.emptyList();
		List<ParseTreeNode> withList = new ArrayList<ParseTreeNode>();
		List<QueryAxis> axisList = new ArrayList<QueryAxis>();

		for(CalculatedMember c: query.getCalculatedMembers()){
			WithMemberNode wm = toOlap4jCalculatedMember(c);
			withList.add(wm);
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
		return new SelectNode(
				null,
				withList,
				toAxisList(withList, axisList),
				new CubeNode(
						null,
						query.getCube()),
						filterAxis,
						cellpropertyList);
	}

	private static List<AxisNode> toAxisList(List<ParseTreeNode> withList, List<QueryAxis> axes) throws Exception {
		final ArrayList<AxisNode> axisList = new ArrayList<AxisNode>();
		for (QueryAxis axis : axes) {
			AxisNode axisNode = toAxis(withList, axis);
			if (axisNode != null) {
				axisList.add(axisNode);
			}
		}
		return axisList;
	}



	/*
	 * This method merges the selections into a single
	 * MDX axis selection.  Right now we do a simple
	 * crossjoin.
	 * It might return null if there are no dimensions placed on the axis.
	 */
	private static AxisNode toAxis(List<ParseTreeNode> withList, QueryAxis axis) throws Exception {

		BackendFlavor flavor = axis.getQuery().getFlavor();
		ParseTreeNode axisExpression = null;
		boolean axisAsSet = false;
		boolean isFilter = Axis.FILTER.equals(axis.getLocation());
		if (!axis.isMdxSetExpression()) {
			List<ParseTreeNode> hierarchies = new ArrayList<ParseTreeNode>();
			
			int hierarchyCount = axis.getQueryHierarchies().size();
			if (hierarchyCount == 1) {
				axisExpression = toHierarchy(withList, axis.getQueryHierarchies().get(0));
				axisAsSet = true;
			} else if (hierarchyCount > 1) {
				for(QueryHierarchy h : axis.getQueryHierarchies()) {
					ParseTreeNode hierarchyNode = toHierarchy(withList, h);
					if (!isFilter) {
						WithSetNode withNode = new WithSetNode(null, getIdentifier(axis.getName(), h.getHierarchy().getDimension().getName(), h.getName()), hierarchyNode);
						withList.add(withNode);		
						hierarchies.add(withNode.getIdentifier());
					} else {
						hierarchies.add(hierarchyNode);
					}
				}

				if(System.getProperty("saiku.plugin")!=null && System.getProperty("saiku.plugin").equals("true")) {
					axisExpression = generateCrossJoin(hierarchies, axis.isNonEmpty(), false);
				}
				else{
					axisExpression = generateCrossJoin(hierarchies, axis.isNonEmpty(), isFilter);
				}
			} else {
				// TODO do we need to handle hierarchy count == 0 ?
			}

		}
		axisExpression = toSortedQuerySet(axisExpression, axis);
//		TODO - it seems like its better to have the crossjoin as close to the NON EMPTY axis etc. as possible in mondrian 3 - works ok in mondrian 4
		ParseTreeNode axisNode = null;
		if ((flavor != null && !BackendFlavor.SSAS.equals(flavor)) && axisExpression != null && axisAsSet) {
			WithSetNode withNode = new WithSetNode(null, getIdentifier(axis.getName()), axisExpression);
			withList.add(withNode);
			axisNode = withNode.getIdentifier();
		} else {
			axisNode = axisExpression;
		}
		QueryDetails details = axis.getQuery().getDetails();

		if (details.getMeasures().size() > 0 && axis.getLocation().equals(details.getAxis())) {
			for (Measure m : details.getMeasures()) {
				if (m.isCalculatedInQuery()) {
					WithMemberNode wm = toOlap4jCalculatedMember((CalculatedMeasure) m);
					withList.add(wm);
				}
			}
			
			
			ParseTreeNode measuresNode = toOlap4jMeasureSet(details.getMeasures());
			if (axisNode == null) {
				axisNode = measuresNode;
			} else {
				List<ParseTreeNode> axisNodes = new ArrayList<ParseTreeNode>();
				if (details.getLocation().equals(QueryDetails.Location.TOP)) {
					axisNodes.add(measuresNode);
					axisNodes.add(axisNode);	
				} else {
					axisNodes.add(axisNode);
					axisNodes.add(measuresNode);
				}
				axisNode = generateCrossJoin(axisNodes);
			}
		}
		
		if (axisNode == null) {
			return null;
		}
		return new AxisNode(
				null,
				axis.isNonEmpty(),
				axis.getLocation(),
				new ArrayList<IdentifierNode>(),
				axisNode);
	}

	private static ParseTreeNode toHierarchy(List<ParseTreeNode> withList, QueryHierarchy h) throws OlapException {
		ParseTreeNode hierarchySet = null;

		if (!h.isMdxSetExpression()) {
			boolean isFilter = Axis.FILTER.equals(h.getAxis().getLocation());
			// TODO probably not the best idea
			if (h.getActiveQueryLevels().size() == 0 && h.getActiveCalculatedMembers().size() == 0) {
				return new CallNode(null, "{}", Syntax.Braces, new ArrayList<ParseTreeNode>());
				
			}
			List<ParseTreeNode> levels = new ArrayList<ParseTreeNode>();
			ParseTreeNode existSet = null;
			boolean allSimple = true;
			int firstComplex = -1;

			for (int i = 0; i < h.getActiveQueryLevels().size(); i++) {
				QueryLevel l = h.getActiveQueryLevels().get(i);
				allSimple = allSimple & l != null & l.isSimple();
				// if we find a complex node, save it so parent levels use it for exists
				if (!allSimple && firstComplex == -1) {
					firstComplex = i;
					ParseTreeNode levelNode = toLevel(l);
					levelNode = toQuerySet(levelNode, l);
					existSet = getIdentifier(h.getHierarchy().getDimension().getName(), h.getName(), l.getName());
					break;
				}
			}
			for (int i = 0; i < h.getActiveQueryLevels().size(); i++) {
				QueryLevel l = h.getActiveQueryLevels().get(i);
				ParseTreeNode levelNode = toLevel(l);
				levelNode = toQuerySet(levelNode, l);
				
				// don't include exist set if exist set was set on a lower level
				if (h.isConsistent() && existSet != null && i != firstComplex && !l.getLevel().getLevelType().equals(Type.ALL)) {
					levelNode = new CallNode(null, "Exists", Syntax.Function, levelNode, existSet);
				}
				if (!allSimple && h.getActiveQueryLevels().size() > 1) {
					WithSetNode withNode = new WithSetNode(null, getIdentifier(h.getHierarchy().getDimension().getName(), h.getName(), l.getName()), levelNode);
					withList.add(withNode);
					levelNode = withNode.getIdentifier();
					if (!l.isSimple() || (existSet != null && i > firstComplex)) {
						existSet = levelNode;
					}
				}
				levels.add(levelNode);
			}
			ParseTreeNode levelSet = null;
			if (h.getAxis().isLowestLevelsOnly()) {
				ParseTreeNode lowestLevel = levels.remove(levels.size() -1);
				levels.clear();
				levels.add(lowestLevel);
			}
			if (levels.size() > 1) {
				levelSet = generateListSetCall(levels);
			} else if (levels.size() == 1) {
				levelSet = levels.get(0);
			}

			// hierarchize() on the hierarchy is only needed if we select more than 1 level and on axes != FILTER
			if ( !isFilter && !h.getAxis().isLowestLevelsOnly() && h.needsHierarchize()) {
				levelSet = new CallNode(
						null,
						"Hierarchize",
						Syntax.Function,
						levelSet);

			}
				
			List<ParseTreeNode> cmNodes = new ArrayList<ParseTreeNode>();
			for (CalculatedMember cm : h.getActiveCalculatedMembers()) {
				WithMemberNode wm = toOlap4jCalculatedMember(cm);
				//withList.add(wm);*/
				cmNodes.add(wm.getIdentifier());
			}
			if (cmNodes.size() > 0) {
				ParseTreeNode cmSet = generateListSetCall(cmNodes);
				if (levelSet != null) {
					hierarchySet = generateSetCall(cmSet, levelSet);
				} else {
					hierarchySet = cmSet;
				}
			} else {
				hierarchySet = levelSet;	
			}
		}
		hierarchySet = toSortedQuerySet(hierarchySet, h);

		if (h.isVisualTotals()) {
			if (h.getVisualTotalsPattern() !=  null) {
				hierarchySet = new CallNode(
						null,
						"VisualTotals",
						Syntax.Function,
						hierarchySet,
						LiteralNode.createString(null,  h.getVisualTotalsPattern()));
			} else {
				hierarchySet = new CallNode(
						null,
						"VisualTotals",
						Syntax.Function,
						hierarchySet);
			}
			
		}
		return hierarchySet;
	}

	private static ParseTreeNode toLevel(QueryLevel level) throws OlapException {
		List<Member> inclusions = new ArrayList<Member>();
		List<Member> exclusions = new ArrayList<Member>();
		inclusions.addAll(level.getInclusions());
		exclusions.addAll(level.getExclusions());
		
		if (level.hasParameter()) {
			String parameterName = level.getParameterName();
			String parameterValue = level.getQueryHierarchy().getQuery().getParameter(parameterName);
			if (StringUtils.isNotBlank(parameterValue)) {
				List<Member> resolvedParameters = resolveParameter(level.getQueryHierarchy().getQuery().getCube(), level.getUniqueName(), parameterValue);
				switch(level.getParameterSelectionType()) {
					case EXCLUSION:
						exclusions.clear();
						exclusions.addAll(resolvedParameters);
					break;
					case INCLUSION:
						inclusions.clear();
						inclusions.addAll(resolvedParameters);
					break;
				default:
					break;
				}
			}
		}
		
		ParseTreeNode baseNode = new CallNode(null, "Members", Syntax.Property, new LevelNode(null, level.getLevel()));
		if (level.getLevel().getLevelType().equals(Type.ALL)) {
			try {
				baseNode = new MemberNode(null, level.getLevel().getHierarchy().getDefaultMember());
			} catch (OlapException e) {
				throw new RuntimeException("Cannot include hierarchy default member for " + level.getUniqueName());
			}
		}

		// TODO shall we really wrap all <Level>.Members into {} ?
		baseNode = generateSetCall(baseNode);
		
		if (level.isRange()) {
			if (level.getRangeStart() != null && level.getRangeEnd() != null) {
				List<ParseTreeNode> args = new ArrayList<ParseTreeNode>();
				args.add(new MemberNode(null, level.getRangeStart()));
				args.add(new MemberNode(null, level.getRangeEnd()));
				baseNode = new CallNode(null, ":", Syntax.Infix, args);
			} else {
				String startExpr = level.getRangeStartExpr();
				String endExpr = level.getRangeEndExpr();
				if (StringUtils.isBlank(endExpr)) {
					baseNode = generateSetCall(toMdxNode(startExpr));
				} else {
					baseNode = generateSetCall(toMdxNode(startExpr + " : " + endExpr));
				}
				
			}
		}
		if (inclusions.size() > 0) {
			baseNode = toOlap4jMemberSet(inclusions);
		}
		if (exclusions.size() > 0) {
			ParseTreeNode exceptSet = toOlap4jMemberSet(exclusions);
			baseNode =  new CallNode(null, "Except", Syntax.Function, baseNode, exceptSet);			
		}
		
//		if (Axis.FILTER.equals(level.getQueryHierarchy().getAxis().getLocation())
//				&& inclusions.size() == 0 && exclusions.size() == 0 && !level.isRange()) {
//			baseNode = null;
//		}
		
		return baseNode;
	}
	
	@Deprecated
	private static List<Member> resolveParameter(Cube cube, String parent, String value) throws OlapException {
			List<IdentifierSegment> parentParts = null;
			if (StringUtils.isNotBlank(parent)) {
				parentParts = IdentifierParser.parseIdentifier(parent);
			}
			List<Member> resolvedList = new ArrayList<Member>();
			if (StringUtils.isNotBlank(value)) {
				String[] vs = value.split(",");
				for (String v : vs) {
					v = v.trim();
					if (StringUtils.isNotBlank(v)) {
						List<IdentifierSegment> nameParts = IdentifierParser.parseIdentifier(v);
						List<IdentifierSegment> combined = new ArrayList<IdentifierSegment>();
						if (parentParts != null && nameParts.size() == 1) {
							combined.addAll(parentParts);
						}
						combined.addAll(nameParts);
						Member m = cube.lookupMember(combined);
						if (m == null) {
							throw new OlapException("Cannot find member with name parts: " + combined.toString());
						}
						resolvedList.add(m);
					}
				}
			}
			return resolvedList;
	}
	
	private static ParseTreeNode toMdxNode(String mdx) {
		MdxParser parser = new DefaultMdxParserImpl();
		ParseTreeNode expression =  parser.parseExpression(mdx);
		return expression;
	}
	
	private static ParseTreeNode toQuerySet(ParseTreeNode expression, IQuerySet o) {
		MdxParser parser = new DefaultMdxParserImpl();
		if (o.isMdxSetExpression()) {
			expression =  toMdxNode("{" + o.getMdxSetExpression() + "}");
		}

		if (expression != null && o.getFilters().size() > 0) {
			for (IFilterFunction filter : o.getFilters()) {
				expression = filter.visit(parser, expression);
			}
		}
		
		return expression;
		
	}
	
	private static ParseTreeNode toSortedQuerySet(ParseTreeNode expression, ISortableQuerySet o) {
		expression = toQuerySet(expression, o);
		if (expression == null) {
			return null;
		}
		if (o.getSortOrder() != null) {
			LiteralNode evaluatorNode =
					LiteralNode.createSymbol(
							null,
							o.getSortEvaluationLiteral());
			expression =
					new CallNode(
							null,
							"Order",
							Syntax.Function,
							expression,
							evaluatorNode,
							LiteralNode.createSymbol(
									null, o.getSortOrder().name()));
		} else if (o.getHierarchizeMode() != null) {
			if (o.getHierarchizeMode().equals(
					ISortableQuerySet.HierarchizeMode.PRE))
			{
				// In pre mode, we don't add the "POST" literal.
				expression = new CallNode(
						null,
						"Hierarchize",
						Syntax.Function,
						expression);
			} else if (o.getHierarchizeMode().equals(
					ISortableQuerySet.HierarchizeMode.POST))
			{
				expression = new CallNode(
						null,
						"Hierarchize",
						Syntax.Function,
						expression,
						LiteralNode.createSymbol(
								null, o.getHierarchizeMode().name()));
			} else {
				throw new RuntimeException("Missing value handler.");
			}
		}
		return expression;

	}
}










