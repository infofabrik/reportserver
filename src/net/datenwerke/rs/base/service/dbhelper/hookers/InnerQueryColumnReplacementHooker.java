package net.datenwerke.rs.base.service.dbhelper.hookers;

import java.util.regex.Pattern;

import net.datenwerke.rs.base.service.dbhelper.hooks.InnerQueryModificationHook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

import org.apache.commons.lang3.StringUtils;

public class InnerQueryColumnReplacementHooker implements InnerQueryModificationHook {
	

	@Override
	public String modifyQuery(String currentQuery, QueryBuilder queryBuilder) {
		
		if(currentQuery.contains("/*<rs:cols>*/") && null != queryBuilder.getColumns() && !queryBuilder.getColumns().isEmpty()){
			String colstr = StringUtils.join(queryBuilder.getColumns(), ", ");
			
			Pattern pattern = Pattern.compile("/\\*<rs:cols>\\*/.*/\\*</rs:cols>\\*/", Pattern.DOTALL | Pattern.MULTILINE);
			return pattern.matcher(currentQuery).replaceAll(colstr);
		}
		
		return currentQuery;
	}

}
