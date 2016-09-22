package br.com.oma.omaonline.util;

import java.lang.reflect.Field;
import java.util.Collection;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.oma.omaonline.entidades.black_list;

@FacesConverter(forClass= black_list.class,value="selectOneUsingObjectConverter")
public class SelectOneUsingObjectConverter implements Converter{
	   @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {

	        black_list retorno = null;

	        if (value != null) {
	          // retorno = (black_list) this.distribuidoraServices.findById(new Long(value));
	        }

	        return retorno;
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {

	        if (value != null) {
	            int code = ((black_list) value).getCodigo();
	            String stringRetorno = (code == 0 ? null : String.valueOf(code));
	            return stringRetorno;
	        }
	        return "";
	    }
}