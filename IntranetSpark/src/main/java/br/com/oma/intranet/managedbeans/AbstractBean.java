package br.com.oma.intranet.managedbeans;

import org.primefaces.context.RequestContext;

public class AbstractBean {

	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractBean() {
		super();
	}
	
	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}

	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}

	protected void closeDialog() {
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}

	protected void keepDialogOpen() {
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}

	protected RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	protected void hideDialog(String nomeDialog) {
		RequestContext.getCurrentInstance().execute(
				"PF('" + nomeDialog + "').hide()");
	}

	protected void showDialog(String nomeDialog) {
		RequestContext.getCurrentInstance().execute(
				"PF('" + nomeDialog + "').show()");
	}
}