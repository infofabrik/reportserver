package net.datenwerke.gxtdto.client.servercommunication.callback;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.info.Info;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.locale.CallbackHandlerMessages;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.client.utils.StringEscapeUtils;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public abstract class HandledAsyncCallback<T> implements AsyncCallback<T>{
	
	@Inject
	private static HookHandlerService hookHandlerService;
	
	private String successText = null;
	private String successTitle = null;
	private String errorText = null;
	private String errorTitle = null;
	private Request request;
	
	public HandledAsyncCallback(String successText) {
		 this.successText = successText;
		 this.successTitle = BaseMessages.INSTANCE.error();
		 
		 doOnStartup();
	}
	
	public HandledAsyncCallback(String errorText, String successText) {
		 this.successText = successText;
		 this.successTitle = BaseMessages.INSTANCE.changesApplied();
		 this.errorText = errorText;
		 this.errorTitle = BaseMessages.INSTANCE.error();
		 
		 doOnStartup();
	}
	
	public HandledAsyncCallback(String errorTitle, String errorText, String successTitle, String successText) {
		this.successText = successText;
		this.successTitle = successTitle;
		this.errorText = errorText;
		this.errorTitle = errorTitle;

		doOnStartup();
	}
	
	public void onFailure(final Throwable caught) {
		doOnFinish();
		
		if(caught instanceof ViolatedSecurityExceptionDto){
			handleViolatedSecurityException((ViolatedSecurityExceptionDto) caught);
		}else{
			displayErrrorMessage(caught);
			doOnFailure(caught);
		}
	}

	private void handleViolatedSecurityException(ViolatedSecurityExceptionDto caught) {
		List<ViolatedSecurityHook> vshookers = hookHandlerService.getHookers(ViolatedSecurityHook.class);

		vshookers.add(new ViolatedSecurityHook() {
			@Override
			public void violationOccured(ViolatedSecurityExceptionDto caught, List<ViolatedSecurityHook> chain) {
				displayErrrorMessage(caught);
				doOnFailure(caught);
			}
		});
		
		vshookers.remove(0).violationOccured(caught, vshookers);
	}

	protected void displayErrrorMessage(final Throwable caught) {
		if(null != errorText && null != errorTitle){
			new DwAlertMessageBox(errorTitle, errorText) {
				protected void onHide() {
					doOnFailure(caught);
				}
			}.show();
		} else 
			handleException(caught);
	}

	protected void handleException(Throwable caught) {
		if(caught instanceof ExpectedException)
			handleExpectedException((ExpectedException) caught);
		else if(caught instanceof NonFatalException)
			handleUnexpectedNonevalException((NonFatalException) caught);
		else if(caught instanceof StatusCodeException && 0 == ((StatusCodeException)caught).getStatusCode()){
			GWT.log("Statuscode 0 Exception");
			return;
		} else{
			caught.printStackTrace();
			handleGenericException(caught);
		}
	}
	
	private void handleExpectedException(ExpectedException caught) {
		String msg = caught.getLocalizedMessage() == null ? caught.getMessage() : caught.getLocalizedMessage();
		String title = caught.getTitle();
		
		SimpleErrorDialog dialog = new SimpleErrorDialog(title, msg);
		dialog.show();
	}
	
	protected void handleUnexpectedNonevalException(NonFatalException caught) {
		/* get messages to display */
		String title = caught.getTitle();
		String msg = caught.getLocalizedMessage() == null ? caught.getMessage() : caught.getLocalizedMessage();
		if(null == msg || "".equals(msg)) //$NON-NLS-1$
			msg = StringEscapeUtils.unescapeHTML(CallbackHandlerMessages.INSTANCE.defaultErrorMessage());
		String stacktrace = null;
		if(caught instanceof ServerCallFailedException){
			stacktrace = ((ServerCallFailedException)caught).getStackTraceAsString();
			title =  ((ServerCallFailedException)caught).getTitle();
		}
		
		DetailErrorDialog window = new DetailErrorDialog(title, msg, stacktrace);
		window.show();
	}

	protected void handleGenericException(Throwable caught) {
		/* get messages to display */
		String title = BaseMessages.INSTANCE.error();
		String msg = caught.getLocalizedMessage() == null ? caught.getMessage() : caught.getLocalizedMessage();
		if(null == msg || "".equals(msg)) //$NON-NLS-1$
			msg = StringEscapeUtils.unescapeHTML(CallbackHandlerMessages.INSTANCE.defaultErrorMessage());
		String stacktrace = null;
		if(caught instanceof ServerCallFailedException){
			stacktrace = ((ServerCallFailedException)caught).getStackTraceAsString();
			title =  ((ServerCallFailedException)caught).getTitle();
		} else if (caught instanceof StatusCodeException){
			msg += "\nStatuscode: " + ((StatusCodeException)caught).getStatusCode() + 
					"\nEncodedResponse: " + ((StatusCodeException)caught).getEncodedResponse();  
		}
		
		new DetailErrorDialog(title, msg, stacktrace).show();
	}


	final public void onSuccess(T result) {
		doOnFinish();
		if(null != successTitle && null != successText)
			Info.display(successTitle, successText);
		doOnSuccess(result);
		
		for(AsyncCallbackSuccessHook hook : hookHandlerService.getHookers(AsyncCallbackSuccessHook.class)){
			hook.onSuccess();
		}
	}
	
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public Request getRequest() {
		return request;
	}
	

	public void doOnSuccess(T result){}
	public void doOnFailure(Throwable caught){}
	
	public void doOnStartup(){};
	public void doOnFinish(){}

	public String getSuccessText() {
		return successText;
	}

	public void setSuccessText(String successText) {
		this.successText = successText;
	}

	public String getSuccessTitle() {
		return successTitle;
	}

	public void setSuccessTitle(String successTitle) {
		this.successTitle = successTitle;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}

	
	
	
}
