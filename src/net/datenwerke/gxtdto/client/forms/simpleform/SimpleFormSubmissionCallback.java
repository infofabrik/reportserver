package net.datenwerke.gxtdto.client.forms.simpleform;

public abstract class SimpleFormSubmissionCallback {
	
	private SimpleFormCbProcessor cbProc;
	
	protected SimpleForm form;
	
	public SimpleFormSubmissionCallback(SimpleForm form) {
		super();
		this.form = form;
	}

	public abstract void formSubmitted();

	public void cbFailure(Throwable caught){
		if(null != cbProc)
			cbProc.cbFailure(caught);
	}

	public void cbSuccess(){
		if(null != cbProc)
			cbProc.cbSuccess();
	}
	
	public void setCbProcessor(SimpleFormCbProcessor cbp){
		this.cbProc = cbp;
	}

}
