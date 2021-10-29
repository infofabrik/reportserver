package net.datenwerke.rs.birt.service.datasources.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.birt.core.data.DataType;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.IDataIterator;
import org.eclipse.birt.report.engine.api.IExtractionResults;
import org.eclipse.birt.report.engine.api.IParameterDefn;
import org.eclipse.birt.report.engine.api.IParameterSelectionChoice;
import org.eclipse.birt.report.engine.api.IResultMetaData;
import org.eclipse.birt.report.model.api.DataSetHandle;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceTargetType;
import net.datenwerke.rs.birt.service.reportengine.BirtHelper;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public class BirtToRSTableHelper  {

	private final Provider<BirtHelper> birtHelperProvider;
	

	@Inject
	public BirtToRSTableHelper(
		Provider<BirtHelper> birtHelperProvider
		){
		
		this.birtHelperProvider = birtHelperProvider;
	}

	
	public RSTableModel asTableModel(DatasourceContainerProvider datasourceContainerProvider,
			BirtReport report,
			ParameterSet parameters,
			String target, 
			BirtReportDatasourceTargetType targetType)  {
		
		RSTableModel tableModel = null;
		BirtHelper birtHelper = birtHelperProvider.get();
		try {
			birtHelper.loadReportDesign(report);
			
			switch(targetType){
			case DATASET:
				Collection<DataSetHandle> dataSetHandles = birtHelper.getDataSetHandles();
				for(DataSetHandle dh : dataSetHandles){
					if(null == target || dh.getName().equals(target) || target.isEmpty()){
						IExtractionResults dataSetData = birtHelper.getDataSetData(report, dh, parameters);
						try{
							tableModel = createTableModelFromIExtractionResult(dataSetData);
						}finally{
							dataSetData.close();
						}
						break;
					}
				}
				break;
			case PARAMETER:
				for(IParameterDefn pd : birtHelper.getParameterDefinitions()){
					if(null == target || pd.getName().equals(target) || target.isEmpty()){
						Collection<IParameterSelectionChoice> parameterSelectionList = birtHelper.getParameterSelectionList(pd, parameters);
						tableModel = createTableModelFromParameterSelectionChoice(parameterSelectionList);
						break;
					}
				}
				break;
			default:
				throw new RuntimeException("Unknown target type " + targetType);
			}
			
		} catch (BirtException e) {
			throw new RuntimeException(e);
		} finally {
			birtHelper.cleanup();
		}
		
		return tableModel;
	}
	
	private RSTableModel createTableModelFromIExtractionResult(IExtractionResults extractionResults) throws BirtException{
		IResultMetaData resultMetaData = extractionResults.getResultMetaData();
		int columnCount = resultMetaData.getColumnCount();
		ArrayList<String> columNames = new ArrayList<String>();
		ArrayList<Class<?>> columnTypes = new ArrayList<Class<?>>();
		for(int i = 0; i < columnCount; i++){
			columNames.add(resultMetaData.getColumnLabel(i));
			columnTypes.add(DataType.getClass(resultMetaData.getColumnType(i)));
		}
		
		TableDefinition tableDefinition = new TableDefinition(columNames, columnTypes);
		RSTableModel tableModel = new RSTableModel(tableDefinition);
		
		IDataIterator dataIterator = extractionResults.nextResultIterator();
		while(dataIterator.next()){
			Object[] data = new Object[columnCount];
			for(int i = 0; i < columnCount; i++){
				data[i] = dataIterator.getValue(i);
			}

			RSTableRow row = new RSTableRow(tableDefinition, data);
			tableModel.addDataRow(row);
		}
		
		return tableModel;
	}
	
	private RSTableModel createTableModelFromParameterSelectionChoice(Collection<IParameterSelectionChoice> parameterSelectionList){
		Class<?> valueType = null;
		TableDefinition tableDefinition = new TableDefinition(Arrays.asList("label", "value"));
		RSTableModel tableModel = new RSTableModel(tableDefinition);

		Iterator<IParameterSelectionChoice> it = parameterSelectionList.iterator();
		while(it.hasNext()){
			IParameterSelectionChoice selectionChoice = it.next();
			
			String label = selectionChoice.getLabel();
			Object value = selectionChoice.getValue();
			
			if(null == valueType){
				valueType = value.getClass();
			}
			
			Object[] data = new Object[]{value, StringUtils.isEmpty(label) ? String.valueOf(value) : label};
			RSTableRow row = new RSTableRow(tableDefinition, data);
			tableModel.addDataRow(row);
		}
		
		tableDefinition.setColumnTypes(Arrays.asList(valueType, String.class));
		
		return tableModel;
	}


}
