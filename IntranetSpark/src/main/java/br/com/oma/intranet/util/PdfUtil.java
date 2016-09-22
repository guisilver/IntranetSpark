package br.com.oma.intranet.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.oma.intranet.dao.ContatosDAO;
import br.com.oma.intranet.entidades.intra_agd_contatos;

public class PdfUtil {
	private String FILE;// = "c:/temp/FirstPdf2015.pdf";
	// private Font cat26 = new Font(Font.FontFamily.TIMES_ROMAN, 26,
	// Font.BOLD);
	private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	// private Font small = new Font(Font.FontFamily.TIMES_ROMAN, 10,
	// Font.NORMAL);

	public String randomImageName() {
		int i = (int) (Math.random() * 100000);
		return String.valueOf(i);
	}

	/*-------------------------------------------------LISTA DE RAMAIS--------------------------------------------------*/

	public String geraRamais(EntityManager manager) {		
		try {
			Document document = new Document();
			// this.FILE = this.randomImageName();
			this.FILE = "Ramais";
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String arquivo = servletContext.getRealPath("") + File.separator
					+ "resources" + File.separator + "arquivos"
					+ File.separator + this.FILE + ".pdf";

			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(arquivo));
			//writer.setPageEvent(new HeaderAndFooterUtil());
			document.open();
			addMetaData(document);
			conteudoRamais(document, manager);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.FILE;
	}

	private void addMetaData(Document document) {
		document.addTitle("Controle de Correios");
		document.addSubject("OMA");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Guilherme Silveira");
		document.addCreator("Guilherme Silveira");
	}

	public void conteudoRamais(Document document, EntityManager manager)
			throws DocumentException {
		relatorioRamais(document, manager);
	}

	/*
	 * public void relatorioRamais(Document document, EntityManager manager)
	 * throws DocumentException {
	 * 
	 * ContatosRepositorio repositorio = new ContatosRepositorio(manager);
	 * List<intra_agd_contatos> ramais_deptos = repositorio
	 * .pesquisaRamaisDeptos();
	 * 
	 * Paragraph preface = new Paragraph(); Paragraph paragraph = new
	 * Paragraph("Ramais", catFont);
	 * 
	 * paragraph.setAlignment(Element.ALIGN_CENTER); //
	 * paragraph.setIndentationLeft(50); preface.add(paragraph);
	 * document.add(preface);
	 * 
	 * if (ramais_deptos.size() > 0) {
	 * 
	 * for (Object aux : ramais_deptos) { document.add(new Paragraph(" "));
	 * document.add(new Paragraph(aux.toString())); document.add(new
	 * Paragraph(" "));
	 * 
	 * document.add(new Paragraph(" "));
	 * 
	 * 
	 * PdfPTable table = new PdfPTable(2);
	 * 
	 * List<Object[]> intra_agd_tel = repositorio.pesquisaRamais(aux
	 * .toString());
	 * 
	 * for (Object[] aux2 : intra_agd_tel) {
	 * 
	 * PdfPCell c2 = new PdfPCell(); c2 = new PdfPCell(new
	 * Phrase(String.valueOf(aux2[0])));
	 * c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * c2.setBorder(Rectangle.NO_BORDER); table.addCell(c2);
	 * 
	 * c2 = new PdfPCell(new Phrase(String.valueOf(aux2[1])));
	 * c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * c2.setBorder(Rectangle.NO_BORDER); table.addCell(c2);
	 * 
	 * } document.add(table); document.add(new Paragraph(" "));
	 * 
	 * } } else { Paragraph preface2 = new Paragraph(); Paragraph paragraph2 =
	 * new Paragraph("Nenhum correio pendente.");
	 * paragraph2.setAlignment(Element.ALIGN_CENTER); preface2.add(paragraph2);
	 * document.add(preface2); } }
	 */

	public void relatorioRamais(Document document, EntityManager manager)
			throws DocumentException {

		ContatosDAO repositorio = new ContatosDAO(manager);
		List<intra_agd_contatos> ramais_deptos = repositorio
				.pesquisaRamaisDeptos();

		Paragraph preface = new Paragraph();
		Paragraph paragraph = new Paragraph("Ramais", catFont);

		paragraph.setAlignment(Element.ALIGN_CENTER);
		preface.add(paragraph);
		document.add(preface);

		if (ramais_deptos.size() > 0) {

			for (Object aux : ramais_deptos) {
				document.add(new Paragraph(" "));

				Paragraph depto = new Paragraph(aux.toString(), subFont);
				depto.setAlignment(Element.ALIGN_CENTER);
				document.add(depto);
				document.add(new Paragraph(" "));

				List<Object[]> intra_agd_tel = repositorio.pesquisaRamais(aux
						.toString());

				for (Object[] aux2 : intra_agd_tel) {

					if (aux2[0] != null) {
						Paragraph conteudo = new Paragraph(
								String.valueOf(aux2[0]));
						document.add(conteudo);
					}

					if (aux2[1] != null) {
						Paragraph conteudo2 = new Paragraph(
								String.valueOf(aux2[1]));
						document.add(conteudo2);
					}

					if (aux2[2] != null) {
						Paragraph conteudo3 = new Paragraph(
								String.valueOf(aux2[2]));
						document.add(conteudo3);
					}

					Paragraph conteudo4 = new Paragraph(
							"_____________________________________________________________________________");
					document.add(conteudo4);

				}
				document.add(new Paragraph(" "));

			}
		} else {
			Paragraph preface2 = new Paragraph();
			Paragraph paragraph2 = new Paragraph("Nenhum correio pendente.");
			paragraph2.setAlignment(Element.ALIGN_CENTER);
			preface2.add(paragraph2);
			document.add(preface2);
		}
	}

	/*-----------------------------------------M�TODOS---------------------------------------------*/

	@SuppressWarnings("unused")
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public String getFILE() {
		return FILE;
	}

	public void setFILE(String fILE) {
		FILE = fILE;
	}

	public Font getCatFont() {
		return catFont;
	}

	public void setCatFont(Font catFont) {
		this.catFont = catFont;
	}

	public Font getRedFont() {
		return redFont;
	}

	public void setRedFont(Font redFont) {
		this.redFont = redFont;
	}

	public Font getSubFont() {
		return subFont;
	}

	public void setSubFont(Font subFont) {
		this.subFont = subFont;
	}

	public Font getSmallBold() {
		return smallBold;
	}

	public void setSmallBold(Font smallBold) {
		this.smallBold = smallBold;
	}

}