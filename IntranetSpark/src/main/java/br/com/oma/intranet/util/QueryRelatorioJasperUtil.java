package br.com.oma.intranet.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.filters.Conexao;

public class QueryRelatorioJasperUtil implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int RELATORIO_PDF = 1;
	public static final int RELATORIO_EXCEL = 2;
	public static final int RELATORIO_HTML = 3;
	public static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
	public static final int RELATORIO_DOCX = 5;
	private StreamedContent stream, stream2, stream3, stream4, stream5, stream6;

	public StreamedContent getStream6() {
		return stream6;
	}

	public void setStream6(StreamedContent stream6) {
		this.stream6 = stream6;
	}

	public StreamedContent getStream2() {
		return stream2;
	}

	public void setStream2(StreamedContent stream2) {
		this.stream2 = stream2;
	}

	public StreamedContent getStream3() {
		return stream3;
	}

	public void setStream3(StreamedContent stream3) {
		this.stream3 = stream3;
	}

	public StreamedContent getStream4() {
		return stream4;
	}

	public void setStream4(StreamedContent stream4) {
		this.stream4 = stream4;
	}

	public StreamedContent getStream5() {
		return stream5;
	}

	public void setStream5(StreamedContent stream5) {
		this.stream5 = stream5;
	}

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRelSind(Map parametros, String nome_entrada, String nome_saida, int tipo) throws Exception {
		StreamedContent retorno = null;
		byte[] b;
		try {
			Connection conex = Conexao.getConexao();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case QueryRelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter tipo_exportado = new JRPdfExporter();
				extensao_exportada = "pdf";
				tipo_exportado.setExporterInput(new SimpleExporterInput(impressora_jasper));
				tipo_exportado.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				tipo_exportado.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream2 = retorno;

				break;
			case QueryRelatorioJasperUtil.RELATORIO_EXCEL:
				JRXlsExporter exporter = new JRXlsExporter();

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setFontSizeFixEnabled(true);

				exporter.setConfiguration(configuration);

				Map<String, String> map = new HashMap<>();
				map.put("whitePageBackground", "false");
				extensao_exportada = "xls";

				exporter.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporter.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream2 = retorno;
				break;
			}
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRelProp(Map parametros, String nome_entrada, String nome_saida, int tipo) throws Exception {
		StreamedContent retorno = null;
		byte[] b;
		try {
			Connection conex = Conexao.getConexao();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case QueryRelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter tipo_exportado = new JRPdfExporter();
				extensao_exportada = "pdf";
				tipo_exportado.setExporterInput(new SimpleExporterInput(impressora_jasper));
				tipo_exportado.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				tipo_exportado.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream3 = retorno;

				break;
			case QueryRelatorioJasperUtil.RELATORIO_EXCEL:
				JRXlsExporter exporter = new JRXlsExporter();

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setFontSizeFixEnabled(true);

				exporter.setConfiguration(configuration);

				Map<String, String> map = new HashMap<>();
				map.put("whitePageBackground", "false");
				extensao_exportada = "xls";

				exporter.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporter.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream3 = retorno;
				break;
			}
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRelInq(Map parametros, String nome_entrada, String nome_saida, int tipo) throws Exception {
		StreamedContent retorno = null;
		byte[] b;
		try {
			Connection conex = Conexao.getConexao();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case QueryRelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter tipo_exportado = new JRPdfExporter();
				extensao_exportada = "pdf";
				tipo_exportado.setExporterInput(new SimpleExporterInput(impressora_jasper));
				tipo_exportado.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				tipo_exportado.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream4 = retorno;

				break;
			case QueryRelatorioJasperUtil.RELATORIO_EXCEL:
				JRXlsExporter exporter = new JRXlsExporter();

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setFontSizeFixEnabled(true);

				exporter.setConfiguration(configuration);

				Map<String, String> map = new HashMap<>();
				map.put("whitePageBackground", "false");
				extensao_exportada = "xls";

				exporter.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporter.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream4 = retorno;
				break;
			}
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRelZel(Map parametros, String nome_entrada, String nome_saida, int tipo) throws Exception {
		StreamedContent retorno = null;
		byte[] b;
		try {
			Connection conex = Conexao.getConexao();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case QueryRelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter tipo_exportado = new JRPdfExporter();
				extensao_exportada = "pdf";
				tipo_exportado.setExporterInput(new SimpleExporterInput(impressora_jasper));
				tipo_exportado.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				tipo_exportado.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream5 = retorno;

				break;
			case QueryRelatorioJasperUtil.RELATORIO_EXCEL:
				JRXlsExporter exporter = new JRXlsExporter();

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setFontSizeFixEnabled(true);

				exporter.setConfiguration(configuration);

				Map<String, String> map = new HashMap<>();
				map.put("whitePageBackground", "false");
				extensao_exportada = "xls";

				exporter.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporter.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream5 = retorno;
				break;
			}
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRelZel2(Map parametros, String nome_entrada, String nome_saida, int tipo) throws Exception {
		StreamedContent retorno = null;
		byte[] b;
		try {
			Connection conex = Conexao.getConexao();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case QueryRelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter tipo_exportado = new JRPdfExporter();
				extensao_exportada = "pdf";
				tipo_exportado.setExporterInput(new SimpleExporterInput(impressora_jasper));
				tipo_exportado.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				tipo_exportado.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream6 = retorno;

				break;
			case QueryRelatorioJasperUtil.RELATORIO_EXCEL:
				JRXlsExporter exporter = new JRXlsExporter();

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setFontSizeFixEnabled(true);

				exporter.setConfiguration(configuration);

				Map<String, String> map = new HashMap<>();
				map.put("whitePageBackground", "false");
				extensao_exportada = "xls";

				exporter.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporter.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream6 = retorno;
				break;
			}
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRelCond(Map parametros, String nome_entrada, String nome_saida, int tipo) throws Exception {
		StreamedContent retorno = null;
		byte[] b;
		try {
			Connection conex = Conexao.getConexao();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case QueryRelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter tipo_exportado = new JRPdfExporter();
				extensao_exportada = "pdf";
				tipo_exportado.setExporterInput(new SimpleExporterInput(impressora_jasper));
				tipo_exportado.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				tipo_exportado.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream = retorno;

				break;
			case QueryRelatorioJasperUtil.RELATORIO_EXCEL:
				JRXlsExporter exporter = new JRXlsExporter();

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setFontSizeFixEnabled(true);

				exporter.setConfiguration(configuration);

				Map<String, String> map = new HashMap<>();
				map.put("whitePageBackground", "false");
				extensao_exportada = "xls";

				exporter.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporter.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream = retorno;
				break;
			}
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRel2(Map parametros, String nome_entrada,
			String nome_saida, int tipo) throws Exception {
		StreamedContent retorno = null;
		byte[] b;
		try {
			Connection conex = Conexao.getConexao();
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada	+ ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader
					.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(
					relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case RelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter tipo_exportado = new JRPdfExporter();
				extensao_exportada = "pdf";
				tipo_exportado.setExporterInput(new SimpleExporterInput(
						impressora_jasper));
				tipo_exportado
						.setExporterOutput(new SimpleOutputStreamExporterOutput(
								output));
				tipo_exportado.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(
						output.toByteArray()), "application/"
						+ extensao_exportada, nome_saida + "."
						+ extensao_exportada);
				this.stream = retorno;

				break;
			case RelatorioJasperUtil.RELATORIO_EXCEL:
				JRXlsExporter exporter = new JRXlsExporter();

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setFontSizeFixEnabled(true);

				exporter.setConfiguration(configuration);

				Map<String, String> map = new HashMap<>();
				map.put("whitePageBackground", "false");
				extensao_exportada = "xls";

				exporter.setExporterInput(new SimpleExporterInput(
						impressora_jasper));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
						output));
				exporter.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(
						output.toByteArray()), "application/"
						+ extensao_exportada, nome_saida + "."
						+ extensao_exportada);
				this.stream = retorno;
				break;
			}
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}
}
