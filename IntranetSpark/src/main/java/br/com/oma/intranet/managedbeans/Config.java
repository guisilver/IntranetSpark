package br.com.oma.intranet.managedbeans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean(name="config")
public class Config implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3141041284570512773L;
	private String banco;
	
	public Config(String banco) {
		this.banco = banco;
	}
	
	public Properties Conf() {
		if(banco.trim().equals("local")){
			Properties p = new Properties();
			FacesContext context = FacesContext.getCurrentInstance();
			String caminho_rel = context.getExternalContext().getRealPath("/config/databaseLocal.properties");
			try {
				FileInputStream in = new FileInputStream(caminho_rel);
				p.load(in);
				in.close();
				return p;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return p;
		}else if(banco.trim().equals("producao")){
			Properties p = new Properties();
			FacesContext context = FacesContext.getCurrentInstance();
			String caminho_rel = context.getExternalContext().getRealPath("/config/databaseProducao.properties");
			try {
				FileInputStream in = new FileInputStream(caminho_rel);
				p.load(in);
				in.close();
				return p;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return p;
		}else if(banco.trim().equals("email")){
			Properties p = new Properties();
			FacesContext context = FacesContext.getCurrentInstance();
			String caminho_rel = context.getExternalContext().getRealPath("/config/email.properties");
			try {
				FileInputStream in = new FileInputStream(caminho_rel);
				p.load(in);
				in.close();
				return p;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return p;
		}else if(this.banco.trim().equals("persistLocal")){
			Properties p = new Properties();
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("/config/persistLocal.properties");
			
			try {
				FileInputStream in = new FileInputStream(caminho_rel);
				p.load(in);
				in.close();
				return p;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return p;
		}else if(this.banco.trim().equals("persistProducao")){
			Properties p = new Properties();
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("/config/persistProducao.properties");
			try {
				FileInputStream in = new FileInputStream(caminho_rel);
				p.load(in);
				in.close();
				return p;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return p;
		}else{
			return null;
		}
			
	}
}
