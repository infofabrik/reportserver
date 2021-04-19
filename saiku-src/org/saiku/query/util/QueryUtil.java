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
package org.saiku.query.util;

import org.saiku.query.IQuerySet;
import org.saiku.query.ISortableQuerySet;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryUtil {
	
	public static List<String> parseParameters(String expression) {
		List<String> parameterNames = new ArrayList<String>();
		if (StringUtils.isNotBlank(expression)) {
			char[] exArray = expression.toCharArray();
			
			for (int i = 0; i < exArray.length-2; i++) {
				if (exArray[i] == '$' && exArray[i+1] == '{') {
					StringBuffer sb = new StringBuffer();
					for(i = i + 2; i < exArray.length && exArray[i] != '}'; i++) {
						sb.append(exArray[i]);
					}
					String newParam = sb.toString();
					if (StringUtils.isNotBlank(newParam)) {
						parameterNames.add(newParam);
					}
				}
			}
		}
		return parameterNames;
	}
	
	public static List<String> retrieveSortableSetParameters(ISortableQuerySet sqs) {
		List<String> parameterNames = new ArrayList<String>();
		String sortEval = sqs.getSortEvaluationLiteral();
		List<String> sortParameters = parseParameters(sortEval);
		parameterNames.addAll(sortParameters);
		parameterNames.addAll( retrieveSetParameters(sqs));
		return parameterNames;
	}
	
	public static List<String> retrieveSetParameters(IQuerySet qs) {
		List<String> parameterNames = new ArrayList<String>();

      String mdx = qs.getMdxSetExpression();

      List<String> mdxParameters = parseParameters(mdx);

		parameterNames.addAll(mdxParameters);
		
//		MdxParser parser = new DefaultMdxParserImpl();
//		if (qs.getFilters() != null) {
//			for (IFilterFunction f : qs.getFilters()) {
//				List<ParseTreeNode> args = f.getArguments(parser);
//			}
//		}
		
		return parameterNames;
		
	}

}
