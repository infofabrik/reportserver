package net.datenwerke.rs.base.service.reportengines.hookers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaRequestAndLocationImpl;

public class ConfigureBaseReportViaRequestHooker extends ConfigureReportViaRequestAndLocationImpl {

	@Override
	public void adjustReport(Report report, ParameterProvider req) {
		if(! (report instanceof TableReport))
			return;
		
		List<Column> columns = new ArrayList<Column>();
		
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name = parameterNames.nextElement();
			
			if(report instanceof TableReport){
				if(name.startsWith("c_")){ //$NON-NLS-1$
					int columnPosition = Integer.parseInt(name.substring(2));
					
					String columnName = req.getParameter(name);
					String alias = null;
					if(columnName.contains("|")){ //$NON-NLS-1$
						int pos = columnName.indexOf("|"); //$NON-NLS-1$
						alias = columnName.substring(pos+1);
						columnName = columnName.substring(0, pos);
					}
						
					Column column = new Column();
					column.setPosition(columnPosition);
					column.setName(columnName);
					column.setAlias(alias);
					
					getIncludeCondition(column, columnPosition, req);
					getExcludeCondition(column, columnPosition, req);
					
					getAggregateCondition(column, columnPosition, req);
					getOrderCondition(column, columnPosition, req);
					getHiddenCondition(column, columnPosition, req);
					
					columns.add(column);
				}
				else if(name.startsWith("ac_")){ //$NON-NLS-1$
					int columnPosition = Integer.parseInt(name.substring(3));
					
					String columnName = req.getParameter(name);
					if(null == columnName)
						continue;
					
					String alias = null;
					if(columnName.contains("|")){ //$NON-NLS-1$
						int pos = columnName.indexOf("|"); //$NON-NLS-1$
						alias = columnName.substring(pos+1);
						columnName = columnName.substring(0, pos);
					}
						
					for(AdditionalColumnSpec spec : ((TableReport) report).getAdditionalColumns()){
						if(null == spec.getName())
							continue;
						
						if(columnName.toLowerCase().equals(spec.getName().toLowerCase())){
							ColumnReference ref = new ColumnReference();
							ref.setReference(spec);
							
							ref.setPosition(columnPosition);
							ref.setName(columnName);
							ref.setAlias(alias);
							
							getIncludeCondition(ref, columnPosition, req);
							getExcludeCondition(ref, columnPosition, req);
							
							getAggregateCondition(ref, columnPosition, req);
							getOrderCondition(ref, columnPosition, req);
							getHiddenCondition(ref, columnPosition, req);
							
							columns.add(ref);
							break;
						}
					}
				}
				
				if(name.toLowerCase().equals("allcolumns") && "true".equals(req.getParameter(name))){
					((TableReport)report).setSelectAllColumns(true);
				}
			}
		}
		
		if( (! (report instanceof TableReportVariant ) &&  columns.isEmpty()))
			((TableReport)report).setSelectAllColumns(true);
		
		if(report instanceof TableReport && ! columns.isEmpty()){
			Collections.sort(columns, new Comparator<Column>() {
				public int compare(Column o1, Column o2) {
					return ((Integer)o1.getPosition()).compareTo(o2.getPosition());
				}
			});
			((TableReport)report).setColumns(columns);
		}
		
	}

	private void getHiddenCondition(Column column, int columnPosition,
			ParameterProvider req) {
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name = parameterNames.nextElement();
			if(name.startsWith("h_")){
				int position = Integer.parseInt(name.substring(2));
				if(position == columnPosition){
					column.setHidden(true);
				}
			}
		}
	}

	private void getOrderCondition(Column column, int columnPosition,
			ParameterProvider req) {
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name = parameterNames.nextElement();
			if(name.startsWith("or_")){
				int position = Integer.parseInt(name.substring(3));
				if(position == columnPosition){
					String par = req.getParameter(name);
					if("asc".equals(par.toLowerCase()))
						column.setOrder(Order.ASC);
					if("desc".equals(par.toLowerCase()))
						column.setOrder(Order.DESC);
				}
			}
		}
	}

	private void getAggregateCondition(Column column, int columnPosition,
			ParameterProvider req) {
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name = parameterNames.nextElement();
			if(name.startsWith("agg_")){
				int position = Integer.parseInt(name.substring(4));
				if(position == columnPosition){
					String par = req.getParameter(name);
					
					AggregateFunction agg = null;
					try{
						agg = AggregateFunction.valueOf(par);
					} catch(Exception e){}

					try{
						if(null == agg)
							agg = AggregateFunction.valueOf(par.toUpperCase());
					} catch(Exception e){}
					
						
					if(null != agg)
						column.setAggregateFunction(agg);
				}
			}
		}
	}

	private void getIncludeCondition(Column column, int columnPosition, ParameterProvider req) {
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name = parameterNames.nextElement();
			if(name.startsWith("fi_")){
				int filterPosition = Integer.parseInt(name.substring(3));
				if(filterPosition == columnPosition){
					String par = req.getParameter(name);
					String[] cond = par.split("\\|");
					
					List<String> filterList = Arrays.asList(cond);
					if( null == column.getFilter())
						column.setFilter(new Filter());
					column.getFilter().setIncludeValues(filterList);
				}
			} else if(name.startsWith("fri_")){
				int filterPosition = Integer.parseInt(name.substring(4));
				if(filterPosition == columnPosition){
					String par = req.getParameter(name);
					String[] cond = par.split("\\|");
					
					List<FilterRange> filterList = new ArrayList<FilterRange>();
					for(String c : cond){
						c = c.trim();
						if(c.contains(" - ")){
							String[] fromTo = c.split(" \\- ", 2);
							filterList.add(new FilterRange(fromTo[0], fromTo[1]));
						} else if(c.startsWith("- ")){
							filterList.add(new FilterRange(null, c.substring(2)));
						} else if(c.endsWith(" -")){
							filterList.add(new FilterRange(c.substring(0,c.length()-2), null));
						}
					}
					if(null == column.getFilter())
						column.setFilter(new Filter());
					column.getFilter().setIncludeRanges(filterList);
				}
			}
		}
	}
	
	private void getExcludeCondition(Column column, int columnPosition, ParameterProvider req) {
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name = parameterNames.nextElement();
			if(name.startsWith("fe_")){
				int filterPosition = Integer.parseInt(name.substring(3));
				if(filterPosition == columnPosition){
					String par = req.getParameter(name);
					String[] cond = par.split("\\|");
					
					List<String> filterList = Arrays.asList(cond);
					if( null == column.getFilter())
						column.setFilter(new Filter());
					column.getFilter().setExcludeValues(filterList);
				}
			} else if(name.startsWith("fre_")){
				int filterPosition = Integer.parseInt(name.substring(4));
				if(filterPosition == columnPosition){
					String par = req.getParameter(name);
					String[] cond = par.split("\\|");
					
					List<FilterRange> filterList = new ArrayList<FilterRange>();
					for(String c : cond){
						c = c.trim();
						if(c.contains(" - ")){
							String[] fromTo = c.split(" \\- ", 2);
							filterList.add(new FilterRange(fromTo[0], fromTo[1]));
						} else if(c.startsWith("- ")){
							filterList.add(new FilterRange(null, c.substring(2)));
						} else if(c.endsWith(" -")){
							filterList.add(new FilterRange(c.substring(0,c.length()-2), null));
						}
					}
					if(null == column.getFilter())
						column.setFilter(new Filter());
					column.getFilter().setExcludeRanges(filterList);
				}
			}
		}
	}

}
