package net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.fto.FtoSupervisor;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;

import com.sencha.gxt.core.client.ValueProvider;

/**
 * Dto Decorator for {@link ScriptReportDto}
 *
 */
public class ScriptReportDtoDec extends ScriptReportDto implements FtoSupervisor {


	private static final long serialVersionUID = 1L;

	public ScriptReportDtoDec() {
		super();
	}

	@Override
	public boolean consumes(ValueProvider vp) {
		return "exportFormats".equals(vp.getPath());
	}

	@Override
	public String adaptFtoGeneration(ValueProvider vp, Dto dto) {
		StringBuilder sb = new StringBuilder();
		
		List<String> l = (List<String>) vp.getValue(dto);
		if(null == l)
			return "";
		boolean first = true;
		for(String f : l){
			if(first)
				first=false;
			else
				sb.append(":");
			
			sb.append(f.replace(":", "\\:"));
		}
		return sb.toString();
	}

	@Override
	public void decodeFromFto(String val, PropertyAccessor pa, Dto dto) {
		if(null == val)
			return;
		val = val.replace("\\:", ":");
		
		List<String> values = new ArrayList<String>();
		for(String v : val.split(":"))
			values.add(v);
		pa.setValue(dto, values);
	}

}
