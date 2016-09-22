package br.com.oma.intranet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class ExportWord {

	// ATRIBUTOS
	private StreamedContent stream;

	// GET X SET
	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	// METODOS
	public String tipo(String tipo) {
		String t = "";
		switch (tipo) {
		case "AGO":
			t = "Assembleia Geral Ordinaria";
			break;
		case "AGE":
			t = "Assembleia Geral Extraordinaria";
			break;
		case "AGI":
			t = "Assembleia Geral Instalação";
			break;
		}
		return t;
	}

	@SuppressWarnings("static-access")
	public StreamedContent emissor5Obs(String nomeCondo, String endereco,
			String tipo, Date data, Date hr1, Date hr2, String obs1,
			String obs2, String local, String ordem, String sind,
			String outroLocal, String obs) throws Exception {
		StringUtil tnc = new StringUtil();
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("#dataAtual#", new java.text.SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"))
				.format(new Date()));
		mapa.put("#nomeCondo#", tnc.trataNomeComposto(nomeCondo));
		mapa.put("#endereco#", tnc.trataNomeComposto(endereco));
		mapa.put("#tipo#", this.tipo(tipo));
		mapa.put("#data#", new java.text.SimpleDateFormat("dd/MM/yyyy",
				new Locale("pt", "BR")).format(data)
				+ " - "
				+ new java.text.SimpleDateFormat("EEEEEEEEE", new Locale("pt",
						"BR")).format(data));
		mapa.put("#hr1#", new java.text.SimpleDateFormat("HH:mm").format(hr1)
				+ " " + (obs1.trim().isEmpty() ? "" : obs1));
		if (hr2 != null) {
			mapa.put("#hr2#",
					new java.text.SimpleDateFormat("HH:mm").format(hr2) + " "
							+ (obs2.trim().isEmpty() ? "" : obs2));
		}
		if (local.equals("Outro")) {
			mapa.put("#local#", outroLocal);
		} else if (local.equals("Predio")) {
			mapa.put("#local#", "Nas dependências do condomínio");
		} else if (local.equals("OMA")) {
			mapa.put("#local#",
					"Rua Cincinato Braga, 200 - Bela Vista - CEP 01333-010 - São Paulo - SP");
		}

		mapa.put("#ordem#", ordem);
		mapa.put("#sind#", sind);
		mapa.put("#obs#", obs);

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("")
					+ File.separator + "relatorios" + File.separator
					+ "emissorObs.doc";

			String nomeArqui = this.getRandomName();

			File gerado = null;
			StreamedContent retorno = null;

			HWPFDocument word = new HWPFDocument(new POIFSFileSystem(
					new FileInputStream(new File(caminho_rel))));
			Range range = word.getRange();
			for (String keySet : mapa.keySet())
				range.replaceText(keySet, mapa.get(keySet));

			ServletContext servletContext2 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String open = servletContext2.getRealPath("") + File.separator
					+ "relatorios" + File.separator + "relWord"
					+ File.separator + this.getRandomName() + ".doc";

			FileOutputStream out = new FileOutputStream(open);
			word.write(out);
			out.close();

			gerado = new java.io.File(open);
			InputStream conteudo = new FileInputStream(gerado);

			retorno = new DefaultStreamedContent(conteudo, "application/"
					+ ".doc", nomeArqui + "." + ".doc");

			this.stream = retorno;
			// RequestContext.getCurrentInstance().execute("clicaBotao();");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.stream;
	}

	@SuppressWarnings("static-access")
	public StreamedContent emissor5SemObs(String nomeCondo, String endereco,
			String tipo, Date data, Date hr1, Date hr2, String obs1,
			String obs2, String local, String ordem, String sind,
			String outroLocal) throws Exception {
		StringUtil tnc = new StringUtil();
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("#dataAtual#", new java.text.SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"))
				.format(new Date()));
		mapa.put("#nomeCondo#", tnc.trataNomeComposto(nomeCondo));
		mapa.put("#endereco#", tnc.trataNomeComposto(endereco));
		mapa.put("#tipo#", this.tipo(tipo));
		mapa.put("#data#", new java.text.SimpleDateFormat("dd/MM/yyyy",
				new Locale("pt", "BR")).format(data)
				+ " - "
				+ new java.text.SimpleDateFormat("EEEEEEEEE", new Locale("pt",
						"BR")).format(data));
		mapa.put("#hr1#", new java.text.SimpleDateFormat("HH:mm").format(hr1)
				+ " " + (obs1.trim().isEmpty() ? "" : obs1));
		if (hr2 != null) {
			mapa.put("#hr2#",
					new java.text.SimpleDateFormat("HH:mm").format(hr2) + " "
							+ (obs2.trim().isEmpty() ? "" : obs2));
		}
		if (local.equals("Outro")) {
			mapa.put("#local#", outroLocal);
		} else if (local.equals("Predio")) {
			mapa.put("#local#", "Nas dependências do condomínio");
		} else if (local.equals("OMA")) {
			mapa.put("#local#",
					"Rua Cincinato Braga, 200 - Bela Vista - CEP 01333-010 - São Paulo - SP");
		}

		mapa.put("#ordem#", ordem);
		mapa.put("#sind#", sind);

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("")
					+ File.separator + "relatorios" + File.separator
					+ "relWord" + File.separator + "emissorSemObs.doc";
			String nomeArqui = this.getRandomName();

			File gerado = null;
			StreamedContent retorno = null;

			HWPFDocument word = new HWPFDocument(new POIFSFileSystem(
					new FileInputStream(new File(caminho_rel))));
			Range range = word.getRange();
			for (String keySet : mapa.keySet())
				range.replaceText(keySet, mapa.get(keySet));

			ServletContext servletContext2 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String open = servletContext2.getRealPath("") + File.separator
					+ "relatorios" + File.separator + "relWord"
					+ File.separator + this.getRandomName() + ".doc";

			FileOutputStream out = new FileOutputStream(open);
			word.write(out);
			out.close();

			gerado = new java.io.File(open);
			InputStream conteudo = new FileInputStream(gerado);

			retorno = new DefaultStreamedContent(conteudo, "application/"
					+ ".doc", nomeArqui + "." + ".doc");

			this.stream = retorno;
			// RequestContext.getCurrentInstance().execute("clicaBotao();");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.stream;
	}

	@SuppressWarnings("static-access")
	public StreamedContent emissorItemUnicoObs(String nomeCondo,
			String endereco, String tipo, Date data, Date hr1, Date hr2,
			String obs1, String obs2, String local, String ordem, String sind,
			String outroLocal, String obs) throws Exception {
		StringUtil tnc = new StringUtil();
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("#dataAtual#", new java.text.SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"))
				.format(new Date()));
		mapa.put("#nomeCondo#", tnc.trataNomeComposto(nomeCondo));
		mapa.put("#endereco#", tnc.trataNomeComposto(endereco));
		mapa.put("#tipo#", this.tipo(tipo));
		mapa.put("#data#", new java.text.SimpleDateFormat("dd/MM/yyyy",
				new Locale("pt", "BR")).format(data)
				+ " - "
				+ new java.text.SimpleDateFormat("EEEEEEEEE", new Locale("pt",
						"BR")).format(data));
		mapa.put("#hr1#", new java.text.SimpleDateFormat("HH:mm").format(hr1)
				+ " " + (obs1.trim().isEmpty() ? "" : obs1));
		if (hr2 != null) {
			mapa.put("#hr2#",
					new java.text.SimpleDateFormat("HH:mm").format(hr2) + " "
							+ (obs2.trim().isEmpty() ? "" : obs2));
		}
		if (local.equals("Outro")) {
			mapa.put("#local#", outroLocal);
		} else if (local.equals("Predio")) {
			mapa.put("#local#", "Nas dependências do condomínio");
		} else if (local.equals("OMA")) {
			mapa.put("#local#",
					"Rua Cincinato Braga, 200 - Bela Vista - CEP 01333-010 - São Paulo - SP");
		}

		mapa.put("#ordem#", ordem);
		mapa.put("#sind#", sind);
		mapa.put("#obs#", obs);

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("")
					+ File.separator + "relatorios" + File.separator
					+ "relWord" + File.separator + "emissorItemUnicoObs.doc";
			String nomeArqui = this.getRandomName();

			File gerado = null;
			StreamedContent retorno = null;

			HWPFDocument word = new HWPFDocument(new POIFSFileSystem(
					new FileInputStream(new File(caminho_rel))));
			Range range = word.getRange();
			for (String keySet : mapa.keySet())
				range.replaceText(keySet, mapa.get(keySet));

			ServletContext servletContext2 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String open = servletContext2.getRealPath("") + File.separator
					+ "relatorios" + File.separator + "relWord"
					+ File.separator + this.getRandomName() + ".doc";

			FileOutputStream out = new FileOutputStream(open);
			word.write(out);
			out.close();

			gerado = new java.io.File(open);
			InputStream conteudo = new FileInputStream(gerado);

			retorno = new DefaultStreamedContent(conteudo, "application/"
					+ ".doc", nomeArqui + "." + ".doc");

			this.stream = retorno;
			// RequestContext.getCurrentInstance().execute("clicaBotao();");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.stream;
	}

	@SuppressWarnings("static-access")
	public StreamedContent emissorItemUnicoSemObs(String nomeCondo,
			String endereco, String tipo, Date data, Date hr1, Date hr2,
			String obs1, String obs2, String local, String ordem, String sind,
			String outroLocal) throws Exception {
		StringUtil tnc = new StringUtil();
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("#dataAtual#", new java.text.SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"))
				.format(new Date()));
		mapa.put("#nomeCondo#", tnc.trataNomeComposto(nomeCondo));
		mapa.put("#endereco#", tnc.trataNomeComposto(endereco));
		mapa.put("#tipo#", this.tipo(tipo));
		mapa.put("#data#", new java.text.SimpleDateFormat("dd/MM/yyyy",
				new Locale("pt", "BR")).format(data)
				+ " - "
				+ new java.text.SimpleDateFormat("EEEEEEEEE", new Locale("pt",
						"BR")).format(data));
		mapa.put("#hr1#", new java.text.SimpleDateFormat("HH:mm").format(hr1)
				+ " " + (obs1.trim().isEmpty() ? "" : obs1));
		if (hr2 != null) {
			mapa.put("#hr2#",
					new java.text.SimpleDateFormat("HH:mm").format(hr2) + " "
							+ (obs2.trim().isEmpty() ? "" : obs2));
		}
		if (local.equals("Outro")) {
			mapa.put("#local#", outroLocal);
		} else if (local.equals("Predio")) {
			mapa.put("#local#", "Nas dependências do condomínio");
		} else if (local.equals("OMA")) {
			mapa.put("#local#",
					"Rua Cincinato Braga, 200 - Bela Vista - CEP 01333-010 - São Paulo - SP");
		}

		mapa.put("#ordem#", ordem);
		mapa.put("#sind#", sind);

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("")
					+ File.separator + "relatorios" + File.separator
					+ "relWord" + File.separator + "emissorItemUnicoSemObs.doc";
			String nomeArqui = this.getRandomName();

			File gerado = null;
			StreamedContent retorno = null;
			
			//HWPFDocument word = new HWPFDocument(new POIFSFileSystem(new FileInputStream(new File(caminho_rel))));
			
			HWPFDocument word = new HWPFDocument(new FileInputStream(new File(caminho_rel)));
			
			Range range = word.getRange();
			for (String keySet : mapa.keySet())
				range.replaceText(keySet, mapa.get(keySet));

			ServletContext servletContext2 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String open = servletContext2.getRealPath("") + File.separator
					+ "relatorios" + File.separator + "relWord"
					+ File.separator + this.getRandomName() + ".doc";

			FileOutputStream out = new FileOutputStream(open);
			word.write(out);
			out.close();

			gerado = new java.io.File(open);
			InputStream conteudo = new FileInputStream(gerado);

			retorno = new DefaultStreamedContent(conteudo, "application/"
					+ ".doc", nomeArqui + "." + ".doc");

			this.stream = retorno;
			// RequestContext.getCurrentInstance().execute("clicaBotao();");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.stream;
	}
	
	public StreamedContent advWord(Date data, String nomeCondo, String nome,
			String uniBloc, String tipoReg, String tipoClauArt, String ocorridoMotivo, String nomeGeren, String emailGeren, String texto)
			throws Exception {
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("#dataAtual#", new java.text.SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"))
				.format(new Date()));
		mapa.put("#condoNome#", StringUtil.trataNomeComposto(nomeCondo));
		mapa.put("#nome#", nome);
		mapa.put("#dataAtual#", "São Paulo "+ 
		new java.text.SimpleDateFormat("dd",	new Locale("pt", "BR")).format(data) + " de "+ 
				new java.text.SimpleDateFormat("MMMM",	new Locale("pt", "BR")).format(data) + " de " +
				new java.text.SimpleDateFormat("yyyy",	new Locale("pt", "BR")).format(data));
		mapa.put("#uniBl#", uniBloc);
		mapa.put("#tipoReg#", tipoReg);
		mapa.put("#tipoClauArt#", tipoClauArt);
		mapa.put("#ocorridoMotivo#", ocorridoMotivo);
		mapa.put("#nomeGeren#", nomeGeren);
		mapa.put("#emailGeren#", emailGeren);
		mapa.put("#validaTexto#", texto);

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("")
					+ File.separator + "relatorios" + File.separator
					+ "adv-mult-word" + File.separator + "adv.doc";
			String nomeArqui = this.getRandomName();

			File gerado = null;
			StreamedContent retorno = null;
			
			//HWPFDocument word = new HWPFDocument(new POIFSFileSystem(new FileInputStream(new File(caminho_rel))));
			
			HWPFDocument word = new HWPFDocument(new FileInputStream(new File(caminho_rel)));
			
			Range range = word.getRange();
			for (String keySet : mapa.keySet())
				range.replaceText(keySet, mapa.get(keySet));

			ServletContext servletContext2 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String open = servletContext2.getRealPath("") + File.separator
					+ "relatorios" + File.separator + "adv-mult-word"
					+ File.separator + this.getRandomName() + ".doc";

			FileOutputStream out = new FileOutputStream(open);
			word.write(out);
			out.close();

			gerado = new java.io.File(open);
			InputStream conteudo = new FileInputStream(gerado);

			retorno = new DefaultStreamedContent(conteudo, "application/"
					+ ".doc", nomeArqui + "." + ".doc");

			this.stream = retorno;
			// RequestContext.getCurrentInstance().execute("clicaBotao();");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.stream;
	}
	
	public StreamedContent advWordOutros(Date data, String nomeCondo, String nome,
			String uniBloc, String ocorridoMotivo, String nomeGeren, String emailGeren, String texto)
			throws Exception {
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("#dataAtual#", new java.text.SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"))
				.format(new Date()));
		mapa.put("#condoNome#", StringUtil.trataNomeComposto(nomeCondo));
		mapa.put("#nome#", nome);
		mapa.put("#dataAtual#", "São Paulo "+ 
		new java.text.SimpleDateFormat("dd",	new Locale("pt", "BR")).format(data) + " de "+ 
				new java.text.SimpleDateFormat("MMMM",	new Locale("pt", "BR")).format(data) + " de " +
				new java.text.SimpleDateFormat("yyyy",	new Locale("pt", "BR")).format(data));
		mapa.put("#uniBl#", uniBloc);
		mapa.put("#ocorridoMotivo#", ocorridoMotivo);
		mapa.put("#nomeGeren#", nomeGeren);
		mapa.put("#emailGeren#", emailGeren);
		mapa.put("#validaTexto#", texto);

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("")
					+ File.separator + "relatorios" + File.separator
					+ "adv-mult-word" + File.separator + "adv-outros.doc";
			String nomeArqui = this.getRandomName();

			File gerado = null;
			StreamedContent retorno = null;
			
			//HWPFDocument word = new HWPFDocument(new POIFSFileSystem(new FileInputStream(new File(caminho_rel))));
			
			HWPFDocument word = new HWPFDocument(new FileInputStream(new File(caminho_rel)));
			
			Range range = word.getRange();
			for (String keySet : mapa.keySet())
				range.replaceText(keySet, mapa.get(keySet));

			ServletContext servletContext2 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String open = servletContext2.getRealPath("") + File.separator
					+ "relatorios" + File.separator + "adv-mult-word"
					+ File.separator + this.getRandomName() + ".doc";

			FileOutputStream out = new FileOutputStream(open);
			word.write(out);
			out.close();

			gerado = new java.io.File(open);
			InputStream conteudo = new FileInputStream(gerado);

			retorno = new DefaultStreamedContent(conteudo, "application/"
					+ ".doc", nomeArqui + "." + ".doc");

			this.stream = retorno;
			// RequestContext.getCurrentInstance().execute("clicaBotao();");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.stream;
	}
	
	public StreamedContent multWord(Date data, String nomeCondo, String nome,
			String uniBloc, String tipoReg, String tipoClauArt, String ocorridoMotivo, String nomeGeren, String emailGeren, String texto, String valor)
			throws Exception {
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("#dataAtual#", new java.text.SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"))
				.format(new Date()));
		mapa.put("#condoNome#", StringUtil.trataNomeComposto(nomeCondo));
		mapa.put("#nome#", nome);
		mapa.put("#dataAtual#", "São Paulo "+ 
		new java.text.SimpleDateFormat("dd",	new Locale("pt", "BR")).format(data) + " de "+ 
				new java.text.SimpleDateFormat("MMMM",	new Locale("pt", "BR")).format(data) + " de " +
				new java.text.SimpleDateFormat("yyyy",	new Locale("pt", "BR")).format(data));
		mapa.put("#uniBl#", uniBloc);
		mapa.put("#tipoReg#", tipoReg);
		mapa.put("#tipoClauArt#", tipoClauArt);
		mapa.put("#ocorridoMotivo#", ocorridoMotivo);
		mapa.put("#nomeGeren#", nomeGeren);
		mapa.put("#emailGeren#", emailGeren);
		mapa.put("#validaTexto#", texto);
		mapa.put("#valor#", valor);

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("")
					+ File.separator + "relatorios" + File.separator
					+ "adv-mult-word" + File.separator + "mult.doc";
			String nomeArqui = this.getRandomName();

			File gerado = null;
			StreamedContent retorno = null;
			
			//HWPFDocument word = new HWPFDocument(new POIFSFileSystem(new FileInputStream(new File(caminho_rel))));
			
			HWPFDocument word = new HWPFDocument(new FileInputStream(new File(caminho_rel)));
			
			Range range = word.getRange();
			for (String keySet : mapa.keySet())
				range.replaceText(keySet, mapa.get(keySet));

			ServletContext servletContext2 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String open = servletContext2.getRealPath("") + File.separator
					+ "relatorios" + File.separator + "adv-mult-word"
					+ File.separator + this.getRandomName() + ".doc";

			FileOutputStream out = new FileOutputStream(open);
			word.write(out);
			out.close();

			gerado = new java.io.File(open);
			InputStream conteudo = new FileInputStream(gerado);

			retorno = new DefaultStreamedContent(conteudo, "application/"
					+ ".doc", nomeArqui + "." + ".doc");

			this.stream = retorno;
			// RequestContext.getCurrentInstance().execute("clicaBotao();");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.stream;
	}
	
	public StreamedContent multWordOutros(Date data, String nomeCondo, String nome,
			String uniBloc, String ocorridoMotivo, String nomeGeren, String emailGeren, String texto, String valor)
			throws Exception {
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("#dataAtual#", new java.text.SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"))
				.format(new Date()));
		mapa.put("#condoNome#", StringUtil.trataNomeComposto(nomeCondo));
		mapa.put("#nome#", nome);
		mapa.put("#dataAtual#", "São Paulo "+ 
		new java.text.SimpleDateFormat("dd",	new Locale("pt", "BR")).format(data) + " de "+ 
				new java.text.SimpleDateFormat("MMMM",	new Locale("pt", "BR")).format(data) + " de " +
				new java.text.SimpleDateFormat("yyyy",	new Locale("pt", "BR")).format(data));
		mapa.put("#uniBl#", uniBloc);
		mapa.put("#ocorridoMotivo#", ocorridoMotivo);
		mapa.put("#nomeGeren#", nomeGeren);
		mapa.put("#emailGeren#", emailGeren);
		mapa.put("#validaTexto#", texto);
		mapa.put("#valor#", valor);

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("")
					+ File.separator + "relatorios" + File.separator
					+ "adv-mult-word" + File.separator + "mult-outros.doc";
			String nomeArqui = this.getRandomName();

			File gerado = null;
			StreamedContent retorno = null;
			
			//HWPFDocument word = new HWPFDocument(new POIFSFileSystem(new FileInputStream(new File(caminho_rel))));
			
			HWPFDocument word = new HWPFDocument(new FileInputStream(new File(caminho_rel)));
			
			Range range = word.getRange();
			for (String keySet : mapa.keySet())
				range.replaceText(keySet, mapa.get(keySet));

			ServletContext servletContext2 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String open = servletContext2.getRealPath("") + File.separator
					+ "relatorios" + File.separator + "adv-mult-word"
					+ File.separator + this.getRandomName() + ".doc";

			FileOutputStream out = new FileOutputStream(open);
			word.write(out);
			out.close();

			gerado = new java.io.File(open);
			InputStream conteudo = new FileInputStream(gerado);

			retorno = new DefaultStreamedContent(conteudo, "application/"
					+ ".doc", nomeArqui + "." + ".doc");

			this.stream = retorno;
			// RequestContext.getCurrentInstance().execute("clicaBotao();");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.stream;
	}

	public String getRandomName() {
		int i = (int) (Math.random() * 100000);
		return String.valueOf(i);
	}
}