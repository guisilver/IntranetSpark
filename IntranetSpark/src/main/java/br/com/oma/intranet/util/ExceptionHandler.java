package br.com.oma.intranet.util;

import java.io.IOException;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ExceptionHandler {

	public void throwException() throws Exception {
		throw new Exception("Exception!");
	}

	public void redirectError() throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "error.xhtml?faces-redirect=true");
	}

}
