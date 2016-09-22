package br.com.oma.intranet.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="alerta")
public class AlertaSIP implements Serializable{

	private static final long serialVersionUID = 1L;

	public String alertaSuspenso(int valor){
		if(valor == 3){
			return "alertaSuspenso";
		}else{
			return "fontSize";
		}
	}
}
