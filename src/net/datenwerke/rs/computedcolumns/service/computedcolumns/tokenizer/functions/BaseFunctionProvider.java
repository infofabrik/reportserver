package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.functions;

import java.util.List;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.annotations.AllowedFunctions;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class BaseFunctionProvider implements FunctionProviderHook {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject @AllowedFunctions
	private List<String> functions;
	
	@Override
	public boolean consumes(String strToken, ExpressionTokenizer expressionTokenizer) {
		try {
			if(strToken.contains("(") && StringUtils.countMatches(strToken, ")") != StringUtils.countMatches(strToken, "("))
				return false;
			
			for(String func : functions){
				if(func.toLowerCase().equals(strToken.toLowerCase()))
					return true;
			}
			
		} catch(Exception e){
			logger.info( "Exception while processing funciton", e);
		}
		
		return false;
	}

}
