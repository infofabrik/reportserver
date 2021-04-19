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
package org.saiku.query.mdx;

import java.util.ArrayList;
import java.util.List;

import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.mdx.parser.MdxParser;

public class GenericFilter extends AbstractFilterFunction {

	private String filterExpression;
	private MdxFunctionType type;

	public GenericFilter(String filterExpression) {
		this.filterExpression = filterExpression;
		this.type = MdxFunctionType.Filter;
	}
	
	public String getFilterExpression() {
		return filterExpression;
	}

	@Override
	public List<ParseTreeNode> getArguments(MdxParser parser) {
		List<ParseTreeNode> arguments = new ArrayList<ParseTreeNode>();
		ParseTreeNode filterExp =  parser.parseExpression(filterExpression);		
		arguments.add(filterExp);
		return arguments;
	}

	@Override
	public MdxFunctionType getFunctionType() {
		return type;
	}
}
