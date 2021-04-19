package net.datenwerke.rs.base.service.datasources.helpers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;

import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

public class CsvToTableModelHelper {
	
	private CsvPreference preferences = CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE;

	public CsvToTableModelHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public RSTableModel processCSV(InputStream dataStream, CsvCellProcessorGuesser cpg) throws IOException {
		CsvListReader csvReader = null;
		try{
			csvReader = new CsvListReader(new InputStreamReader(dataStream), getPreferences());
			String[] header = csvReader.getHeader(true);
			
			TableDefinition tableDefinition = new TableDefinition(Arrays.asList(header), Arrays.asList((Class<?>[])cpg.getTyes()));
			RSTableModel table = new RSTableModel(tableDefinition);
			
			List<Object> record;		
			while( (record = csvReader.read(cpg.getCps())) != null ) {
				table.addDataRow(record);
			}

			return table;
		}finally{
			csvReader.close();
		}
	}
	
	public RSTableModel processCSV(byte[] csv) throws IOException{
		CsvCellProcessorGuesser cpg = guessDatatypes(new ByteArrayInputStream(csv), 100);
		
		return processCSV(new ByteArrayInputStream(csv), cpg);
	}
	
	public CsvCellProcessorGuesser guessDatatypes(InputStream dataStream, int limit) throws IOException {
		return guessDatatypes(new InputStreamReader(dataStream), getPreferences(), limit);
	}
	
	public CsvCellProcessorGuesser guessDatatypes(Reader reader, CsvPreference preferences, int limit) throws IOException {
		CsvCellProcessorGuesser cpg = new CsvCellProcessorGuesser();
		try{
			CsvListReader listReader = new CsvListReader(reader, preferences);
			String[] header = listReader.getHeader(true);
			List<String> record;
			while( (record = listReader.read()) != null && listReader.getRowNumber() < limit) {
				cpg.addData(record);

			}
		}finally{
			reader.close();
		}
		return cpg;
	}

	public CsvPreference getPreferences() {
		return preferences;
	}

	public void setPreferences(CsvPreference preferences) {
		this.preferences = preferences;
	}

	public void setPreferences(char quote, char separator, String newline) {
		setPreferences(new CsvPreference.Builder(quote, separator, newline).build());
	}


	
}
