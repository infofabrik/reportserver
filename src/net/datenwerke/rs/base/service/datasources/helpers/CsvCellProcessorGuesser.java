package net.datenwerke.rs.base.service.datasources.helpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.StrReplace;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;

public class CsvCellProcessorGuesser {

	class ProcessorGuess {
		private CellProcessorAdaptor cellprocessor;
		private Class type;
		
		public ProcessorGuess(CellProcessorAdaptor cellprocessor, Class type) {
			this.cellprocessor = cellprocessor;
			this.type = type;
		}

		public CellProcessorAdaptor getCellprocessor() {
			return cellprocessor;
		}

		public Class getType() {
			return type;
		}
		
		@Override
		public String toString() {
			return type.getName() + ": " + cellprocessor.toString();
		}
	}

	ArrayList<ProcessorGuess> cps;
	ArrayList<ProcessorGuess> test;

	public CsvCellProcessorGuesser() {
		test = new ArrayList<ProcessorGuess>();
		test.add(new ProcessorGuess(new ParseLong(), Long.class));
		test.add(new ProcessorGuess(new ParseBigDecimal(), BigDecimal.class));
		test.add(new ProcessorGuess(new ParseBool(), Boolean.class));
		test.add(new ProcessorGuess(new StrRegEx(".+%", new StrReplace("%", "", new ParseBigDecimal())), BigDecimal.class));
		test.add(new ProcessorGuess(new ParseDate("yyyy-MM-dd HH:mm"), Date.class));
		test.add(new ProcessorGuess(new ParseDate("dd.MM.yyyy HH:mm"), Date.class));
		test.add(new ProcessorGuess(new ParseDate("dd.MM.yyyy"), Date.class));
		test.add(new ProcessorGuess(new ParseDate("yyyy-MM-dd"), Date.class));
	}

	public ArrayList<ProcessorGuess> getGuesses(){
		return cps;
	}
	
	public CellProcessor[] getCps() {
		ArrayList<CellProcessor> res = new ArrayList();
		for(ProcessorGuess tt : cps){
			if(null != tt){
				res.add(new Optional(tt.getCellprocessor()));
			}else{
				res.add(new Optional());
			}
		}
		return res.toArray(new CellProcessor[0]);
	}

	public Class[] getTyes() {
		ArrayList<Class> res = new ArrayList();
		for(ProcessorGuess tt : cps){
			if(null != tt){
				res.add(tt.getType());
			}else{
				res.add(String.class);
			}
		}
		return res.toArray(new Class[0]);
	}

	public void addData(List<String> record){
		if(null == cps){
			cps = new ArrayList<ProcessorGuess>();
			for(int i = 0; i < record.size(); i++){
				cps.add(test.get(0));
			}
		}

		for(int i = 0; i < record.size(); i++){
			String value = record.get(i);
			if(null == value)
				continue;

			boolean error = true;
			while(error){
				try{
					if(null != cps.get(i)){
						new Optional(cps.get(i).getCellprocessor()).execute(value, null);
					}
					error = false;
				}catch(SuperCsvCellProcessorException e){
					int j = test.indexOf(cps.get(i));
					if(j < test.size() - 1){
						cps.set(i, test.get(j + 1));
					}else{
						cps.set(i, null);
					}
				}
			}
		}
	}
}
