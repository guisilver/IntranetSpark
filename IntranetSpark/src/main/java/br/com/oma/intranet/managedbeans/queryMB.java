package br.com.oma.intranet.managedbeans;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.QueryRelatorioJasperUtil;

@ManagedBean(name = "qMB")
@ViewScoped

public class queryMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -77778205668730606L;
	
	private StreamedContent stream, stream2, stream3, stream4, stream5, stream6;
	
	public StreamedContent getStream() {
		try {
			this.gerarelatorioExcelCondomino();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}
	
	public StreamedContent getStream2() {
		try {
			this.gerarelatorioExcelSindico();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream2;
	}

	public void setStream2(StreamedContent stream2) {
		this.stream2 = stream2;
	}
	
	public StreamedContent getStream3() {
		try {
			this.gerarelatorioExcelProprietario();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream3;
	}

	public void setStream3(StreamedContent stream3) {
		this.stream3 = stream3;
	}
	
	public StreamedContent getStream4() {
		try {
			this.gerarelatorioExcelInquilino();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream4;
	}

	public void setStream4(StreamedContent stream4) {
		this.stream4 = stream4;
	}
	
	public StreamedContent getStream5() {
		try {
			this.gerarelatorioExcelZelador();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return stream5;
	}

	public void setStream5(StreamedContent stream5) {
		this.stream5 = stream5;
	}
	
	public StreamedContent getStream6() {
		try {
			this.gerarelatorioExcelZelador2();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream6;
	}

	public void setStream6(StreamedContent stream6) {
		this.stream6 = stream6;
	}
	
	// ↓ MÉTODO PARA RELATÓRIO DE CONDÔMINO ↓
	
	public void gerarelatorioExcelCondomino() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();	
		rju.geraRelCond(parametros, "QueryCondomino", "QueryCondomino", 2);
		this.stream = rju.getStream();
		}
	
	// ↓ MÉTODO PARA RELATÓRIO DE SÍNDICO ↓
	
	public void gerarelatorioExcelSindico() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		rju.geraRelSind(parametros, "QuerySindico", "QuerySindico", 2);
		this.stream2 = rju.getStream2();
	}

	// ↓ MÉTODO PARA RELATÓRIO DE PROPRITÁRIO ↓
	
	public void gerarelatorioExcelProprietario() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		rju.geraRelProp(parametros, "QueryProprietario", "QueryProprietario", 2);
		this.stream3 = rju.getStream3();
	}

	// ↓ MÉTODO PARA RELATÓRIO DE INQUILINO ↓
	
	public void gerarelatorioExcelInquilino() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		rju.geraRelInq(parametros, "QueryInquilino", "QueryInquilino", 2);
		this.stream4 = rju.getStream4();
	}

	// ↓ MÉTODO PARA RELATÓRIO DE ZELADOR (SIGADM) ↓
	
	public void gerarelatorioExcelZelador() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		rju.geraRelZel(parametros, "QueryZelador", "QueryZelador", 2);
		this.stream5 = rju.getStream5();
	}
	// ↓ MÉTODO PARA RELATÓRIO DE ZELADOR (FOLHAWEB) ↓
	
	public void gerarelatorioExcelZelador2() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		rju.geraRelZel2(parametros, "QueryZelador2", "QueryZelador2", 2);
		this.stream6 = rju.getStream6();
	}
}