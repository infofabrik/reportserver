package net.datenwerke.rs.terminal.client.terminal.helper;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.terminal.client.terminal.TerminalDao;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec;

import com.google.inject.Inject;

public class AutocompleteHelper {

	public interface AutocompleteCallback{

		void displayAutoCompleteResult(AutocompleteResultDto currentAutoCompleteResult);

		void noResult();

		void autocomplete(String string);

		void manyResults(AutocompleteResultDto resultList);

		void onFailure(Throwable caught);
		
	}
	
	private TerminalDao terminalDao;
	
	private AutocompleteResultDto currentAutoCompleteResult;
	
	@Inject
	public AutocompleteHelper(
		){
	}

	public void init(TerminalDao terminalDao) {
		this.terminalDao = terminalDao;
	}
	
	public void invalidateCurrentAutocompleteResult() {
		currentAutoCompleteResult = null;
	}

	public void autoComplete(String value, final int cursorPos, final AutocompleteCallback callback) {
		if(null != currentAutoCompleteResult){
			callback.displayAutoCompleteResult(currentAutoCompleteResult);
			invalidateCurrentAutocompleteResult();
			return;
		}
		
		terminalDao.autocomplete(value, cursorPos, new RsAsyncCallback<AutocompleteResultDto>(){
			@Override
			public void onSuccess(AutocompleteResultDto result) {
				if(null != result.getCompleteStump())
					callback.autocomplete(result.getCompleteStump());
				else if(((AutocompleteResultDtoDec)result).size() == 0)
					callback.noResult();
				else {
					currentAutoCompleteResult = result;
					callback.manyResults(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}
		});
	}


}
