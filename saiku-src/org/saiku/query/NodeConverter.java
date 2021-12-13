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
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.olap4j.mdx.CallNode;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.LiteralNode;
import org.olap4j.mdx.MemberNode;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.mdx.PropertyValueNode;
import org.olap4j.mdx.Syntax;
import org.olap4j.mdx.WithMemberNode;
import org.olap4j.mdx.parser.MdxParser;
import org.olap4j.mdx.parser.impl.DefaultMdxParserImpl;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.saiku.query.metadata.Calculated;

public class NodeConverter {
	
	protected static CallNode generateSetCall(ParseTreeNode... args) {
		return
				new CallNode(
						null,
						"{}",
						Syntax.Braces,
						args);
	}

	protected static CallNode generateListSetCall(List<ParseTreeNode> cnodes) {
		return
				new CallNode(
						null,
						"{}",
						Syntax.Braces,
						cnodes);
	}

	protected static CallNode generateListTupleCall(List<ParseTreeNode> cnodes) {
		return
				new CallNode(
						null,
						"()",
						Syntax.Parentheses,
						cnodes);
	}

	protected static CallNode generateCrossJoin(List<ParseTreeNode> selections) {
		return generateCrossJoin(selections, false, true);
	}

	protected static CallNode generateCrossJoin(List<ParseTreeNode> selections, boolean nonEmpty, boolean asterisk)
	{
		String crossJoinFun = nonEmpty ? "NonEmptyCrossJoin" : "CrossJoin";
		if (selections == null || selections.size() == 0)
			return null;
		
		ParseTreeNode sel1 = selections.get(0);
		if (sel1 instanceof MemberNode) {
			sel1 = generateSetCall(sel1);
		}
		if (selections.size() == 2) {
			ParseTreeNode sel2 = selections.get(1);
			if (sel2 instanceof MemberNode) {
				sel2 = generateSetCall(sel2);
			}
			return new CallNode(
					null, crossJoinFun, Syntax.Function, sel1, sel2);
		} else {
			
			if (asterisk) {
				return new CallNode(
						null, " * ", Syntax.Infix, selections);
				
			} else {
				/* patched call */
				ParseTreeNode last = selections.get(selections.size()-1);
				selections.remove(last);
				return new CallNode(null, crossJoinFun, Syntax.Function,
						generateCrossJoin(selections, nonEmpty, asterisk), last);
				
//				return new CallNode(
//				null, crossJoinFun, Syntax.Function, selections);
				
				/* original */
//				selections.remove(0);
//				return new CallNode(
//						null, crossJoinFun, Syntax.Function, sel1,
//						generateCrossJoin(selections, nonEmpty, asterisk));
			}
		}
	}

	protected static CallNode generateUnion(List<List<ParseTreeNode>> unions) {
		if (unions.size() > 2) {
			List<ParseTreeNode> first = unions.remove(0);
			return new CallNode(
					null, "Union", Syntax.Function,
					generateCrossJoin(first),
					generateUnion(unions));
		} else {
			return new CallNode(
					null, "Union", Syntax.Function,
					generateCrossJoin(unions.get(0)),
					generateCrossJoin(unions.get(1)));
		}
	}

	protected static CallNode generateHierarchizeUnion(
			List<List<ParseTreeNode>> unions)
	{
		return new CallNode(
				null, "Hierarchize", Syntax.Function,
				generateUnion(unions));
	}
	
	
	protected static ParseTreeNode toOlap4jMemberSet(List<Member> members) {
		List<ParseTreeNode> membernodes = new ArrayList<ParseTreeNode>();
		for (Member m : members) {
			membernodes.add(new MemberNode(null, m));
		}
		return generateListSetCall(membernodes);
	}

	protected static ParseTreeNode toOlap4jMeasureSet(List<Measure> measures) {
		List<ParseTreeNode> membernodes = new ArrayList<ParseTreeNode>();
		for (Measure m : measures) {
			membernodes.add(new MemberNode(null, m));
		}
		return generateListSetCall(membernodes);
	}
	
	
	protected static WithMemberNode toOlap4jCalculatedMember(Calculated cm) {
		MdxParser parser = new DefaultMdxParserImpl();
		ParseTreeNode formula = parser.parseExpression(cm.getFormula());
		List<PropertyValueNode> propertyList = new ArrayList<PropertyValueNode>();
		for (Entry<String, String> entry : cm.getFormatProperties().entrySet()) {
			ParseTreeNode exp = LiteralNode.createString(null,  entry.getValue());
			String name = entry.getKey();
			PropertyValueNode prop = new PropertyValueNode(null, name, exp);
			propertyList.add(prop);
		}
		WithMemberNode wm = new WithMemberNode(
				null, 
				IdentifierNode.parseIdentifier(cm.getUniqueName()), 
				formula, 
				propertyList);
		return wm;
	}
	
	protected static IdentifierNode getIdentifier(String... identifiers) {
		String identifier = "";
		for (int i = 0; i < identifiers.length; i ++) {
			if (i == 0) {
				identifier = "~" + identifiers[0];
			} else {
				identifier += "_" + identifiers[i];
			}
		}
		return IdentifierNode.ofNames(identifier);
	}




}
