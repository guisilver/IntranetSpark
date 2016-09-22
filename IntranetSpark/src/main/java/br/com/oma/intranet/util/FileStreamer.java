package br.com.oma.intranet.util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class FileStreamer {

	private StreamedContent file;
	private StreamedContent pdfLancto;

	public StreamedContent getFile() {
		if (this.file != null) {
			return this.file;
		} else {
			return new DefaultStreamedContent();
		}
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public StreamedContent getPdfLancto() {
		if (this.pdfLancto != null) {
			return this.pdfLancto;
		} else {
			return new DefaultStreamedContent();
		}
	}

	public void setPdfLancto(StreamedContent pdfLancto) {
		this.pdfLancto = pdfLancto;
	}

	public void convertImg(byte[] img) throws FileNotFoundException {
		if (img != null) {
			InputStream is = new ByteArrayInputStream(img);
			this.file = new DefaultStreamedContent(is, "image/jpeg");
		}
	}

	public void convertPdf(byte[] arquivo) {
		if (arquivo != null) {
			InputStream is = new ByteArrayInputStream(arquivo);
			this.file = new DefaultStreamedContent(is, "application/pdf",
					"termos2.pdf");
		} else {
			this.file = null;
		}
	}

	public void convertPdfLancto(byte[] arquivo) {
		if (arquivo != null) {
			InputStream is = new ByteArrayInputStream(arquivo);
			this.pdfLancto = new DefaultStreamedContent(is, "application/pdf",
					"pdf-lancto.pdf");
		} else {
			this.pdfLancto = null;
		}
	}

	public long getDate() {
		return System.currentTimeMillis();
	}
}
